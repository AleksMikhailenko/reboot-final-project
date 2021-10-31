package com.example.reboot.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppPermission {

    ATM_READ("atm:read"),
    ATM_WRITE("atm:write");

    private final String permission;
}
