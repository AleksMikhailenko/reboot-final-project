package com.example.reboot.service.impl;

import com.example.reboot.dto.Card;
import com.example.reboot.dto.InfoByCard;
import com.example.reboot.exception.ATMException;
import com.example.reboot.service.ATMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ATMServiceImpl implements ATMService {

    private final RestTemplate restTemplate;

    @Override
    public Card retrieveCardBalance(String cardNumber, String pin) {
        InfoByCard source = restTemplate.getForObject("http://localhost:9090/api/v1.0/cards/{cardNumber}", InfoByCard.class, cardNumber);

        if (source == null) {
            throw new ATMException("Не удалось получить информацию по карте");
        }

        return source.getCard() == null
                ? Card.builder().build()
                : Card.builder()
                .number(source.getCard().getNumber())
                .balance(source.getCard().getBalance())
                .currency(source.getCard().getCurrency())
                .build();
    }
}
