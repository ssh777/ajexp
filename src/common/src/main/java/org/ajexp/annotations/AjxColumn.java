package org.ajexp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by SSh on 29.09.2017.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface AjxColumn {
	
	int columnOrder() default 0;
	AjxName[] headerName();
	boolean autoSize() default true;
	int width() default 0;
	boolean complexType() default false;
	String format() default "";
}
