package com.example.reboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoByCard {

    Status status;
    CardDTO card;
}
