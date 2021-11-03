package com.example.reboot.controller;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.InfoByCardRq;
import com.example.reboot.enums.Currency;
import com.example.reboot.service.AtmService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmController.class)
class AtmControllerTest {

    @MockBean
    AtmService atmService;

    @Autowired
    MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldBeCorrectlyStructuredResponseBySrvGetInfoByCardTest() {
        // given
        String message = String.format("Баланс по карте на %s", LocalDate.now());
        String rawRq = "{\"cardNumber\":\"1111222233331111\",\"pin\":\"1234\"}";
        InfoByCardRq rq = InfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        BalanceDTO rs = BalanceDTO.builder()
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .message(message)
                .build();

        // when
        when(atmService.retrieveCardBalance(rq)).thenReturn(rs);

        // then
        mockMvc.perform(post("/atm/v1.0/cards")
                        .characterEncoding("UTF_8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rawRq))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(134900.4)))
                .andExpect(jsonPath("$.currency", is("RUB")))
                .andExpect(jsonPath("$.message", is(message)));
    }
}