package com.example.reboot.repository;

import com.example.reboot.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    /**
     * Поиск карты по номеру.
     *
     * @param number номер карты
     * @return объект типа {@link Card} обернутый в {@link Optional}
     */
    Optional<Card> findCardByNumber(String number);
}
