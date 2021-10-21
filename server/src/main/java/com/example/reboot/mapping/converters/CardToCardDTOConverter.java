package com.example.reboot.mapping.converters;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.entity.Card;
import com.example.reboot.mapping.Converter;
import org.springframework.stereotype.Component;

@Component
public class CardToCardDTOConverter implements Converter<Card, CardDTO> {

    @Override
    public CardDTO convert(Card source) {
        return CardDTO.builder()
                .number(source.getNumber())
                .balance(source.getBalance())
                .currency(source.getCurrency())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .build();
    }
}
