package com.billme.currency.registry;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.mockito.Mockito;

public class CurrencyRegistryTest {

    private CurrencyDao      dao      = Mockito.mock(CurrencyDao.class);

    private CurrencyParser   parser   = Mockito.mock(CurrencyParser.class);

    private CurrencyRegistry registry = new CurrencyRegistry(dao, parser);

    @Test
    public void loadTest() throws Exception {
        List<Currency> currencyList = Stream.of(
                new Currency("EUR", 123, 2, "Euro"), 
                new Currency("USD", 456, 2, "Dollar"))
                .collect(Collectors.toList());
        Mockito.when(parser.readWikiPage()).thenReturn(currencyList);
        registry.load();
        
        Mockito.verify(dao).save(new Currency("EUR", 123, 2, "Euro"));
        Mockito.verify(dao).save(new Currency("USD", 456, 2, "Dollar"));
    }

    @Test
    public void getCurrencyExistsTest() throws Exception {
        Mockito.when(dao.findById("EUR")).thenReturn(Optional.of(new Currency("EUR", 123, 2, "Euro")));
        Currency currency = registry.getCurrency("EUR");
        assertNotNull(currency);
        assertEquals("EUR", currency.getCode());
    }

    @Test
    public void getCurrencyNotExistsTest() throws Exception {
        Mockito.when(dao.findById("LVL")).thenReturn(Optional.ofNullable(null));
        Currency currency = registry.getCurrency("LVL");
        assertNull(currency);
    }
    
}
