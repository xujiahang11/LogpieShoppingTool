package com.logpie.framework.db.basic;

import java.security.Timestamp;
import java.util.List;

import com.logpie.framework.db.util.TableUtil;

public class SqlClause {
	private Class<?> table;
	private String alias;
	private Class<?> parentTable;
	private String column;
	private Object value;
	private SqlOperator operator;
	private SqlFunction function;
	private boolean isASC;

	/**
	 * 
	 * @param table
	 * @param alias
	 * @param parentTable
	 *            A table has a foreign key referenced with the current table
	 * @param column
	 * @param value
	 * @param operator
	 * @param function
	 * @param isASC
	 */
	private SqlClause(Class<?> table, String alias, Class<?> parentTable,
			String column, Object value, SqlOperator operator,
			SqlFunction function, boolean isASC) {
		this.table = table;
		this.alias = alias;
		this.parentTable = parentTable;
		this.column = column;
		this.value = value;
		this.operator = operator;
		this.function = function;
		this.isASC = isASC;
	}

	public static SqlClause createUpdateClause(Class<?> table, String column,
			Object value) {
		return createWhereClause(table, null, null, column, value,
				SqlOperator.EQUAL);
	}

	public static SqlClause createWhereClause(Class<?> table, String alias,
			Class<?> parentTable, String column, Object value,
			SqlOperator operator) {
		return new SqlClause(table, alias, parentTable, column, value,
				operator, null, true);
	}

	public static SqlClause createOrderByClause(Class<?> table, String alias,
			Class<?> parentTable, String column, boolean isASC) {
		return new SqlClause(table, alias, parentTable, column, null,
				SqlOperator.EQUAL, null, isASC);
	}

	/**
	 * generate an alias if referenced table does not have an alias.
	 * 
	 * The alias format: original_table_name + referenced_table_name; e.g
	 * "OrderShop" means an alias for a Shop table which is referred by an Order
	 * table
	 * 
	 * @return
	 */
	public String getAlias() {
		if (parentTable == null) {
			return alias == null || alias.isEmpty() ? TableUtil
					.getTableName(table) : alias;
		}
		return alias == null || alias.isEmpty() ? TableUtil
				.getTableName(parentTable) + TableUtil.getTableName(table)
				: alias;
	}

	public String getColumn() {
		return column;
	}

	public Object getValue() {
		return value;
	}

	// TODO optimize this function
	public String getValueString() {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Boolean.class) {
			return String.valueOf(((Boolean) value).booleanValue());
		}
		if (value.getClass() == Long.class) {
			return String.valueOf(((Long) value).longValue());
		}
		if (value.getClass() == Integer.class) {
			return String.valueOf(((Integer) value).intValue());
		}
		if (value.getClass() == Float.class) {
			return String.valueOf(((Float) value).floatValue());
		}
		if (value.getClass() == Timestamp.class) {
			return ((Timestamp) value).getTimestamp().toString();
		}
		if (value.getClass() == List.class) {
			StringBuffer buffer = new StringBuffer();
			int length = ((List<?>) value).size();
			for (int i = 0; i < length; i++) {
				buffer.append("'" + ((List<?>) value).get(i).toString() + "'");
				if (i < length - 1) {
					buffer.append(",");
				}
			}
		}
		return "'" + value.toString() + "'";
	}

	public String getOperatorString() {
		return operator.toString();
	}

	public SqlFunction getFunction() {
		return function;
	}

	public String getOrderString() {
		return isASC == true ? "ASC" : "DESC";
	}
}
