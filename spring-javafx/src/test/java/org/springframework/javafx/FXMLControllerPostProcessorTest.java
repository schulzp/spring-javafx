package org.springframework.javafx;

import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FXMLControllerPostProcessorTest {

    public static final String DEFAULT_RESOURCE_PREFIX = "/fxml/";
    public static final String DEFAULT_RESOURCE_SUFFIX = ".fxml";

    @Mock
    private FXMLControllerLoader componentLoader;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Resource resource;

    @InjectMocks
    private FXMLControllerPostProcessor postProcessor;

    @BeforeEach
    public void initialize() throws IOException {
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getURL()).thenReturn(getClass().getResource(DEFAULT_RESOURCE_PREFIX + "empty" + DEFAULT_RESOURCE_SUFFIX));
    }

    @Test
    public void stripTestSuffix() {
        postProcessor.setStripSuffix("Test");
        postProcessor.postProcessBeforeInitialization(mock(ControllerTest.class), "test");
        verify(resourceLoader).getResource(endsWith("Controller" + DEFAULT_RESOURCE_SUFFIX));
    }

    @Test
    public void stripDefaultSuffix() {
        postProcessor.postProcessBeforeInitialization(mock(TestController.class), "test");
        verify(resourceLoader).getResource(endsWith("Test" + DEFAULT_RESOURCE_SUFFIX));
    }

    @FXMLController
    public static class ControllerTest {

    }

    @FXMLController
    public static class TestController {

    }

}