package com.logpie.framework.db.basic;

public enum SqlOperator {
	EQUAL("="), SMALLER("<"), GREATER(">"), LIKE("like"), IN("in"), NOTIN(
			"not in"), BETWEEN("between"), NOTBETWEEN("not between");

	private String text;

	SqlOperator(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
