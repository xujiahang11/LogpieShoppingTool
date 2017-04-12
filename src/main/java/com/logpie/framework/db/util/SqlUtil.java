package com.logpie.framework.db.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.basic.ForeignKey;
import com.logpie.framework.db.basic.KVP;
import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.SqlClause;

public class SqlUtil {

	private static final Logger logger = Logger.getLogger(SqlUtil.class
			.getName());

	public static String insertSQL(final Model model) {
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

	public static String updateSQL(final Model model) {
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
			logger.log(Level.SEVERE, id + "," + idValue);
			logger.log(Level.SEVERE, "cannot find id");
			return null;
		}
		List<SqlClause> conditionArgs = new ArrayList<SqlClause>();
		conditionArgs.add(SqlClause.createUpdateClause(model.getClass(), id,
				idValue));
		sql.append(whereSQL(model.getClass(), conditionArgs));

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	public static String updateSQL(final Class<?> c,
			final List<SqlClause> updateArgs,
			final List<SqlClause> conditionArgs) {
		if (updateArgs == null || updateArgs.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find update columns and values");
			return null;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update " + TableUtil.getTableName(c) + " set ");
		int i = 0;
		for (SqlClause obj : updateArgs) {
			if (i++ > 0 && i <= updateArgs.size()) {
				sql.append(", ");
			}
			sql.append(obj.getColumn() + " = ");
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

	/**
	 * a query sql without any conditional sql, using left join connections
	 * 
	 * @param c
	 * @return
	 */
	public static String querySQL(final Class<?> c) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + TableUtil.getTableName(c));

		Set<String> aliasSet = new HashSet<String>();
		List<ForeignKey> foreignKeys = TableUtil.getAllForeignKeys(c, null);
		for (ForeignKey key : foreignKeys) {
			sql.append(" left join ");

			String alias = key.getAlias() == null || key.getAlias().equals("") ? key
					.getTable() : key.getAlias();
			String foreignTable = TableUtil.getTableName(key.getForeignEntity()
					.referencedTable());
			String foreignAlias = TableUtil.getAliasOfForeignTable(key);

			// check if alias of referenced table is unique
			if (aliasSet.contains(foreignAlias.toLowerCase())) {
				logger.log(Level.SEVERE, "foreign table alias duplicates");
				return null;
			}
			aliasSet.add(foreignAlias);

			sql.append(foreignTable + " " + foreignAlias + " on " + alias + "."
					+ key.getForeignEntity().name() + " = " + foreignAlias
					+ "."
					+ TableUtil.getId(key.getForeignEntity().referencedTable()));
		}
		logger.log(Level.INFO, "QUERY SQL: " + sql.toString());
		return sql.toString();
	}

	public static String orderBySQL(List<SqlClause> args) {
		if (args == null || args.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find order by clause");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" order by ");
		int i = 0;
		for (SqlClause obj : args) {
			sql.append(obj.getColumn() + " " + obj.getOrderString());
			if (++i < args.size()) {
				sql.append(", ");
			}
		}
		return sql.toString();
	}

	public static String whereSQL(final Class<?> c, final List<SqlClause> args) {
		if (args == null || args.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find where clause args");
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" where ");

		List<String> columns = new ArrayList<String>();
		columns.addAll(TableUtil.getAllColumns(c));

		int i = 0;
		for (SqlClause obj : args) {
			if (i++ > 0 && i <= args.size()) {
				sql.append(" and ");
			}
			sql.append(obj.getAlias() + "." + obj.getColumn() + " "
					+ obj.getOperatorString() + " ");
			if (obj.getValue() == null) {
				sql.append("?");
			} else {
				sql.append(obj.getValueString());
			}
		}

		logger.log(Level.INFO, "WHERE CLAUSE: " + sql.toString());
		return sql.toString();
	}
}
