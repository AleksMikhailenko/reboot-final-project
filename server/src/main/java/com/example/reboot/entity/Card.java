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
     * Card identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Card number
     */
    @Column(name = "number")
    String number;

    /**
     * Card balance
     */
    @Column(name = "balance")
    BigDecimal balance;

    /**
     * Card currency
     */
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

    /**
     * Card pin-code
     */
    @Column(name = "pin")
    String pin;

    /**
     * Card start date
     */
    @Column(name = "start_date")
    LocalDate startDate;

    /**
     * Card end date
     */
    @Column(name = "end_date")
    LocalDate endDate;
}
