package com.logpie.framework.db.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.ForeignEntity;

public class SQLUtil {

	private static final Logger logger = Logger.getLogger(SQLUtil.class
			.getName());

	public static String insertSQL(final LogpieModel model) {
		List<String> keyValuePair = DatabaseUtil.getColumnValuePairs(model,
				false);
		if (keyValuePair == null || keyValuePair.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get model map");
			return null;
		}
		if (DatabaseUtil.getTableName(model.getClass()) == null) {
			logger.log(Level.SEVERE, "cannot find the table");
			return null;
		}
		StringBuffer keys = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for (String s : keyValuePair) {
			if (keys.length() > 0) {
				keys.append(", ");
				values.append(", ");
			}
			String[] pair = s.split(",");
			keys.append(pair[0]);
			values.append(pair[1]);
		}

		String sql = "insert into "
				+ DatabaseUtil.getTableName(model.getClass()) + "("
				+ keys.toString() + ") values (" + values.toString() + ")";
		logger.log(Level.INFO, "INSERT SQL: " + sql);
		return sql;
	}

	public static String updateSQL(final Class<?> c,
			final String[] updateColumns, final String[] conditionColumns) {
		if (updateColumns == null || updateColumns.length == 0) {
			logger.log(Level.SEVERE, "cannot find update columns");
			return null;
		}
		if (conditionColumns == null || conditionColumns.length == 0) {
			logger.log(Level.SEVERE, "cannot find condition columns");
			return null;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update " + DatabaseUtil.getTableName(c) + " set ");
		int i = 0;
		for (String column : updateColumns) {
			if (i++ > 0 && i < updateColumns.length) {
				sql.append(", ");
			}
			sql.append(column + " = ?");
		}
		sql.append(whereConditionSQL(c, conditionColumns, false));

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	public static String updateSQL(final Class<?> c,
			final Map<String, Object> updateMap,
			final Map<String, Object> conditionMap) {
		if (updateMap == null || updateMap.size() == 0) {
			logger.log(Level.SEVERE, "cannot find update columns and values");
			return null;
		}
		if (conditionMap == null || conditionMap.size() == 0) {
			logger.log(Level.SEVERE, "cannot find condition columns and values");
			return null;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update " + DatabaseUtil.getTableName(c) + " set ");
		int i = 0;
		for (Entry<String, Object> entry : updateMap.entrySet()) {
			if (i++ > 0 && i < updateMap.size()) {
				sql.append(", ");
			}
			sql.append(entry.getKey()
					+ " = "
					+ DatabaseUtil.toSqlString(c, entry.getKey(),
							entry.getValue()));
		}
		sql.append(whereConditionSQL(c, conditionMap, false));

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	public static String querySQL(final Class<?> c) {
		StringBuffer sql = new StringBuffer();

		List<String> queryColumns = new ArrayList<String>();
		queryColumns.addAll(DatabaseUtil.getAllColumns(c));
		queryColumns.addAll(DatabaseUtil.getAllLinkedColumns(c));
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
		logger.log(Level.INFO, "QUERY SQL: " + sql.toString());
		return sql.toString();
	}

	public static String whereConditionSQL(final Class<?> c,
			final String[] conditionColumns, final boolean hasAlias) {
		if (conditionColumns == null || conditionColumns.length == 0) {
			logger.log(Level.SEVERE, "cannot find condition columns");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" where ");

		if (hasAlias) {
			List<String> columns = new ArrayList<String>();
			columns.addAll(DatabaseUtil.getAllColumns(c));
			columns.addAll(DatabaseUtil.getAllLinkedColumns(c));

			int i = 0;
			for (String column : conditionColumns) {
				if (i++ > 0 && i < conditionColumns.length) {
					sql.append(" and ");
				}
				for (int j = 0; j < columns.size(); j++) {
					if (columns.get(j).endsWith(column)) {
						sql.append(columns.get(j) + "= ?");
						break;
					}
					if (j == columns.size() - 1) {
						logger.log(Level.SEVERE, "cannot find " + column
								+ " column");
						return null;
					}
				}
			}
		} else {
			int i = 0;
			for (String column : conditionColumns) {
				if (i++ > 0 && i < conditionColumns.length) {
					sql.append(" and ");
				}
				sql.append(column + " = ?");
			}
		}
		logger.log(Level.INFO, "WHERE CONDITION: " + sql.toString());
		return sql.toString();
	}

	public static String whereConditionSQL(final Class<?> c,
			final Map<String, Object> conditionMap, final boolean hasAlias) {
		if (conditionMap == null || conditionMap.size() == 0) {
			logger.log(Level.SEVERE, "cannot find condition columns and values");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" where ");

		if (hasAlias) {
			List<String> columns = new ArrayList<String>();
			columns.addAll(DatabaseUtil.getAllColumns(c));
			columns.addAll(DatabaseUtil.getAllLinkedColumns(c));

			int i = 0;
			for (String column : conditionMap.keySet()) {
				if (i++ > 0 && i < conditionMap.size()) {
					sql.append(" and ");
				}
				for (int j = 0; j < columns.size(); j++) {
					if (columns.get(j).endsWith(column)) {
						sql.append(columns.get(j)
								+ " = "
								+ DatabaseUtil.toSqlString(c, column,
										conditionMap.get(column)));
						break;
					}
					if (j == columns.size() - 1) {
						logger.log(Level.SEVERE, "cannot find " + column
								+ " column");
						return null;
					}
				}
			}
		} else {
			int i = 0;
			for (Entry<String, Object> entry : conditionMap.entrySet()) {
				if (i++ > 0 && i < conditionMap.size()) {
					sql.append(" and ");
				}
				sql.append(entry.getKey()
						+ " = "
						+ DatabaseUtil.toSqlString(c, entry.getKey(),
								entry.getValue()));
			}
		}
		logger.log(Level.INFO, "WHERE CONDITION: " + sql.toString());
		return sql.toString();
	}
}
