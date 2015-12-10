package org.springframework.javafx.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.javafx.EnableFXMLComponents;

/**
 * Example configuration.
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.javafx.controller")
@EnableFXMLComponents
public class ExampleConfiguration {

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        return messageSource;
    }

}
