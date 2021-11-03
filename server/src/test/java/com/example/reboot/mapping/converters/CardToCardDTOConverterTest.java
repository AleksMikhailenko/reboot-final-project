package com.example.reboot.mapping.converters;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.entity.Card;
import com.example.reboot.enums.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardToCardDTOConverterTest {

    CardToCardDTOConverter cardToCardDTOConverter;
    Card card;

    @BeforeEach
    void setup() {
        cardToCardDTOConverter = new CardToCardDTOConverter();
        card = Card.builder()
                .id(1L)
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .pin("1234")
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();
    }

    @Test
    void shouldSuccessConvertFromCardToCardDTOTest() {
        // given
        CardDTO cardDTO = CardDTO.builder()
                .number("1111222233331111")
                .balance(BigDecimal.valueOf(134900.4))
                .currency(Currency.RUB)
                .startDate(LocalDate.of(2021, Month.SEPTEMBER, 15))
                .endDate(LocalDate.of(2024, Month.SEPTEMBER, 15))
                .build();

        // then
        assertEquals(cardDTO, cardToCardDTOConverter.convert(card));
    }

    @Test
    void shouldThrowNPEWhenCardIsNullTest() {
        assertThrows(NullPointerException.class, () -> cardToCardDTOConverter.convert(null));
    }

    @Test
    void shouldThrowUOEByUnusedMethodTest() {
        assertThrows(UnsupportedOperationException.class,
                () -> cardToCardDTOConverter.convert(card, new Object[0]));
    }
}