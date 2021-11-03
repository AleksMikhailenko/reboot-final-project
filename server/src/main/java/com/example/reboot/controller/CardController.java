package com.example.reboot.controller;

import com.example.reboot.dto.GetInfoByCardRq;
import com.example.reboot.dto.GetInfoByCardRs;
import com.example.reboot.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1.0/cards")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CardController {

    private final CardService cardService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GetInfoByCardRs getInfoByCard(@RequestBody GetInfoByCardRq getInfoByCardRq) {
        log.info("Request obj: " + getInfoByCardRq);
        return cardService.getInfoByCard(getInfoByCardRq);
    }
}
