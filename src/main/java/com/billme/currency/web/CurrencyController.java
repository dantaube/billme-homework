package com.billme.currency.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.billme.currency.logger.RequestLogger;

@RestController
class CurrencyController {

    @Autowired
    private CurrencyService service;

    @Autowired
    private RequestLogger requestLogger;
    
    @GetMapping("currencies/{code}")
    public CurrencyDto getCurrency(@PathVariable String code, HttpServletRequest httpRequest) {
        requestLogger.logRequestAttempt(code, httpRequest.getRemoteAddr());
        return service.getCurrency(code);
    }

}
