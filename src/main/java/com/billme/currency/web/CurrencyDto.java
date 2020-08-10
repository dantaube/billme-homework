package com.billme.currency.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDto {

    @JsonProperty("Code")
    private final String code;

    @JsonProperty("Num")
    private final int    number;

    @JsonProperty("E")
    private final int    scale;

    @JsonProperty("Currency")
    private final String name;

    public CurrencyDto(String code, int number, int scale, String name) {
        this.code = code;
        this.number = number;
        this.scale = scale;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

    public int getScale() {
        return scale;
    }

    public String getName() {
        return name;
    }

}
