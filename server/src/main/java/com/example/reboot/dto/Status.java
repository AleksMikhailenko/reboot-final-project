package com.example.reboot.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Status {

    int statusCode;
    String statusDesc;
}
