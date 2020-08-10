package com.billme.currency.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestLogger {

    private static final Logger LOG = LoggerFactory.getLogger(RequestLogger.class);

    @Autowired
    private LogEventDao dao;

    public void logRequestAttempt(LogEvent logEvent) {
        LOG.info("New client request: {}", logEvent);
        dao.save(logEvent);
    }

    public List<LogEvent> getLogEvents() {
        return dao.findAllByOrderByIdAsc();
    }

}
