package com.logpie.framework.db.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.Assert;

import com.logpie.framework.db.basic.ForeignKey;
import com.logpie.framework.db.basic.KVP;
import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.Sort;
import com.logpie.framework.db.basic.WhereParam;

public class SqlUtil {

	private static final Logger logger = Logger.getLogger(SqlUtil.class
			.getName());

	public static String insertSQL(final Model model) {
		String tableName = TableUtil.getTableName(model.getClass());
		Assert.notNull(tableName, "cannot find the table");

		List<KVP> keyValuePairs = ModelUtil.getModelKVP(model, false);
		Assert.notEmpty(keyValuePairs, "cannot get model map for INSERT");

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

		String sql = "insert into " + tableName + "(" + keys.toString()
				+ ") values (" + values.toString() + ")";
		logger.log(Level.INFO, "INSERT SQL: " + sql);

		return sql;
	}

	public static String updateSQL(final Model model) {
		String tableName = TableUtil.getTableName(model.getClass());
		Assert.notNull(tableName, "cannot find the table");

		List<KVP> keyValuePairs = ModelUtil.getModelKVP(model, false);
		Assert.notEmpty(keyValuePairs, "cannot get model map for UPDATE");

		StringBuffer sql = new StringBuffer();
		sql.append("update " + tableName + " set ");

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

		Long id = ModelUtil.getIdValue(model);
		Parameter cond = new WhereParam(model.getClass(), TableUtil.getId(model
				.getClass()), id);
		sql.append(whereSQL(model.getClass(), cond));

		logger.log(Level.INFO, "UPDATE SQL: " + sql);
		return sql.toString();
	}

	/**
	 * a query sql without any conditional sql, using left join connections
	 * 
	 * @param c
	 * @return
	 */
	public static String queryAllSQL(final Class<?> c) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + TableUtil.getTableName(c));
		sql.append(joinSQL(c));

		logger.log(Level.INFO, "QUERY SQL: " + sql.toString());
		return sql.toString();
	}

	public static String queryBySQL(final Class<?> c, final Parameter... params) {
		String sql = queryAllSQL(c);
		Assert.hasLength(sql);

		return params.length == 0 ? sql : sql + whereSQL(c, params);
	}

	public static String queryBySQL(final Class<?> c, final Pageable pageable,
			final Parameter... params) {
		Assert.notNull(pageable, "Pageable must not be null");

		if (!pageable.isPaged()) {
			return queryAllSQL(c);
		}

		StringBuffer sql = new StringBuffer(queryAllSQL(c));

		/**
		 * 分页SQL语句
		 * 
		 * select * from table ... join (select #id# from table where ... order
		 * by #id# limit #offset#, #size#) alias on #id# = alias.#id#
		 */
		String table = TableUtil.getTableName(c);
		String id = TableUtil.getId(c);
		String alias = "Inner" + TableUtil.getTableName(c);

		sql.append(" join (select " + id + " from " + table);
		/**
		 * 这里的where查询语句是关于分页中的where条件，目前只能查询关于主表中columns的相关条件，
		 * 不支持查询主表中其他子表的columns条件
		 */
		if (params.length > 0) {
			sql.append(whereSQL(c, params));
		}
		sql.append(" order by " + id + " limit " + pageable.getOffset() + ", "
				+ pageable.getSize() + ") " + alias);
		sql.append(" on " + table + "." + id + " = " + alias + "." + id);

		return sql.toString();
	}

	public static String countSQL(final Class<?> c, final String key,
			final Parameter... params) {
		String column = key == null || key.isEmpty() ? TableUtil.getId(c) : key;
		String sql = "select count(" + column + ") from "
				+ TableUtil.getTableName(c);

		return params.length == 0 ? sql : sql + whereSQL(c, params);
	}

	public static String orderBySQL(final Sort sort) {
		Assert.notNull(sort, "Sort must not be null");

		StringBuffer sql = new StringBuffer();
		sql.append(" order by ");

		int i = 0;
		for (Sort.Order order : sort) {
			if (i++ > 0) {
				sql.append(", ");
			}
			Assert.hasLength(order.getKey());
			// TODO check alias, add Table to Order
			sql.append(order.toString());
		}
		return sql.toString();
	}

	public static String whereSQL(final Class<?> c, final Parameter... params) {
		Assert.notEmpty(params, "Parameter must not be null or empty");

		StringBuffer sql = new StringBuffer();
		sql.append(" where ");

		for (int i = 0; i < params.length; i++) {

			if (i > 0) {
				sql.append(" and ");
			}

			Parameter param = params[i];
			String column = findColumn(c, param);
			Assert.notNull(column, "Cannot find corresponding column");

			sql.append(column + " " + param.getOperator() + " "
					+ param.valueToString());
		}

		logger.log(Level.INFO, "QUERY BY WHERE CLAUSE: " + sql.toString());
		return sql.toString();
	}

	public static String clearIllegalCharacters(final String s) {
		return s.replaceAll(".*([';]+|(--)+).*", "");
	}

	private static String joinSQL(final Class<?> c) {
		StringBuffer sql = new StringBuffer();

		Set<String> aliasSet = new HashSet<>();
		List<ForeignKey> foreignKeys = TableUtil.getAllForeignKeys(c, null);

		for (ForeignKey key : foreignKeys) {
			sql.append(" left join ");

			String alias = key.getTableAlias();
			if (alias == null || alias.equals("")) {
				alias = key.getTableName();
			}

			String refAlias = TableUtil.getOrAutoGenerateRefAlias(key);

			// check if alias of referenced table is unique
			if (aliasSet.contains(refAlias.toLowerCase())) {
				logger.log(Level.SEVERE, "foreign table alias duplicates");
				return null;
			}
			aliasSet.add(refAlias);

			sql.append(key.getRefTableName() + " " + refAlias + " on " + alias
					+ "." + key.getKey() + " = " + refAlias + "."
					+ TableUtil.getId(key.getRefTableClass()));
		}

		return sql.toString();
	}

	private static String findColumn(final Class<?> c, final Parameter param) {
		Assert.notNull(param, "Parameter must not be null or empty");
		String tableName = param.getTable().getName();
		String tableAlias = param.getTable().getAlias();

		List<String> columns = TableUtil.getAllColumnsWithAlias(c);
		for (String column : columns) {
			String columnUncased = column.toLowerCase();

			if (!column.contains(".")) {
				if (columnUncased.equals(param.getKey().toLowerCase())) {
					return column;
				}
				continue;
			}

			String alias = columnUncased.split("\\.")[0];
			String key = columnUncased.split("\\.")[1];

			if (key.equals(param.getKey().toLowerCase())) {
				if (((tableAlias == null || tableAlias.isEmpty()) && alias
						.endsWith(tableName.toLowerCase()))
						|| (tableAlias != null && alias.equals(tableAlias
								.toLowerCase()))) {
					return column;
				}
			}
		}
		return null;
	}
}
