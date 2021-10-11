package com.sbrf.reboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    Card rubCard;
    Card usdCard;

    @BeforeEach
    void setUp() {
        rubCard = Card.builder()
                .id(1L)
                .number("123456789")
                .balance(BigDecimal.valueOf(2500))
                .currency(Currency.RUB)
                .secureCode("1234")
                .startDate(LocalDate.of(2020, Month.AUGUST, 23))
                .endDate(LocalDate.of(2023, Month.AUGUST, 23))
                .build();
        usdCard = Card.builder()
                .id(2L)
                .number("987654321")
                .balance(BigDecimal.valueOf(10_000))
                .currency(Currency.USD)
                .secureCode("0987")
                .startDate(LocalDate.of(2021, Month.MARCH, 5))
                .endDate(LocalDate.of(2024, Month.MARCH, 5))
                .build();
    }

    // insert card
    @Test
    void shouldInsertCardToATM() {

        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(rubCard);

        assertEquals(rubCard, atm.getCard());
    }

    @Test
    void shouldChangeEmptyCardSlotAfterInsertCard() {
        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(rubCard);

        assertFalse(atm.isEmptyCardSlot());
    }

    @Test
    void shouldThrowIAEWhenCardSlotNotEmpty() {
        ATM atm = ATM.builder()
                .id(1L)
                .build();

        assertThrows(IllegalArgumentException.class, () -> atm.retrieveCard(rubCard));
    }

    @Test
    void shouldThrowIAEWhenCardNotNull() {
        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .card(rubCard)
                .build();

        assertThrows(IllegalArgumentException.class, () -> atm.retrieveCard(rubCard));
    }

    // check secure code
    @Test
    void shouldCheckSecureCode() {

        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(rubCard);

        assertTrue(atm.checkSecureCode(rubCard.getSecureCode()));
    }

    // retrieve card balance
    @Test
    void shouldRetrieveCardBalance() {
        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(usdCard);

        assertEquals(usdCard.getBalance(), atm.retrieveCardBalance());
    }

    // return card
    @Test
    void shouldReturnCard() {

        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(usdCard);

        assertEquals(usdCard, atm.returnCard());
    }

    @Test
    void shouldChangeEmptyCardSlotAfterReturnCard() {

        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .build();

        atm.retrieveCard(usdCard);
        atm.returnCard();

        assertTrue(atm.isEmptyCardSlot());
    }

    @Test
    void shouldThrowIAEWhenReturnCardEmpty() {
        ATM atm = ATM.builder()
                .id(1L)
                .build();

        assertThrows(IllegalArgumentException.class, atm::returnCard);
    }

    @Test
    void shouldThrowIAEWhenReturnCardEmptyCardSlot() {
        ATM atm = ATM.builder()
                .id(1L)
                .emptyCardSlot(true)
                .card(rubCard)
                .build();

        assertThrows(IllegalArgumentException.class, atm::returnCard);
    }
}