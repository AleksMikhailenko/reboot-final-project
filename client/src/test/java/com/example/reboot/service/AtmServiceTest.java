package com.example.reboot.service;

import com.example.reboot.dto.*;
import com.example.reboot.enums.Currency;
import com.example.reboot.exception.AtmException;
import com.example.reboot.service.impl.AtmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    AtmService atmService;

    @Value("${atm.server.url}")
    String url;

    @BeforeEach
    void setup() {
        atmService = new AtmServiceImpl(url, restTemplate);
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
                        .statusDesc("?????????? ?????????????? ??????????????")
                        .build())
                .card(cardDTO)
                .build();

        BalanceDTO balanceDTO = BalanceDTO.builder()
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .message(String.format("???????????? ???? ?????????? ???? %s", LocalDate.now()))
                .build();

        // when
        when(restTemplate.postForObject(url, rq, InfoByCardRs.class))
                .thenReturn(rs);

        // then
        assertEquals(balanceDTO, atmService.retrieveCardBalance(rq));
    }

    @ParameterizedTest
    @CsvSource({
            "1, '?????????? ???? ??????????????', '?????????? ???? ?????????????????????????? ?????? ???????????????????? ???????? ?????????????????????????? ??????????'",
            "2, '???????????? ???????????????? ??????-??????', '???????????? ???????????????? ??????-??????, ???????????????????? ?????? ??????'",
            "3, '???????????????????? ???????????? ?????? ?????????????????? ??????????????', '?????????????????? ???????????????????? ????????????'"
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
        when(restTemplate.postForObject(url, rq, InfoByCardRs.class))
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
        when(restTemplate.postForObject(url, rq, InfoByCardRs.class))
                .thenReturn(null);

        // then
        assertThrows(AtmException.class,
                () -> atmService.retrieveCardBalance(rq));
    }
}