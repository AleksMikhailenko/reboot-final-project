package com.example.reboot.service;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.InfoByCardRq;

public interface AtmService {

    BalanceDTO retrieveCardBalance(InfoByCardRq cardRq);
}
