package com.example.reboot.mapping;

/**
 * Интерфейс для конвертации из одного объекта в другой
 */
public interface Converter<S, D> {

    /**
     * Конвертация объекта типа S в объект типа D (с созданием нового экземпляра).
     *
     * @param source исходный объект типа S
     * @return созданный объект типа D
     */
    default D convert(S source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Конвертация объекта типа S в объект типа D (с созданием нового экземпляра) с учетом дополнительных параметров.
     *
     * @param source  исходный объект типа S
     * @param options дополнительные параметры
     * @return созданный объект типа D
     */
    default D convert(S source, Object... options) {
        throw new UnsupportedOperationException();
    }
}
