package com.example.reboot.controller;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.dto.Status;
import com.example.reboot.enums.Currency;
import com.example.reboot.enums.ProcessStatusCode;
import com.example.reboot.service.CardService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @MockBean
    CardService cardService;

    @Autowired
    MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldBeCorrectlyStructuredResponseBySrvGetInfoByCardTest() {
        // given
        String rawRq = "{\"cardNumber\":\"1111222233331111\",\"pin\":\"1234\"}";
        GetInfoByCardRq rq = GetInfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.SUCCESS.getId())
                        .statusDesc(ProcessStatusCode.SUCCESS.getDescription())
                        .build())
                .card(CardDTO.builder()
                        .number("1111222233331111")
                        .balance(BigDecimal.valueOf(134900.4))
                        .currency(Currency.RUB)
                        .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                        .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                        .build())
                .build();

        // when
        when(cardService.getInfoByCard(rq)).thenReturn(rs);

        // then
        mockMvc.perform(post("/api/v1.0/cards")
                        .characterEncoding("UTF_8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rawRq))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status.statusCode", is(0)))
                .andExpect(jsonPath("$.status.statusDesc", is("Карта успешно найдена")))
                .andExpect(jsonPath("$.card.number", is("1111222233331111")))
                .andExpect(jsonPath("$.card.balance", is(134900.4)))
                .andExpect(jsonPath("$.card.currency", is("RUB")))
                .andExpect(jsonPath("$.card.startDate", is("2021-09-15")))
                .andExpect(jsonPath("$.card.endDate", is("2024-09-15")));
    }
}