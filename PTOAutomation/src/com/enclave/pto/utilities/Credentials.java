package com.enclave.pto.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Change the credentials used during the test from the default credentials (loaded from the test.properties file)
 * 
 * Can be placed on the class (to override all tests in the class) or on the method
 * 
 * Setting the credentials will cause the selenium session to be torn down before the test is run
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Credentials {
	public String user() default "";
	public String password() default "";
}