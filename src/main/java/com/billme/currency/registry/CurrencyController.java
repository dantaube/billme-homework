package com.billme.currency.registry;

import javax.servlet.http.HttpServletRequest;

import com.billme.currency.logger.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @Autowired
    private LoggingService loggingService;
    
    @GetMapping("currencies/{code}")
    public CurrencyDto getCurrency(@PathVariable String code, HttpServletRequest httpRequest) {
        loggingService.logEvent(code, httpRequest.getRemoteAddr());
        return service.getCurrency(code);
    }

    @GetMapping("log")
    public ModelAndView getLog() {
        ModelAndView mav = new ModelAndView("log");
        System.out.println(loggingService.getLogEvents().size());
        mav.addObject("logEvents", loggingService.getLogEvents());
        return mav;
    }

}
