package org.springframework.javafx;

import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

/**
 * A {@link BeanFactory} backed controller factory.
 */
public class FXMLControllerFactory implements Callback<Class<?>, Object> {

    private final BeanFactory beanFactory;

    public FXMLControllerFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object call(Class<?> type) {
        return beanFactory.getBean(type);
    }

}
