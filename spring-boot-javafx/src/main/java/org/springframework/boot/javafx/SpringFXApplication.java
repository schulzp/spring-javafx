package org.springframework.boot.javafx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * Abstract base class for spring backed JavaFX applications.
 *
 * <h3>Usage</h3>
 */
public abstract class SpringFXApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    private final Class[] sources;

    /**
     * Initializes a new SpringFXApplication.
     *
     * @param sources an array of {@link org.springframework.context.annotation.Configuration} annotated classes
     */
    public SpringFXApplication(Class...sources) {
        this.sources = sources;
    }

    @Override
    public void init() throws Exception {
        List<String> parameters = getParameters().getRaw();
        String[] args = parameters.toArray(new String[parameters.size()]);

        applicationContext = SpringApplication.run(sources, args);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        applicationContext.registerShutdownHook();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }

}

