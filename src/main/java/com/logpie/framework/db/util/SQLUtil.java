package com.logpie.framework.db.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.ForeignEntity;

public class SQLUtil {

	private static final Logger logger = Logger.getLogger(SQLUtil.class
			.getName());

	public static String insertSQL(Object model) {
		Map<String, String> map = DatabaseUtil.getInsertColumnsAndValues(model,
				false);
		if (map == null || map.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get model map");
			return null;
		}
		if (DatabaseUtil.getTableName(model.getClass()) == null) {
			logger.log(Level.SEVERE, "cannot find the table");
			return null;
		}
		String sql = "insert into "
				+ DatabaseUtil.getTableName(model.getClass()) + "("
				+ map.get("column") + ") values (" + map.get("value") + ")";
		logger.log(Level.FINE, sql);
		return sql;
	}

	public static String querySQL(Class<?> c) {
		StringBuffer sql = new StringBuffer();
		List<String> queryColumns = DatabaseUtil.getQueryColumns(c);
		if (queryColumns == null || queryColumns.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get query column list");
			return null;
		}
		if (DatabaseUtil.getTableName(c) == null) {
			logger.log(Level.SEVERE, "cannot find the table");
			return null;
		}

		sql.append("select ");
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

		sql.append("from " + DatabaseUtil.getTableName(c));
		if (DatabaseUtil.hasTableAlias(c)) {
			sql.append(" " + DatabaseUtil.getTableAliasOrName(c));
		}

		List<ForeignEntity> referencedColumns = DatabaseUtil
				.getForeignEntities(c);
		if (!referencedColumns.isEmpty()) {
			for (ForeignEntity column : referencedColumns) {
				sql.append(" join ");
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
		logger.log(Level.FINE, sql.toString());
		return sql.toString();
	}

	public static String querySQLByKey(Class<?> c, Map<String, String> params) {
		String sql = querySQL(c);
		if (sql == null || sql.equals("")) {
			logger.log(Level.SEVERE, "cannot get query sql");
			return null;
		}
		if (params == null || params.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find conditional parameters");
			return null;
		}

		sql += " where ";
		int i = 0;
		for (Entry<String, String> param : params.entrySet()) {
			if (i > 0 && i < params.size() - 1) {
				sql += " and ";
			}
			sql += param.getKey() + "." + param.getValue() + "=?";
		}
		logger.log(Level.FINE, sql);
		return sql;
	}

}
