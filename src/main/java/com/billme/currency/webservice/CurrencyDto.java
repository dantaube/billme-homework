package com.billme.currency.webservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class CurrencyDto {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Num")
    private int number;

    @JsonProperty("E")
    private int scale;

    @JsonProperty("Currency")
    private String name;

    public CurrencyDto(String code, int number, int scale, String name) {
        this.code = code;
        this.number = number;
        this.scale = scale;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
