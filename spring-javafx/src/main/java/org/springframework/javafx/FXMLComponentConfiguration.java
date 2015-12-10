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
 * {@link FXMLComponent} support configuration.
 */
@Configuration
public class FXMLComponentConfiguration {

    @Bean
    public ResourceBundle fxmlComponentResourceBundle(MessageSource messageSource) {
        return new MessageSourceResourceBundle(messageSource, LocaleContextHolder.getLocale());
    }

    @Bean
    public FXMLComponentControllerFactory fxmlComponentControllerFactory(BeanFactory beanFactory) {
        return new FXMLComponentControllerFactory(beanFactory);
    }

    @Bean
    public FXMLComponentBuilderFactory fxmlComponentBuilderFactory(ListableBeanFactory beanFactory) {
        return new FXMLComponentBuilderFactory(beanFactory);
    }

    @Bean
    public FXMLComponentLoader fxmlComponentLoader(@Qualifier("fxmlComponentControllerFactory") Callback<Class<?>, Object> controllerFactory,
                                                   @Qualifier("fxmlComponentBuilderFactory") BuilderFactory builderFactory,
                                                   @Qualifier("fxmlComponentResourceBundle") ResourceBundle resourceBundle) {
        FXMLComponentLoader fxmlComponentLoader = new FXMLComponentLoader();
        fxmlComponentLoader.setControllerFactory(controllerFactory);
        fxmlComponentLoader.setBuilderFactory(builderFactory);
        fxmlComponentLoader.setResourceBundle(resourceBundle);
        return fxmlComponentLoader;
    }

    @Bean
    public FXMLComponentPostProcessor fxmlComponentPostProcessor(FXMLComponentLoader fxmlComponentLoader, ResourceLoader resourceLoader) {
        return new FXMLComponentPostProcessor(fxmlComponentLoader, resourceLoader);
    }

}
