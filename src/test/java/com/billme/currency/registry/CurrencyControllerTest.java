package com.billme.currency.registry;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.billme.currency.logger.LoggingService;
import com.billme.currency.registry.errors.InvalidCurrencyCodeException;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private CurrencyService service;
    
    @MockBean
    private LoggingService loggingService;
    
    @Test
    public void getCurrencyOkTest() throws Exception {
        Mockito.when(service.getCurrency("EUR")).thenReturn(new CurrencyDto("EUR", 123, 2, "Euro"));        

        mvc.perform(MockMvcRequestBuilders.get("/currencies/{currencyCode}", "EUR").with(remoteAddress("0.0.0.0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Code", CoreMatchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Num", CoreMatchers.is(123)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.E", CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Currency", CoreMatchers.is("Euro")))
                .andReturn();
        
        Mockito.verify(loggingService).logEvent("EUR", "0.0.0.0");
    }

    @Test
    public void getCurrencyNotFoundTest() throws Exception {
        Mockito.when(service.getCurrency("LVL")).thenThrow(new InvalidCurrencyCodeException("LVL"));       

        mvc.perform(MockMvcRequestBuilders.get("/currencies/{currencyCode}", "LVL").with(remoteAddress("0.0.0.0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        
        Mockito.verify(loggingService).logEvent("LVL", "0.0.0.0");
    }
    
    @Test
    public void getCurrencyUnexpectedErrorTest() throws Exception {
        Mockito.when(service.getCurrency("USD")).thenThrow(new RuntimeException("Unexpected error"));       

        mvc.perform(MockMvcRequestBuilders.get("/currencies/{currencyCode}", "USD").with(remoteAddress("0.0.0.0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn();
        
        Mockito.verify(loggingService).logEvent("USD", "0.0.0.0");
    }
    
    private static RequestPostProcessor remoteAddress(final String remoteHost) {
        return request -> {
            request.setRemoteAddr(remoteHost);
            return request;
        };
    }
    
}
