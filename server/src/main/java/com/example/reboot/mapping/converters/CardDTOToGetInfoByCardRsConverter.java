package com.example.reboot.mapping.converters;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.dto.Status;
import com.example.reboot.enums.StatusCode;
import com.example.reboot.mapping.Converter;
import org.springframework.stereotype.Component;

@Component
public class CardDTOToGetInfoByCardRsConverter implements Converter<CardDTO, GetInfoByCardRs> {

    @Override
    public GetInfoByCardRs convert(CardDTO source) {
        return GetInfoByCardRs.builder()
                .status(Status.builder()
                        .statusCode(source != null
                                ? StatusCode.SUCCESS.getId()
                                : StatusCode.CARD_NOT_FOUND.getId())
                        .statusDesc(source != null
                                ? StatusCode.SUCCESS.getDescription()
                                : StatusCode.CARD_NOT_FOUND.getDescription())
                        .build())
                .card(source)
                .build();
    }
}
