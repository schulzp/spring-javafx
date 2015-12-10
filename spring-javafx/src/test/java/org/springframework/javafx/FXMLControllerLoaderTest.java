package org.springframework.javafx;

import org.springframework.test.MockitoExtension;
import javafx.fxml.FXMLLoader;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.gen5.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FXMLControllerLoaderTest {

    @Mock
    BuilderFactory builderFactory;

    @Mock
    Callback<Class<?>, Object> controllerFactory;

    @Mock
    ResourceBundle resourceBundle;

    @Mock
    Object innerLoaderResult;

    FXMLControllerLoader outerLoader;

    FXMLLoader innerLoader;

    @BeforeEach
    void createLoader() {
        outerLoader = new FXMLControllerLoader() {
            {
                setResourceBundle(resourceBundle);
                setBuilderFactory(builderFactory);
                setControllerFactory(controllerFactory);
            }

            @Override
            protected FXMLLoader createFXMLLoader() {
                innerLoader = spy(super.createFXMLLoader());
                try {
                    doAnswer(invocation -> innerLoaderResult).when(innerLoader).load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return innerLoader;
            }

        };
    }

    @Test
    public void load() throws Exception {
        URL location = getClass().getResource("/fxml/empty.fxml");
        Object controller = mock(Object.class);
        Object outerLoaderResult = outerLoader.load(location, controller);
        assertSame(innerLoaderResult, outerLoaderResult, "unexpected result");
        assertSame(resourceBundle, innerLoader.getResources(), "unexpected resources bundle");
        assertSame(builderFactory, innerLoader.getBuilderFactory(), "unexpected builder factory");
        assertSame(controllerFactory, innerLoader.getControllerFactory(), "unexpected controller factory");
        assertSame(controller, innerLoader.getController(), "unexpected controller");
        assertSame(location, innerLoader.getLocation(), "unexpected location");
    }

}