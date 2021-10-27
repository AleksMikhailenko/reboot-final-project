package com.example.reboot.controller;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.InfoByCardRq;
import com.example.reboot.service.AtmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/atm/v1.0")
public class AtmController {

    private final AtmService atmService;

    @PostMapping(path = "/cards",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BalanceDTO retrieveCardBalance(@RequestBody InfoByCardRq cardRq) {
        log.info("CardRq: " + cardRq);
        return atmService.retrieveCardBalance(cardRq);
    }
}
