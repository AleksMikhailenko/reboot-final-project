package com.example.reboot.mapping;

public interface Converter<S, D> {

    D convert(S source);
}
