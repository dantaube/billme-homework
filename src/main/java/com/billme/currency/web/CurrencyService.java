package com.billme.currency.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billme.currency.registry.Currency;
import com.billme.currency.registry.CurrencyRegistry;

@Service
class CurrencyService {

    @Autowired
    private CurrencyRegistry registry;

    CurrencyDto getCurrency(String code) {
        if(code == null) {
            throw new InvalidCurrencyCodeException(null);
        }
        Currency currency = registry.getCurrency(code);
        if(currency == null) {
            throw new InvalidCurrencyCodeException(code);
        }
        return toDto(currency);
    }

    protected CurrencyDto toDto(Currency currency) {
        return new CurrencyDto(currency.getCode(), currency.getNumber(), currency.getScale(), currency.getName());
    }

}
