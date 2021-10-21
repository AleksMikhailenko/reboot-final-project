package com.example.reboot.service.impl;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.entity.Card;
import com.example.reboot.mapping.converters.CardDTOToGetInfoByCardRsConverter;
import com.example.reboot.mapping.converters.CardToCardDTOConverter;
import com.example.reboot.repository.CardRepository;
import com.example.reboot.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardToCardDTOConverter cardToCardDTOConverter;

    private final CardDTOToGetInfoByCardRsConverter cardDTOToGetInfoByCardRsConverter;

    @Override
    public GetInfoByCardRs getInfoByCard(String cardNumber) {
        Optional<Card> optionalCard = cardRepository.findCardByNumber(cardNumber);

        Card card = optionalCard.orElse(null);
        CardDTO cardDTO = card != null
                ? cardToCardDTOConverter.convert(card)
                : null;

        return cardDTOToGetInfoByCardRsConverter.convert(cardDTO);
    }
}
