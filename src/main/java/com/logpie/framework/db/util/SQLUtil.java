package com.logpie.framework.db.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.logpie.framework.db.annotation.DatabaseForeignKey;

public class SQLUtil {

	public static String insertSQL(Object model) {
		Map<String, String> map = DatabaseUtil.getInsertColumnsAndValues(model,
				false);
		String sql = "insert into "
				+ DatabaseUtil.getTableName(model.getClass()) + "("
				+ map.get("column") + ") values (" + map.get("value") + ")";
		return sql;
	}

	public static String querySQL(Class<?> model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");

		List<String> queryColumns = DatabaseUtil.getQueryColumns(model);
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

		sql.append("from " + DatabaseUtil.getTableName(model) + " ");
		if (DatabaseUtil.hasTableAlias(model)) {
			sql.append(DatabaseUtil.getTableAlias(model) + " ");
		}

		List<DatabaseForeignKey> referencedColumns = DatabaseUtil
				.getForeignKeyList(model);
		if (!referencedColumns.isEmpty()) {
			for (DatabaseForeignKey column : referencedColumns) {
				sql.append("join ");
				String tableName = DatabaseUtil.getTableName(column
						.referencedTableModel());
				sql.append(tableName + " ");
				if (!column.referencedTableAlias().isEmpty()) {
					tableName = column.referencedTableAlias();
					sql.append(tableName + " ");
				}
				sql.append("on " + DatabaseUtil.getTableAlias(model) + "."
						+ column.name() + "=" + tableName + "."
						+ column.referencedKey());
			}
		}

		return sql.toString();
	}

	public static String querySQLByKey(Class<?> model,
			Map<String, String> params) {
		String sql = querySQL(model) + " where ";
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
