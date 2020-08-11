package com.billme.currency.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billme.currency.registry.errors.InvalidCurrencyCodeException;

@Service
class CurrencyService {

    private CurrencyRegistry registry;

    CurrencyService(@Autowired CurrencyRegistry registry) {
        this.registry = registry;
    }

    CurrencyDto getCurrency(String code) {
        Currency currency = registry.getCurrency(code);
        if (currency == null) {
            throw new InvalidCurrencyCodeException(code);
        }
        return toDto(currency);
    }

    private CurrencyDto toDto(Currency currency) {
        return new CurrencyDto(currency.getCode(), currency.getNumber(), currency.getScale(), currency.getName());
    }

}
