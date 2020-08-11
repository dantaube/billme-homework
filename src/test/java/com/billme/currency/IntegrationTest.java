package com.billme.currency;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.billme.currency.logger.LogEventDto;
import com.billme.currency.logger.LoggingService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private LoggingService logger;
    
    @Before
    public void clearLog() {
        logger.clean();
    }
    
    @Test
    public void getCurrencyOkTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/currencies/{currencyCode}", "EUR").with(remoteAddress("0.0.0.0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Code", CoreMatchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Num", CoreMatchers.is(978)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.E", CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Currency", CoreMatchers.is("Euro")))
                .andReturn();
        
        verifyLog("EUR", "0.0.0.0");
    }

    @Test
    public void getCurrencyNotFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/currencies/{currencyCode}", "LVL").with(remoteAddress("0.0.0.0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        
        verifyLog("LVL", "0.0.0.0");
    }
    
    private void verifyLog(String currencyCode, String clientIp) throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/log")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        List<LogEventDto> log = (List<LogEventDto>) result.getModelAndView().getModel().get("logEvents");
        assertNotNull(log);
        assertEquals(1, log.size());
        assertEquals(currencyCode, log.get(0).getCurrencyCode());
        assertEquals(clientIp, log.get(0).getClientAddress());
    }
    
    private static RequestPostProcessor remoteAddress(final String remoteHost) {
        return request -> {
            request.setRemoteAddr(remoteHost);
            return request;
        };
    }
    
}
