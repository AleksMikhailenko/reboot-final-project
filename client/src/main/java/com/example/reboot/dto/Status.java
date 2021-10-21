package com.example.reboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {

    private int statusCode;
    private String statusDesc;
}
