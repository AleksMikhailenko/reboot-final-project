package com.example.reboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

    SUCCESS(0, "Карта успешно найдена"),
    CARD_NOT_FOUND(1, "Карта не найдена");

    private final int id;
    private final String description;
}
