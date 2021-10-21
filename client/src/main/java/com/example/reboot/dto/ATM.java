package com.example.reboot.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ATM {

    /**
     * Identifier ATM
     */
    private long id;

    /**
     * Parameter showing that the card is in the ATM
     */
    private boolean emptyCardSlot;

    /**
     * The card inside the ATM
     */
    private Card card;
}