package com.billme.currency.registry;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.billme.currency.registry.errors.InvalidCurrencyCodeException;

public class CurrencyServiceTest {

    private CurrencyRegistry registry = Mockito.mock(CurrencyRegistry.class);

    private CurrencyService  service  = new CurrencyService(registry);

    @Test
    public void getCurrencyExistsTest() throws Exception {
        Mockito.when(registry.getCurrency("EUR")).thenReturn(new Currency("EUR", 123, 2, "Euro"));
        CurrencyDto dto = service.getCurrency("EUR");
        assertNotNull(dto);
        assertEquals("EUR", dto.getCode());
        assertEquals(123, dto.getNumber());
        assertEquals(2, dto.getScale());
        assertEquals("Euro", dto.getName());
    }

    @Test
    public void getCurrencyNotExistsTest() throws Exception {
        Mockito.when(registry.getCurrency("LAT")).thenReturn(null);
        InvalidCurrencyCodeException exception = null;
        try {
            service.getCurrency("LAT");
        } catch (InvalidCurrencyCodeException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

}
