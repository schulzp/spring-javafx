package org.springframework.javafx;

import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

/**
 * A {@link BeanFactory} backed controller factory.
 */
public class FXMLComponentControllerFactory implements Callback<Class<?>, Object> {

    private final BeanFactory beanFactory;

    public FXMLComponentControllerFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object call(Class<?> type) {
        return beanFactory.getBean(type);
    }

}
