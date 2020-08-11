package com.billme.currency.registry.errors;

public class CurrencyRegistryError extends RuntimeException {

    private static final long serialVersionUID = -911885535519453309L;

    public CurrencyRegistryError(String message) {
        super(message);
    }

    public CurrencyRegistryError(String message, Throwable e) {
        super(message, e);
    }

}
