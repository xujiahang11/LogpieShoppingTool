package com.logpie.framework.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * specify the mapped database foreign key for a method
 * 
 * @author xujiahang
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForeignKeyColumn {

	/**
	 *
	 * @return database foreign key name
	 */
	public String name();

	/**
	 * 
	 * @return referenced table class
	 */
	public Class<?> referencedEntityClass();

	/**
	 * 
	 * @return referenced table alias name
	 */
	public String referencedEntityAlias() default "";

}