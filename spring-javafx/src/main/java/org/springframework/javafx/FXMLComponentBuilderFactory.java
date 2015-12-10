package org.springframework.javafx;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.springframework.beans.factory.ListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link ListableBeanFactory} backed {@link BuilderFactory}.
 */
public class FXMLComponentBuilderFactory implements BuilderFactory {

    private static final Builder<Void> NULL_BUILDER = new Builder<Void>() {

        @Override
        public Void build() {
            throw new UnsupportedOperationException("This is a null not intended to be used.");
        }

        @Override
        public String toString() {
            return "NULL_BUILDER";
        }

    };

    private final ListableBeanFactory beanFactory;

    private final Map<Class<?>, Builder<?>> builders = new HashMap<>();

    public FXMLComponentBuilderFactory(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        Builder<?> builder = builders.computeIfAbsent(type, t -> {
            Map<String, ?> beansOfType = beanFactory.getBeansOfType(type, true, false);
            if (beansOfType.isEmpty()) {
                return NULL_BUILDER;
            }
            return () -> beanFactory.getBean(type);
        });

        return builder == NULL_BUILDER ? null : builder;
    }

}
