package com.billme.currency.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestLogger {

    private static final Logger LOG = LoggerFactory.getLogger(RequestLogger.class);

    @Autowired
    private RequestAttemptDao       dao;

    public void logRequestAttempt(String currencyCode, String clientAddress) {
        RequestAttempt log = new RequestAttempt(currencyCode, clientAddress);
        LOG.info("New client request: {}", log);
        dao.save(log);
    }

}
