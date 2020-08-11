package com.billme.currency.registry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, String> {
    
}
