package com.prog.matoz.security.roles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Prog Matoz
 * @see - limits access to the endpoint, allows only the configured roles to have access
 * {@value}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedRoles {
	String[] value();
}
