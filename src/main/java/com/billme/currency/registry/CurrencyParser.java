package com.billme.currency.registry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class CurrencyParser {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyParser.class);

    @Value("${currency.source.url}")
    private String              sourceUrl;

    List<Currency> parse() throws IOException {
        LOG.info("Loading currencies from {}...", sourceUrl);
        Document doc = Jsoup.connect(sourceUrl).get();
        Elements headers = doc.getElementsByTag("h2");
        for (int i = 0; i < headers.size(); i++) {
            Element header = headers.get(i);
            if (header.text().startsWith("Active codes")) {
                return parseTable(header.nextElementSibling().nextElementSibling());
            }
        }
        throw new RuntimeException("Failed to parse currencies from Wiki page!");
    }

    private List<Currency> parseTable(Element table) {
        List<Currency> currencies = new ArrayList<>();
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            currencies.add(parseRow(rows.get(i)));
        }
        return currencies;
    }

    private Currency parseRow(Element row) {
        Elements columns = row.select("td");
        String code = columns.get(0).text().substring(0, 3);
        Integer number = parseInt(columns.get(1).text());
        if (number == null) {
            LOG.error("Failed to parse currency {}! Invalid currency num: {}", code, columns.get(1).text());
            return null;
        }
        Integer scale = parseInt(columns.get(2).text());
        String name = columns.get(3).text();
        return new Currency(code, number, scale, name);
    }

    private Integer parseInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
