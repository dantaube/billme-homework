package com.billme.currency.logger;

public class LogEventDto {

    private final String dateTime;

    private final String currencyCode;

    private final String clientAddress;

    public LogEventDto(String dateTime, String currencyCode, String clientAddress) {
        this.dateTime = dateTime;
        this.currencyCode = currencyCode;
        this.clientAddress = clientAddress;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getClientAddress() {
        return clientAddress;
    }
}
