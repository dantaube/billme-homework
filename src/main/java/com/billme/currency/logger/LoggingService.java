package com.billme.currency.logger;

import com.billme.currency.logger.LogEvent;
import com.billme.currency.logger.LogEventDto;
import com.billme.currency.logger.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoggingService {

    @Autowired
    private RequestLogger requestLogger;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS").withZone(ZoneId.systemDefault());

    public void logEvent(String currencyCode, String clientAddress) {
        requestLogger.logRequestAttempt(new LogEvent(currencyCode, clientAddress));
    }

    public List<LogEventDto> getLogEvents() {
        return requestLogger.
                getLogEvents().
                stream().
                map(logEvent -> toDto(logEvent)).
                collect(Collectors.toList());
    }

    private LogEventDto toDto(LogEvent logEvent) {
        Instant dateTime = Instant.ofEpochMilli(logEvent.getTimestamp());
        return new LogEventDto(formatter.format(dateTime), logEvent.getCurrencyCode(), logEvent.getClientAddress());
    }

}
