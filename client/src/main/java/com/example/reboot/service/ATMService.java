package com.example.reboot.service;

import com.example.reboot.dto.Card;

public interface ATMService {

    Card retrieveCardBalance(String cardNumber, String secureCode);
}
