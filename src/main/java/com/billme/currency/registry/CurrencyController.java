package com.billme.currency.registry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.billme.currency.logger.LoggingService;
import com.billme.currency.registry.errors.CurrencyRegistryError;

@RestController
public class CurrencyController {

    private CurrencyService currencyService;

    private LoggingService  loggingService;

    public CurrencyController(@Autowired CurrencyService currencyService, @Autowired LoggingService loggingService) {
        this.currencyService = currencyService;
        this.loggingService = loggingService;
    }

    @GetMapping("currencies/{code}")
    public CurrencyDto getCurrency(@PathVariable String code, HttpServletRequest httpRequest) {
        CurrencyDto currency = currencyService.getCurrency(code);
        loggingService.logEvent(code, httpRequest.getRemoteAddr());
        return currency;
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception exception, HttpServletRequest httpRequest) throws Exception {
        loggingService.logEvent(extractCurrencyCode(httpRequest.getRequestURI()), httpRequest.getRemoteAddr());
        if (exception instanceof CurrencyRegistryError) {
            throw exception;
        }
        return buildServerErrorResponse(exception.getMessage());
    }

    private String extractCurrencyCode(String uri) {
        return uri.substring(uri.lastIndexOf('/') + 1);
    }

    private ResponseEntity<Object> buildServerErrorResponse(String error) {
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
