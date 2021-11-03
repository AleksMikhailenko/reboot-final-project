package com.example.reboot.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetInfoByCardRq {

    String cardNumber;
    String pin;
}
