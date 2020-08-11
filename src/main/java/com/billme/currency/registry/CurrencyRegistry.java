package com.billme.currency.registry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billme.currency.registry.errors.CurrencyRegistryError;

@Service
public class CurrencyRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRegistry.class);

    private CurrencyDao         dao;

    private CurrencyParser      parser;

    public CurrencyRegistry(@Autowired CurrencyDao dao, @Autowired CurrencyParser parser) {
        this.dao = dao;
        this.parser = parser;
    }

    @PostConstruct
    void load() throws CurrencyRegistryError {
        LOG.info("Loading currencies from Wiki page...");
        parser.readWikiPage().forEach(currency -> dao.save(currency));
        LOG.info("Total currencies loaded: {}", dao.count());
    }

    public Currency getCurrency(String code) {
        if (code == null) {
            return null;
        }
        return dao.findById(code).orElse(null);
    }

}
