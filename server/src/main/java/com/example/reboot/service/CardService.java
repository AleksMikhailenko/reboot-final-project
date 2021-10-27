package com.example.reboot.service;

import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;

public interface CardService {

    GetInfoByCardRs getInfoByCard(GetInfoByCardRq cardRq);
}
