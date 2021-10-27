package com.example.reboot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ATM {

    /**
     * Идентификатор банкомата
     */
    private long id;

    /**
     * Вставленная карта в банкомат
     */
    private Card card;
}