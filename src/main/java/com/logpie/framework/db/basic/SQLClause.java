package com.logpie.framework.db.basic;

import java.security.Timestamp;
import java.util.List;

public class SQLClause {
	private String key;
	private Object value;

	private Operator operator;
	private Function function;
	private boolean isASC;

	private SQLClause(String key, Object value, Operator operator,
			Function function, boolean isASC) {
		this.key = key;
		this.value = value;
		this.operator = operator;
		this.function = function;
		this.isASC = isASC;
	}

	public static SQLClause createUpdateClause(String key, Object value) {
		return createWhereClause(key, value);
	}

	public static SQLClause createWhereClause(String key, Object value) {
		return createWhereClause(key, value, Operator.EQUAL);
	}

	public static SQLClause createWhereClause(String key, Object value,
			Operator operator) {
		return new SQLClause(key, value, operator, null, true);
	}

	public static SQLClause createOrderByClause(String key, boolean isASC) {
		return new SQLClause(key, null, Operator.EQUAL, null, isASC);
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public String getValueString() {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Boolean.class) {
			return String.valueOf(((Boolean) value).booleanValue());
		}
		if (value.getClass() == Long.class) {
			return String.valueOf(((Long) value).longValue());
		}
		if (value.getClass() == Integer.class) {
			return String.valueOf(((Integer) value).intValue());
		}
		if (value.getClass() == Float.class) {
			return String.valueOf(((Float) value).floatValue());
		}
		if (value.getClass() == Timestamp.class) {
			return ((Timestamp) value).getTimestamp().toString();
		}
		if (value.getClass() == List.class) {
			StringBuffer buffer = new StringBuffer();
			int length = ((List<?>) value).size();
			for (int i = 0; i < length; i++) {
				buffer.append("'" + ((List<?>) value).get(i).toString() + "'");
				if (i < length - 1) {
					buffer.append(",");
				}
			}
		}
		return "'" + value.toString() + "'";
	}

	public Operator getOperator() {
		return operator;
	}

	public String getOperatorString() {
		return operator.toString();
	}

	public Function getFunction() {
		return function;
	}

	public Boolean getIsASC() {
		return isASC;
	}

	public String getOrderString() {
		return isASC == true ? "ASC" : "DESC";
	}
}
