package com.logpie.framework.db.basic;

import java.lang.reflect.Field;

import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.support.TableUtil;

public class ForeignKey {

	private Table table;
	private ForeignEntity foreignEntity;

	public ForeignKey(Class<?> tableClass, ForeignEntity foreignEntity) {
		this(tableClass, null, foreignEntity);
	}

	public ForeignKey(Class<?> tableClass, String alias,
			ForeignEntity foreignEntity) {
		this.table = new Table(tableClass, alias);
		this.foreignEntity = foreignEntity;
	}

	public String getTableName() {
		return table.getName();
	}

	public String getTableAlias() {
		return table.getAlias();
	}

	public String getKey() {
		return foreignEntity.name();
	}

	public Class<?> getRefTableClass() {
		return foreignEntity.referencedTable();
	}

	public String getRefTableName() {
		return TableUtil.getTableName(getRefTableClass());
	}

	public String getRefTableAlias() {
		return foreignEntity.referencedTableAlias();
	}

	public boolean hasChildForeignKey() {
		if (hasForeignEntityAnnotation(getRefTableClass())) {
			return true;
		}
		return false;
	}

	private static boolean hasForeignEntityAnnotation(final Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignEntity.class)) {
				return true;
			}
		}
		return false;
	}
}
