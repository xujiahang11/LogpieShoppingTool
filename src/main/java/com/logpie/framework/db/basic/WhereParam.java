package com.logpie.framework.db.basic;

import com.logpie.framework.db.support.TableUtil;

public class WhereParam implements Parameter {

	private static final String DEFAULT_OPERATOR = "=";
	private final String key;
	private final String operator;
	private final Object value;
	private final Class<?> c;

	public WhereParam(Class<?> c, String key, Object value) {
		this(c, key, DEFAULT_OPERATOR, value);
	}

	/**
	 * 
	 * @param c
	 *            the table which the foreign key belongs to
	 * @param key
	 *            the foreign key's name
	 * @param operator
	 * @param value
	 *            the foreign key's value
	 */
	public WhereParam(Class<?> c, String key, String operator, Object value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
		this.c = c;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getOperator() {
		return operator;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String valueToString() {
		return TableUtil.toSqlString(value);
	}

	@Override
	public Table getTable() {
		return new Table(c);
	}
}
