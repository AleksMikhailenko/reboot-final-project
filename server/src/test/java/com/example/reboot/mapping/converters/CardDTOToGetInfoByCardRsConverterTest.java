package com.example.reboot.mapping.converters;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.dto.Status;
import com.example.reboot.enums.Currency;
import com.example.reboot.enums.ProcessStatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardDTOToGetInfoByCardRsConverterTest {

    CardDTOToGetInfoByCardRsConverter cardDTOToGetInfoByCardRsConverter;
    CardDTO cardDTO;

    @BeforeEach
    void setup() {
        cardDTOToGetInfoByCardRsConverter = new CardDTOToGetInfoByCardRsConverter();
        cardDTO = CardDTO.builder()
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();
    }

    @Test
    void shouldSuccessStatusConvertFromCardDTOToGetInfoByCardRsTest() {
        // given
        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.SUCCESS.getId())
                        .statusDesc(ProcessStatusCode.SUCCESS.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // then
        assertEquals(rs, cardDTOToGetInfoByCardRsConverter
                .convert(cardDTO, ProcessStatusCode.SUCCESS));
    }

    @Test
    void shouldNotFoundStatusConvertFromCardDTOToGetInfoByCardRsTest() {
        // given
        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.CARD_NOT_FOUND.getId())
                        .statusDesc(ProcessStatusCode.CARD_NOT_FOUND.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // then
        assertEquals(rs, cardDTOToGetInfoByCardRsConverter
                .convert(cardDTO, ProcessStatusCode.CARD_NOT_FOUND));
    }

    @Test
    void shouldPinIncorrectStatusConvertFromCardDTOToGetInfoByCardRsTest() {
        // given
        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.CARD_PIN_INCORRECT.getId())
                        .statusDesc(ProcessStatusCode.CARD_PIN_INCORRECT.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // then
        assertEquals(rs, cardDTOToGetInfoByCardRsConverter
                .convert(cardDTO, ProcessStatusCode.CARD_PIN_INCORRECT));
    }

    @Test
    void shouldExceptionStatusConvertFromCardDTOToGetInfoByCardRsTest() {
        // given
        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.EXCEPTION.getId())
                        .statusDesc(ProcessStatusCode.EXCEPTION.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // then
        assertEquals(rs, cardDTOToGetInfoByCardRsConverter
                .convert(cardDTO, new Object[0]));
    }

    @Test
    void shouldExceptionStatusConvertWhenOptionsIsNullTest() {
        // given
        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.EXCEPTION.getId())
                        .statusDesc(ProcessStatusCode.EXCEPTION.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // then
        assertEquals(rs, cardDTOToGetInfoByCardRsConverter
                .convert(cardDTO, null));
    }

    @Test
    void shouldThrowUOEByUnusedMethodTest() {
        assertThrows(UnsupportedOperationException.class,
                () -> cardDTOToGetInfoByCardRsConverter.convert(cardDTO));
    }
}