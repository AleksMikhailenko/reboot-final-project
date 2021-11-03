package com.example.reboot.dto;

import com.example.reboot.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CardDTO {

    String number;
    BigDecimal balance;
    Currency currency;
    LocalDate startDate;
    LocalDate endDate;
}
