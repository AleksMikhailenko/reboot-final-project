package com.example.reboot.service.impl;

import com.example.reboot.dto.CardDTO;
import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.entity.Card;
import com.example.reboot.enums.ProcessStatusCode;
import com.example.reboot.mapping.converters.CardDTOToGetInfoByCardRsConverter;
import com.example.reboot.mapping.converters.CardToCardDTOConverter;
import com.example.reboot.repository.CardRepository;
import com.example.reboot.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardToCardDTOConverter cardToCardDTOConverter;

    private final CardDTOToGetInfoByCardRsConverter cardDTOToGetInfoByCardRsConverter;

    @Override
    public GetInfoByCardRs getInfoByCard(GetInfoByCardRq cardRq) {
        Optional<Card> optionalCard = cardRepository
                .findCardByNumber(cardRq.getCardNumber());

        log.info("Card: " + optionalCard);

        CardDTO cardDTO;
        ProcessStatusCode processStatusCode;

        if (optionalCard.isPresent()) {
            if (isCorrectPin(cardRq.getPin(), optionalCard.get().getPin())) {
                cardDTO = cardToCardDTOConverter.convert(optionalCard.get());
                processStatusCode = ProcessStatusCode.SUCCESS;
            } else {
                cardDTO = null;
                processStatusCode = ProcessStatusCode.CARD_PIN_INCORRECT;
            }
        } else {
            cardDTO = null;
            processStatusCode = ProcessStatusCode.CARD_NOT_FOUND;
        }

        log.info("CardDTO: " + cardDTO);
        log.info("Status: " + processStatusCode);

        return cardDTOToGetInfoByCardRsConverter.convert(cardDTO, processStatusCode);
    }

    private boolean isCorrectPin(String pinCardRq, String pinFoundCard) {
        return pinCardRq.equals(pinFoundCard);
    }
}
