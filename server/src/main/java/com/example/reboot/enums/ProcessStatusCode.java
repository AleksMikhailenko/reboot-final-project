package com.example.reboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessStatusCode {

    SUCCESS(0, "Карта успешно найдена"),
    CARD_NOT_FOUND(1, "Карта не найдена"),
    CARD_PIN_INCORRECT(2, "Введен неверный пин-код"),
    EXCEPTION(100, "Внутренняя ошибка при обработке запроса");

    private final int id;
    private final String description;
}
