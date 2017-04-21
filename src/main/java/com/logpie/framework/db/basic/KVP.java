package com.logpie.framework.db.basic;

import com.logpie.framework.db.support.TableUtil;

public class KVP implements Parameter {

	private Table table;
	private String key;
	private Object value;

	public KVP(Table table, String key, Object value) {
		this.table = table;
		this.key = key;
		this.value = value;
	}

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public String getKey() {
		return key;
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
	public String getOperator() {
		return "=";
	}
}
