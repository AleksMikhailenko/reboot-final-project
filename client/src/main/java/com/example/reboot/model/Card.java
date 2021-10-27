package com.example.reboot.model;

import com.example.reboot.enums.Currency;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Card {

    /**
     * Номер карты
     */
    String number;

    /**
     * Баланс карты
     */
    BigDecimal balance;

    /**
     * Валюта карты
     */
    Currency currency;
}