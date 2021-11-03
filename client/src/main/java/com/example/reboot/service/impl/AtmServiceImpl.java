package com.example.reboot.service.impl;

import com.example.reboot.dto.BalanceDTO;
import com.example.reboot.dto.InfoByCardRq;
import com.example.reboot.dto.InfoByCardRs;
import com.example.reboot.exception.AtmException;
import com.example.reboot.service.AtmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
@Service
public class AtmServiceImpl implements AtmService {

    private static final String MESSAGE_INFO_BALANCE = "Баланс по карте на %s";
    private static final String MESSAGE_CARD_NOT_VALID = "Карта не действительна или завершился срок использования карты";
    private static final String MESSAGE_PIN_IS_NOT_VALID = "Введен неверный пин-код, попробуйте ещё раз";
    private static final String MESSAGE_ERROR = "Произошла внутренняя ошибка";

    private final String url;
    private final RestTemplate restTemplate;

    @Autowired
    public AtmServiceImpl(@Value("${atm.server.url}") String url,
                          RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public BalanceDTO retrieveCardBalance(InfoByCardRq cardRq) {
        log.info("InfoByCardRq: " + cardRq);

        InfoByCardRs source = restTemplate.postForObject(url, cardRq, InfoByCardRs.class);
        log.info("InfoByCardRs: " + source);

        if (source == null) {
            throw new AtmException("Не удалось получить информацию по карте");
        }

        return getInfoBalance(source);
    }

    private BalanceDTO getInfoBalance(InfoByCardRs rs) {
        int code = rs.getStatus().getStatusCode();
        BalanceDTO balanceDTO;
        log.info("StatusCode: " + code);

        switch (code) {
            case 0:
                balanceDTO = BalanceDTO.builder()
                        .balance(rs.getCard().getBalance())
                        .currency(rs.getCard().getCurrency())
                        .message(String.format(MESSAGE_INFO_BALANCE, LocalDate.now()))
                        .build();
                break;
            case 1:
                balanceDTO = BalanceDTO.builder()
                        .message(MESSAGE_CARD_NOT_VALID)
                        .build();
                break;
            case 2:
                balanceDTO = BalanceDTO.builder()
                        .message(MESSAGE_PIN_IS_NOT_VALID)
                        .build();
                break;
            default:
                balanceDTO = BalanceDTO.builder()
                        .message(MESSAGE_ERROR)
                        .build();
                break;
        }
        log.info("BalanceDTO: " + balanceDTO);

        return balanceDTO;
    }
}
