package com.billme.currency;

import com.billme.currency.webservice.InvalidCurrencyCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRegistry {

    @Autowired
    private CurrencyDao dao;

    public Currency getCurrency(String code) {
        return dao.getCurrency(code);
    }

}
