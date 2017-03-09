package com.logpie.framework.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.logpie.framework.db.annotation.DatabaseKey.ColumnType;

/**
 * specify the mapped database foreign key for a method
 * 
 * @author xujiahang
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DatabaseForeignKey {
	/**
	 *
	 * @return database key name
	 */
	public String name();

	public ColumnType type();

	/**
	 * 
	 * @return referenced column name in another table
	 */
	public String referencedKey();

	/**
	 * 
	 * @return referenced table entity
	 */
	public Class<?> referencedTableModel();

	/**
	 * 
	 * @return referenced table alias name
	 */
	public String referencedTableAlias() default "";

}
