package org.springframework.javafx;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enables support for {@link FXMLController}.
 */
@Target(value={ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(FXMLComponentConfiguration.class)
public @interface EnableFXMLControllers {

}
