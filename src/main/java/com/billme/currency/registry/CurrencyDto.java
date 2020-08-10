package com.billme.currency.registry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDto {

    @JsonProperty("Code")
    private final String code;

    @JsonProperty("Num")
    private final Integer    number;

    @JsonProperty("E")
    private final Integer scale;

    @JsonProperty("Currency")
    private final String name;

    public CurrencyDto(String code, Integer number, Integer scale, String name) {
        this.code = code;
        this.number = number;
        this.scale = scale;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getScale() {
        return scale;
    }

    public String getName() {
        return name;
    }

}
