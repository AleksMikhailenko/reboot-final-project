package com.example.reboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoByCardRs {

    Status status;
    CardDTO card;
}
