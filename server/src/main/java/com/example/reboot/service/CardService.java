package com.example.reboot.service;

import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;

public interface CardService {

    /**
     * Получение информации по карте.
     *
     * @param cardRq объект типа {@link GetInfoByCardRs}
     * @return объект типа {@link GetInfoByCardRs}
     */
    GetInfoByCardRs getInfoByCard(GetInfoByCardRq cardRq);
}
