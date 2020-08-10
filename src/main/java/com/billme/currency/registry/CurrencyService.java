package com.billme.currency.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CurrencyService {

    @Autowired
    private CurrencyRegistry registry;

    CurrencyDto getCurrency(String code) {
        Currency currency = registry.getCurrency(code);
        if(currency == null) {
            throw new InvalidCurrencyCodeException(code);
        }
        return toDto(currency);
    }

    private CurrencyDto toDto(Currency currency) {
        return new CurrencyDto(currency.getCode(), currency.getNumber(), currency.getScale(), currency.getName());
    }

}
