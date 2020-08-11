package com.billme.currency.registry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.billme.currency.logger.LoggingService;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @Autowired
    private LoggingService  loggingService;

    @GetMapping("currencies/{code}")
    public CurrencyDto getCurrency(@PathVariable String code, HttpServletRequest httpRequest) {
        loggingService.logEvent(code, httpRequest.getRemoteAddr());
        return service.getCurrency(code);
    }

    @ExceptionHandler
    public void handleException(Exception exception, HttpServletRequest httpRequest) throws Exception {
        loggingService.logEvent(extractCurrencyCode(httpRequest.getRequestURI()), httpRequest.getRemoteAddr());
        throw new Exception(exception);
    }

    private String extractCurrencyCode(String uri) {
        return uri.substring(uri.lastIndexOf('/') + 1);
    }

}
