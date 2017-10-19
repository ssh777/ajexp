package org.ajexp.annotations;

import org.ajexp.AjxLocale;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by SSh on 29.09.2017.
 */
@Target({})
@Retention(RUNTIME)
public @interface AjxName {
    AjxLocale locale() default AjxLocale.NULL;
    String value();
}
