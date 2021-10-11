package com.sbrf.reboot;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Builder
public class ATM {

    /**
     * Identifier ATM
     */
    private long id;

    /**
     * Parameter showing that the card is in the ATM
     */
    private boolean emptyCardSlot;

    /**
     * The card inside the ATM
     */
    private Card card;

    public void insertCard(Card card) {
        Objects.requireNonNull(card);
        if (this.card != null || !this.emptyCardSlot) {
            // todo: заменить на бизнес-исключение
            throw new IllegalArgumentException("The ATM already contains a card");
        }

        this.card = card;
        this.emptyCardSlot = false;
    }

    public boolean checkSecureCode(String code) {
        // todo: валидацию перевести на модуль сервера
        return code.equals(card.getSecureCode());
    }

    public BigDecimal retrieveCardBalance() {
        // todo: получение баланса будет реализовано в модуле сервера
        return card.getBalance();
    }

    public Card returnCard() {
        if (card == null || emptyCardSlot) {
            // todo: заменить на бизнес-исключение
            throw new IllegalArgumentException("The ATM not contains a card");
        }
        Card returnedCard = card;
        card = null;
        emptyCardSlot = true;
        return returnedCard;
    }
}