package com.example.reboot.service;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.dto.Status;
import com.example.reboot.entity.Card;
import com.example.reboot.enums.Currency;
import com.example.reboot.enums.ProcessStatusCode;
import com.example.reboot.mapping.converters.CardDTOToGetInfoByCardRsConverter;
import com.example.reboot.mapping.converters.CardToCardDTOConverter;
import com.example.reboot.repository.CardRepository;
import com.example.reboot.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    CardRepository cardRepository;

    @Mock
    CardToCardDTOConverter cardToCardDTOConverter;

    @Mock
    CardDTOToGetInfoByCardRsConverter cardDTOToGetInfoByCardRsConverter;

    CardService cardService;

    @BeforeEach
    void setup() {
        cardService = new CardServiceImpl(
                cardRepository,
                cardToCardDTOConverter,
                cardDTOToGetInfoByCardRsConverter
        );
    }

    @Test
    void shouldReturnSuccessStatusForGetCardTest() {
        // given
        GetInfoByCardRq rq = GetInfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("1234")
                .build();

        Card card = Card.builder()
                .id(1L)
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .pin("1234")
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();

        CardDTO cardDTO = CardDTO.builder()
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();

        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.SUCCESS.getId())
                        .statusDesc(ProcessStatusCode.SUCCESS.getDescription())
                        .build())
                .card(cardDTO)
                .build();

        // when
        when(cardRepository.findCardByNumber("1111222233331111")).thenReturn(Optional.of(card));
        when(cardToCardDTOConverter.convert(card)).thenReturn(cardDTO);
        when(cardDTOToGetInfoByCardRsConverter.convert(cardDTO, ProcessStatusCode.SUCCESS)).thenReturn(rs);

        // then
        assertEquals(rs, cardService.getInfoByCard(rq));
    }

    @Test
    void shouldReturnPinIncorrectStatusForGetCardTest() {
        // given
        GetInfoByCardRq rq = GetInfoByCardRq.builder()
                .cardNumber("1111222233331111")
                .pin("5678")
                .build();

        Card card = Card.builder()
                .id(1L)
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .pin("1234")
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();

        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.CARD_PIN_INCORRECT.getId())
                        .statusDesc(ProcessStatusCode.CARD_PIN_INCORRECT.getDescription())
                        .build())
                .card(null)
                .build();

        // when
        when(cardRepository.findCardByNumber("1111222233331111")).thenReturn(Optional.of(card));
        when(cardDTOToGetInfoByCardRsConverter.convert(null, ProcessStatusCode.CARD_PIN_INCORRECT)).thenReturn(rs);

        // then
        assertEquals(rs, cardService.getInfoByCard(rq));
    }

    @Test
    void shouldReturnNotFoundStatusForGetCardTest() {
        // given
        GetInfoByCardRq rq = GetInfoByCardRq.builder()
                .cardNumber("1111222233334444")
                .pin("1234")
                .build();

        GetInfoByCardRs rs = GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(ProcessStatusCode.CARD_NOT_FOUND.getId())
                        .statusDesc(ProcessStatusCode.CARD_NOT_FOUND.getDescription())
                        .build())
                .card(null)
                .build();

        // when
        when(cardRepository.findCardByNumber("1111222233334444")).thenReturn(Optional.empty());
        when(cardDTOToGetInfoByCardRsConverter.convert(null, ProcessStatusCode.CARD_NOT_FOUND)).thenReturn(rs);

        // then
        assertEquals(rs, cardService.getInfoByCard(rq));
    }
}