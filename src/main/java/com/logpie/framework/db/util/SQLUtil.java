package com.logpie.framework.db.util;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.shopping.tool.model.LogpieModel;

public class SQLUtil {

	private static final Logger logger = Logger.getLogger(SQLUtil.class
			.getName());

	public static String insertSQL(LogpieModel model) {
		Map<String, String> map = DatabaseUtil.getColumnAndValuePairs(model,
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
		logger.log(Level.INFO, sql);
		return sql;
	}

	public static String querySQL(Class<?> c) {
		StringBuffer sql = new StringBuffer();
		List<String> queryColumns = DatabaseUtil.getAllColumns(c);
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
				.getForeignEntityAnnotations(c);
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
						+ DatabaseUtil.getID(column.referencedTable()));
			}
		}
		logger.log(Level.INFO, sql.toString());
		return sql.toString();
	}

	/**
	 * 
	 * @param c
	 * @param conditionColumns
	 * @return
	 */
	public static String whereConditionSQL(Class<?> c,
			List<String> conditionColumns) {
		if (conditionColumns == null || conditionColumns.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find condition columns");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" where ");
		List<String> columns = DatabaseUtil.getAllColumns(c);
		int i = 0;
		for (String s : conditionColumns) {
			if (i > 0 && i < conditionColumns.size() - 1) {
				sql.append(" and ");
			}
			for (int j = 0; j < columns.size(); j++) {
				if (columns.get(j).endsWith(s)) {
					sql.append(columns.get(j) + "=?");
					break;
				}
				if (j == columns.size() - 1) {
					logger.log(Level.SEVERE, "cannot find " + s + " column");
					return null;
				}
			}
		}
		logger.log(Level.INFO, sql.toString());
		return sql.toString();
	}
}
