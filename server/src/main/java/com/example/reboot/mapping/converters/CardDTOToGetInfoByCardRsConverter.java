package com.example.reboot.mapping.converters;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.dto.Status;
import com.example.reboot.enums.ProcessStatusCode;
import com.example.reboot.mapping.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CardDTOToGetInfoByCardRsConverter implements Converter<CardDTO, GetInfoByCardRs> {

    @Override
    public GetInfoByCardRs convert(CardDTO source, Object... options) {
        ProcessStatusCode processStatusCode;

        if (options != null && options.length != 0) {
            processStatusCode = Stream.of(options)
                    .filter(ProcessStatusCode.class::isInstance)
                    .map(ProcessStatusCode.class::cast)
                    .findFirst()
                    .orElse(ProcessStatusCode.EXCEPTION);
        } else {
            processStatusCode = ProcessStatusCode.EXCEPTION;
        }

        return GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(processStatusCode.getId())
                        .statusDesc(processStatusCode.getDescription())
                        .build())
                .card(source)
                .build();
    }
}
