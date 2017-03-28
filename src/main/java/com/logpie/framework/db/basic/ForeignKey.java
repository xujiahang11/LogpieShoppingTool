package com.logpie.framework.db.basic;

import com.logpie.framework.db.annotation.ForeignEntity;

public class ForeignKey {
	private String table;
	private ForeignEntity foreignEntity;

	public ForeignKey(String table, ForeignEntity foreignEntity) {
		this.table = table;
		this.foreignEntity = foreignEntity;
	}

	public String getTable() {
		return table;
	}

	public ForeignEntity getForeignEntity() {
		return foreignEntity;
	}
}
