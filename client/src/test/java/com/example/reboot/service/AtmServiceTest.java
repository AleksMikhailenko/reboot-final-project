package com.example.reboot.service;

import com.example.reboot.dto.*;
import com.example.reboot.enums.Currency;
import com.example.reboot.exception.AtmException;
import com.example.reboot.service.impl.AtmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestPropertySource("classpath:application.yml")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AtmServiceTest {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    Environment env;

    AtmService atmService;
    String URL = "http://localhost:9090/api/v1.0/cards";

    @BeforeEach
    void setup() {
        atmService = new AtmServiceImpl(restTemplate, env);
    }

    @Test
    void shouldReturnSuccessBalanceTest() {
        // given
        InfoByCardRq rq = InfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        CardDTO cardDTO = CardDTO.builder()
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();

        InfoByCardRs rs = InfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(0)
                        .statusDesc("Карта успешно найдена")
                        .build())
                .card(cardDTO)
                .build();

        BalanceDTO balanceDTO = BalanceDTO.builder()
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .message(String.format("Баланс по карте на %s", LocalDate.now()))
                .build();

        // when
        when(restTemplate.postForObject(env.getProperty("atm.server.url", URL), rq, InfoByCardRs.class))
                .thenReturn(rs);

        // then
        assertEquals(balanceDTO, atmService.retrieveCardBalance(rq));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Карта не найдена', 'Карта не действительна или завершился срок использования карты'",
            "2, 'Введен неверный пин-код', 'Введен неверный пин-код, попробуйте ещё раз'",
            "3, 'Внутренняя ошибка при обработке запроса', 'Произошла внутренняя ошибка'"
    })
    void shouldCheckBalanceDTOTest(int code, String desc, String message) {
        // given
        InfoByCardRq rq = InfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        InfoByCardRs rs = InfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(code)
                        .statusDesc(desc)
                        .build())
                .card(null)
                .build();

        BalanceDTO balanceDTO = BalanceDTO.builder()
                .message(message)
                .build();

        // when
        when(restTemplate.postForObject(env.getProperty("atm.server.url", URL), rq, InfoByCardRs.class))
                .thenReturn(rs);

        // then
        assertEquals(balanceDTO, atmService.retrieveCardBalance(rq));
    }

    @Test
    void shouldThrowAtmExceptionTest() {
        // given
        InfoByCardRq rq = InfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        // when
        when(restTemplate.postForObject(env.getProperty("atm.server.url", URL), rq, InfoByCardRs.class))
                .thenReturn(null);

        // then
        assertThrows(AtmException.class,
                () -> atmService.retrieveCardBalance(rq));
    }
}