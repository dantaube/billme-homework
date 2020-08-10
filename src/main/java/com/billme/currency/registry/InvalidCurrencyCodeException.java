package com.billme.currency.registry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidCurrencyCodeException extends RuntimeException {

    public InvalidCurrencyCodeException(String code) {
        super("Invalid currency code: " + code);
    }
}
