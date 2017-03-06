package com.logpie.framework.db.util;

import java.util.Map;

public class SQLUtil {

	public static String insertSQL(Object model) {
		Map<String, String> map = DatabaseUtil.serializeColumnAndValue(model,
				false);
		String sql = "insert into "
				+ DatabaseUtil.getTableName(model.getClass()) + "("
				+ map.get("column") + ") values (" + map.get("value") + ")";
		return sql;
	}

	public static String queryAllSQL(Class<?> c) {
		String sql = "select * from " + DatabaseUtil.getTableName(c);
		return sql;
	}

}
