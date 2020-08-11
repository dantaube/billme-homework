package com.billme.currency.registry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.billme.currency.registry.errors.CurrencyRegistryError;

public class CurrencyParserTest {

    private CurrencyParser parser;

    @Test
    public void readWikiPageSuccessTest() throws Exception {
        parser = new CurrencyParser("https://en.wikipedia.org/wiki/ISO_4217#Active_codes");
        List<Currency> currencies = parser.readWikiPage();
        assertFalse(currencies.isEmpty());
    }

    @Test
    public void readWikiPageWrongUrlTest() throws Exception {
        parser = new CurrencyParser("https://en.wikipedia.org/wiki/Elephant");
        List<Currency> currencies = parser.readWikiPage();
        assertTrue(currencies.isEmpty());
    }

    @Test(expected = CurrencyRegistryError.class)
    public void readWikiPageBadUrlTest() throws Exception {
        parser = new CurrencyParser("bad url");
        parser.readWikiPage();
    }

}
