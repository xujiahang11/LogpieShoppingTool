package com.logpie.framework.db.basic;

import java.security.Timestamp;

public class KVP {
	private String key;
	private Object value;

	public KVP(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public String valueToString() {
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
		return "'" + value.toString() + "'";
	}

}
