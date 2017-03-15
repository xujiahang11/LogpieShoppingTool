// Copyright 2017 logpie.com. All rights reserved.
package com.logpie.framework.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * specify the mapped database column for a method
 * 
 * @author xujiahang
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

	/**
	 *
	 * @return database key name
	 */
	public String name();

	/**
	 * declare five common types in the database
	 * 
	 * @author xujiahang
	 *
	 */
	public enum DataType {
		STRING, BOOLEAN, LONG, DOUBLE, TIMESTAMP
	}

	public DataType type();
}