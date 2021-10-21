package com.example.reboot.controller;

import com.example.reboot.dto.Card;
import com.example.reboot.service.ATMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/atm/v1.0")
public class AtmController {

    private final ATMService atmService;

    @PostMapping("/cards/{cardNumber}")
    public Card retrieveCardBalance(@PathVariable String cardNumber,
                                    @RequestBody String pin) {
        log.info("Received a request for a card number: " + cardNumber);
        return atmService.retrieveCardBalance(cardNumber, pin);
    }
}
