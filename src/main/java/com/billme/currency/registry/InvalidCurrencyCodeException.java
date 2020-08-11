package com.billme.currency.registry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidCurrencyCodeException extends RuntimeException {

    private static final long serialVersionUID = -755261975309649367L;

    public InvalidCurrencyCodeException(String code) {
        super("Invalid currency code: " + code);
    }
}
