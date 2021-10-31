package com.example.reboot.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.reboot.security.AppPermission.ATM_READ;
import static com.example.reboot.security.AppPermission.ATM_WRITE;

@Getter
@AllArgsConstructor
public enum AppRole {

    ATM(new HashSet<>(Arrays.asList(ATM_READ, ATM_WRITE)));

    private final Set<AppPermission> permissions;
}
