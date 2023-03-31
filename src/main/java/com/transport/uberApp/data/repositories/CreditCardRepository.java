package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
