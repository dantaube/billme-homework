package com.billme.currency.webservice;

import com.billme.currency.Currency;
import com.billme.currency.CurrencyRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
