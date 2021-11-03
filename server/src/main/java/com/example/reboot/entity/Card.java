package com.example.reboot.entity;

import com.example.reboot.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    /**
     * Идентификатор карты
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Номер карты
     */
    @Column(name = "number")
    String number;

    /**
     * Баланс
     */
    @Column(name = "balance")
    BigDecimal balance;

    /**
     * Валюта
     */
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

    /**
     * Пин-код
     */
    @Column(name = "pin")
    String pin;

    /**
     * Дата начала действия карты
     */
    @Column(name = "start_date")
    LocalDate startDate;

    /**
     * Дата окончания действия карты
     */
    @Column(name = "end_date")
    LocalDate endDate;
}
