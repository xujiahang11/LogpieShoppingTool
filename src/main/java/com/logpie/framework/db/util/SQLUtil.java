package com.logpie.framework.db.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.basic.ForeignKey;
import com.logpie.framework.db.basic.KVP;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.framework.db.basic.SQLClause;

public class SQLUtil {

	private static final Logger logger = Logger.getLogger(SQLUtil.class
			.getName());

	public static String insertSQL(final LogpieModel model) {
		if (TableUtil.getTableName(model.getClass()) == null) {
			logger.log(Level.SEVERE, "cannot find the table");
			return null;
		}

		List<KVP> keyValuePairs = ModelUtil.getModelKVP(model, false);
		if (keyValuePairs == null || keyValuePairs.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get model map for INSERT");
			return null;
		}

		StringBuffer keys = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for (KVP keyValuePair : keyValuePairs) {
			if (keyValuePair.getValue() == null) {
				continue;
			}
			if (keys.length() > 0) {
				keys.append(", ");
				values.append(", ");
			}
			keys.append(keyValuePair.getKey());
			values.append(keyValuePair.valueToString());
		}

		String sql = "insert into " + TableUtil.getTableName(model.getClass())
				+ "(" + keys.toString() + ") values (" + values.toString()
				+ ")";
		logger.log(Level.INFO, "INSERT SQL: " + sql);
		return sql;
	}

	public static String updateSQL(final LogpieModel model) {
		if (TableUtil.getTableName(model.getClass()) == null) {
			logger.log(Level.SEVERE, "cannot find the table");
			return null;
		}

		List<KVP> keyValuePairs = ModelUtil.getModelKVP(model, false);
		if (keyValuePairs == null || keyValuePairs.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get model map for UPDATE");
			return null;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update " + TableUtil.getTableName(model.getClass())
				+ " set ");
		int i = 0;
		for (KVP keyValuePair : keyValuePairs) {
			if (keyValuePair.getValue() == null) {
				continue;
			}
			if (i++ > 0) {
				sql.append(", ");
			}
			sql.append(keyValuePair.getKey() + " = "
					+ keyValuePair.valueToString());
		}

		String id = TableUtil.getId(model.getClass());
		Long idValue = ModelUtil.getIdValue(model);
		if (id == null || idValue == null) {
			logger.log(Level.SEVERE, "cannot find id");
			return null;
		}
		List<SQLClause> conditionArgs = new ArrayList<SQLClause>();
		conditionArgs.add(SQLClause.createWhereClause(id, idValue));
		sql.append(whereSQL(model.getClass(), conditionArgs));

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	public static String updateSQL(final Class<?> c,
			final List<SQLClause> updateArgs,
			final List<SQLClause> conditionArgs) {
		if (updateArgs == null || updateArgs.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find update columns and values");
			return null;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update " + TableUtil.getTableName(c) + " set ");
		int i = 0;
		for (SQLClause obj : updateArgs) {
			if (i++ > 0 && i <= updateArgs.size()) {
				sql.append(", ");
			}
			sql.append(obj.getKey() + " = ");
			if (obj.getValue() == null) {
				sql.append("?");
			} else {
				sql.append(obj.getValueString());
			}
		}
		if (conditionArgs == null || conditionArgs.isEmpty()) {
			logger.log(Level.INFO, "no specific where clause");
		} else {
			sql.append(whereSQL(c, conditionArgs));
		}

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	public static String querySQL(final Class<?> c) {
		StringBuffer sql = new StringBuffer();

		List<String> queryColumns = new ArrayList<String>();
		queryColumns.addAll(TableUtil.getAllColumns(c, null));
		if (queryColumns == null || queryColumns.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get query column list");
			return null;
		}

		sql.append("select ");
		int i = 0;
		for (String column : queryColumns) {
			sql.append(column);
			if (++i < queryColumns.size()) {
				sql.append(", ");
			} else {
				sql.append(" ");
			}
		}

		sql.append("from " + TableUtil.getTableName(c));

		List<ForeignKey> referencedKeys = TableUtil.getAllForeignKeys(c, null);
		for (ForeignKey key : referencedKeys) {
			sql.append(" left join ");
			ForeignEntity entity = key.getForeignEntity();
			String referencedTable = TableUtil.getTableName(entity
					.referencedTable());
			sql.append(referencedTable + " ");
			if (!entity.referencedTableAlias().isEmpty()) {
				referencedTable = entity.referencedTableAlias();
				sql.append(referencedTable + " ");
			}
			sql.append("on " + key.getTable() + "." + entity.name() + "="
					+ referencedTable + "."
					+ TableUtil.getId(entity.referencedTable()));
		}

		logger.log(Level.INFO, "QUERY SQL: " + sql.toString());
		return sql.toString();
	}

	public static String orderBySQL(List<SQLClause> args) {
		if (args == null || args.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find order by clause");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" order by ");
		int i = 0;
		for (SQLClause obj : args) {
			sql.append(obj.getKey());
			if (obj.getIsASC() != null) {
				sql.append(" " + obj.getOrderString());
			}
			if (++i < args.size()) {
				sql.append(", ");
			}
		}
		return sql.toString();
	}

	public static String whereSQL(final Class<?> c, final List<SQLClause> args) {
		if (args == null || args.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find where clause args");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" where ");

		List<String> columns = new ArrayList<String>();
		columns.addAll(TableUtil.getColumns(c, true, true, null));

		int i = 0;
		for (SQLClause obj : args) {
			if (i++ > 0 && i <= args.size()) {
				sql.append(" and ");
			}
			for (int j = 0; j < columns.size(); j++) {
				if (columns.get(j).endsWith(obj.getKey())) {
					sql.append(columns.get(j) + " = ");
					if (obj.getValue() == null) {
						sql.append("?");
					} else {
						sql.append(obj.getValueString());
					}
					break;
				}
				if (j == columns.size() - 1) {
					logger.log(Level.SEVERE, "cannot find " + obj.getKey()
							+ " column");
					return null;
				}
			}
		}

		logger.log(Level.INFO, "WHERE CLAUSE: " + sql.toString());
		return sql.toString();
	}
}
