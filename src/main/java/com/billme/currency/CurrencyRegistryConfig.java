package com.billme.currency;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class CurrencyRegistryConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.
                ofPattern("dd/MM/yyyy HH:mm:ss.SSS").
                withLocale(Locale.getDefault()).
                withZone(ZoneId.systemDefault());
    }

}
