package com.example.reboot.controller;

import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/cards")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CardController {

    private final CardService cardService;

    @GetMapping("/{cardNumber}")
    public GetInfoByCardRs getInfoByCard(@PathVariable String cardNumber) {
        return cardService.getInfoByCard(cardNumber);
    }
}
