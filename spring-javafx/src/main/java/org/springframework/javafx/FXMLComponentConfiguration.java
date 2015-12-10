package org.springframework.javafx;

import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.core.io.ResourceLoader;

import java.util.ResourceBundle;

/**
 * {@link FXMLController} support configuration.
 */
@Configuration
public class FXMLComponentConfiguration {

    @Bean
    public ResourceBundle fxmlComponentResourceBundle(MessageSource messageSource) {
        return new MessageSourceResourceBundle(messageSource, LocaleContextHolder.getLocale());
    }

    @Bean
    public FXMLControllerFactory fxmlComponentControllerFactory(BeanFactory beanFactory) {
        return new FXMLControllerFactory(beanFactory);
    }

    @Bean
    public FXMLControllerBuilderFactory fxmlComponentBuilderFactory(ListableBeanFactory beanFactory) {
        return new FXMLControllerBuilderFactory(beanFactory);
    }

    @Bean
    public FXMLControllerLoader fxmlComponentLoader(@Qualifier("fxmlComponentControllerFactory") Callback<Class<?>, Object> controllerFactory,
                                                    @Qualifier("fxmlComponentBuilderFactory") BuilderFactory builderFactory,
                                                    @Qualifier("fxmlComponentResourceBundle") ResourceBundle resourceBundle) {
        FXMLControllerLoader fxmlControllerLoader = new FXMLControllerLoader();
        fxmlControllerLoader.setControllerFactory(controllerFactory);
        fxmlControllerLoader.setBuilderFactory(builderFactory);
        fxmlControllerLoader.setResourceBundle(resourceBundle);
        return fxmlControllerLoader;
    }

    @Bean
    public FXMLControllerPostProcessor fxmlComponentPostProcessor(FXMLControllerLoader fxmlControllerLoader, ResourceLoader resourceLoader) {
        return new FXMLControllerPostProcessor(fxmlControllerLoader, resourceLoader);
    }

}
