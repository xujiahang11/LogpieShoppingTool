package com.logpie.framework.db.basic;

import com.logpie.framework.db.annotation.ForeignEntity;

public class ForeignKey {
	private String table;
	private String alias;
	private ForeignEntity foreignEntity;

	public ForeignKey(String table, String alias, ForeignEntity foreignEntity) {
		this.table = table;
		this.alias = alias;
		this.foreignEntity = foreignEntity;
	}

	public String getTable() {
		return table;
	}

	public String getAlias() {
		return alias;
	}

	public ForeignEntity getForeignEntity() {
		return foreignEntity;
	}
}
