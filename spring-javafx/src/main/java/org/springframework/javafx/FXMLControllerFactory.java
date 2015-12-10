package org.springframework.javafx;

import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

/**
 * A {@link BeanFactory} backed controller factory.
 */
public class FXMLControllerFactory<T> implements Callback<Class<T>, T> {

    private final BeanFactory beanFactory;

    public FXMLControllerFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public T call(Class<T> type) {
        return beanFactory.getBean(type);
    }

}
