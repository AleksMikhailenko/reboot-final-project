package com.sbrf.reboot;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class Card {

    /**
     * Card identifier
     */
    long id;

    /**
     * Card number
     */
    String number;

    /**
     * Card balance
     */
    BigDecimal balance;

    /**
     * Card currency
     */
    Currency currency;

    /**
     * Card pin-code
     */
    String secureCode;

    /**
     * Card start date
     */
    LocalDate startDate;

    /**
     * Card end date
     */
    LocalDate endDate;
}