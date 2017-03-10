package com.logpie.framework.db.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.logpie.framework.db.annotation.ForeignEntity;

public class SQLUtil {

	public static String insertSQL(Object model) {
		Map<String, String> map = DatabaseUtil.getInsertColumnsAndValues(model,
				false);
		String sql = "insert into "
				+ DatabaseUtil.getTableName(model.getClass()) + "("
				+ map.get("column") + ") values (" + map.get("value") + ")";
		return sql;
	}

	public static String querySQL(Class<?> c) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");

		List<String> queryColumns = DatabaseUtil.getQueryColumns(c);
		int i = 0;
		for (String column : queryColumns) {
			sql.append(column);
			if (i == queryColumns.size() - 1) {
				sql.append(" ");
			} else {
				sql.append(", ");
			}
			i++;
		}

		sql.append("from " + DatabaseUtil.getTableName(c) + " ");
		if (DatabaseUtil.hasTableAlias(c)) {
			sql.append(DatabaseUtil.getTableAliasOrName(c) + " ");
		}

		List<ForeignEntity> referencedColumns = DatabaseUtil
				.getForeignEntities(c);
		if (!referencedColumns.isEmpty()) {
			for (ForeignEntity column : referencedColumns) {
				sql.append("join ");
				String tableName = DatabaseUtil.getTableName(column
						.referencedTable());
				sql.append(tableName + " ");
				if (!column.referencedTableAlias().isEmpty()) {
					tableName = column.referencedTableAlias();
					sql.append(tableName + " ");
				}
				sql.append("on " + DatabaseUtil.getTableAliasOrName(c) + "."
						+ column.name() + "=" + tableName + "."
						+ column.referencedColumn());
			}
		}

		return sql.toString();
	}

	public static String querySQLByKey(Class<?> c, Map<String, String> params) {
		String sql = querySQL(c) + " where ";
		int i = 0;
		for (Entry<String, String> param : params.entrySet()) {
			if (i > 0 && i < params.size() - 1) {
				sql += " and ";
			}
			sql += param.getKey() + "." + param.getValue() + "=?";
		}
		return sql;
	}

}
