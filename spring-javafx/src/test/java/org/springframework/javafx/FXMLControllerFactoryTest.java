package org.springframework.javafx;

import com.example.mockito.MockitoExtension;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.BeanFactory;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FXMLControllerFactoryTest {

    @Mock
    BeanFactory beanFactory;

    @InjectMocks
    FXMLControllerFactory factory;

    @Test
    public void callDelegatesToBeanFactory() throws Exception {
        factory.call(getClass());

        verify(beanFactory).getBean(getClass());
    }

}