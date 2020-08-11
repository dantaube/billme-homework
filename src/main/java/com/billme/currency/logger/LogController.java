package com.billme.currency.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogController {

    private LoggingService loggingService;

    public LogController(@Autowired LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("log")
    public ModelAndView getLog() {
        ModelAndView mav = new ModelAndView("log");
        mav.addObject("logEvents", loggingService.getLogEvents());
        return mav;
    }

}
