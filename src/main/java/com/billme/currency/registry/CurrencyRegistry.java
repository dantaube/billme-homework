package com.billme.currency.registry;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRegistry.class);

    @Autowired
    private CurrencyDao         dao;

    @Autowired
    private CurrencyParser      parser;

    @PostConstruct
    void load() throws IOException {
        parser.parse().forEach(currency -> dao.save(currency));
        LOG.info("Total currencies loaded: {}", dao.count());
    }

    public Currency getCurrency(String code) {
        if (code == null) {
            return null;
        }
        return dao.findById(code).orElse(null);
    }

}
