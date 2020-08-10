package com.billme.currency.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CurrencyController {

    @Autowired
    private CurrencyService service;

    @GetMapping("currencies/{code}")
    public CurrencyDto getCurrency(@PathVariable String code) {
        return service.getCurrency(code);
    }

}
