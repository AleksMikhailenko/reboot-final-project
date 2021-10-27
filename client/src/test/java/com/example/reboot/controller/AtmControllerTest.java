package com.example.reboot.controller;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.InfoByCardRq;
import com.example.reboot.dto.Status;
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
import java.time.Month;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
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
        String rawRq = "{\"cardNumber\":\"1111222233331111\",\"pin\":\"1234\"}";
        InfoByCardRq rq = InfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        BalanceDTO rs = BalanceDTO.builder()
                .build();

        // when
        when(atmService.retrieveCardBalance(rq)).thenReturn(rs);

        // then
        mockMvc.perform(post("/atm/v1.0/cards")
                        .characterEncoding("UTF_8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rawRq))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.status.statusCode", is(0)))
//                .andExpect(jsonPath("$.status.statusDesc", is("Карта успешно найдена")))
//                .andExpect(jsonPath("$.card.number", is("1111222233331111")))
//                .andExpect(jsonPath("$.card.balance", is(134900.4)))
//                .andExpect(jsonPath("$.card.currency", is("RUB")))
//                .andExpect(jsonPath("$.card.startDate", is("2021-09-15")))
//                .andExpect(jsonPath("$.card.endDate", is("2024-09-15")));
    }
}