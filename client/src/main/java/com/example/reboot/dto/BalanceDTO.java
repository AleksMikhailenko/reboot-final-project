package com.example.reboot.dto;

import com.example.reboot.enums.Currency;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class BalanceDTO {

    BigDecimal balance;
    Currency currency;
    String message;
}
