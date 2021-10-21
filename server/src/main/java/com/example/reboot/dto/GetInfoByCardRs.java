package com.example.reboot.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetInfoByCardRs {

    Status status;
    CardDTO card;
}
