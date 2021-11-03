package com.example.reboot.service;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.InfoByCardRq;

public interface AtmService {

    /**
     * Получение баланса по карте.
     *
     * @param cardRq объект запроса типа {@link InfoByCardRq}
     * @return объект ответа типа {@link BalanceDTO}
     */
    BalanceDTO retrieveCardBalance(InfoByCardRq cardRq);
}
