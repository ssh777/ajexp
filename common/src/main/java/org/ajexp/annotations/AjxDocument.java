package org.ajexp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by SSh on 29.09.2017.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({TYPE})
public @interface AjxDocument {

	AjxName[] value();
	boolean autoFilter() default true;
}
