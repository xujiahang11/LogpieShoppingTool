package com.logpie.framework.db.basic;

import com.logpie.framework.db.support.TableUtil;

public class Table {

	private Class<?> tableClass;
	private String alias;

	public Table(final Class<?> tableClass) {
		this(tableClass, null);
	}

	public Table(final Class<?> tableClass, final String alias) {
		this.tableClass = tableClass;
		this.alias = alias;
	}

	public String getName() {
		return TableUtil.getTableName(tableClass);
	}

	public Class<?> getTableClass() {
		return tableClass;
	}

	public String getAlias() {
		return alias;
	}

	public void setClass(Class<?> tableClass) {
		this.tableClass = tableClass;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Table) {
			Table table = (Table) o;
			if (table.getAlias() == null || table.getAlias().isEmpty()) {
				return table.getTableClass() == this.tableClass ? true : false;
			}
			return table.getAlias().equals(this.alias) ? true : false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (getAlias() == null || getAlias().isEmpty()) {
			return getTableClass().hashCode();
		}
		return getAlias().hashCode();
	}
}
