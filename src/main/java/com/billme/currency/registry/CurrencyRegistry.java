package com.billme.currency.registry;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRegistry.class);
    
    @Autowired
    private CurrencyDao dao;
    
    @Value("${currency.source.url}")
    private String sourceUrl;

    @PostConstruct
    void load() throws IOException {
        LOG.info("Loading currencies from {}...", sourceUrl);
        Document doc = Jsoup.connect(sourceUrl).get();
        String title = doc.title();
        System.out.println("Title : " + title);
        dao.save(new Currency("EUR", 100, 2, "Euro"));
    }
    
    public Currency getCurrency(String code) {
        return dao.findById(code).orElse(null);
    }

}
