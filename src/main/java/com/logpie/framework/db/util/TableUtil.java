package com.logpie.framework.db.util;

import java.lang.reflect.Field;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.ForeignKey;

public class TableUtil {

	private static final Logger logger = Logger.getLogger(TableUtil.class
			.getName());

	public static String getTableName(final Class<?> c) {
		if (getTableAnnotation(c) == null) {
			logger.log(Level.WARNING, "cannot find any table");
			return null;
		}
		return getTableAnnotation(c).name();
	}

	public static String getId(final Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ID.class)
					&& field.isAnnotationPresent(Column.class)) {
				return field.getAnnotation(Column.class).name();
			}
		}
		logger.log(Level.WARNING, "cannot find any ID from this table");
		return null;
	}

	/**
	 * get columns of table c
	 * 
	 * @param c
	 * @param hasForeignKey
	 * @param hasAutogeneratedKey
	 * @param tableAlias
	 * @return table name + "." + column names
	 */
	public static List<String> getColumns(final Class<?> c,
			final boolean hasForeignKey, final boolean hasAutogeneratedKey,
			final String tableAlias) {
		List<String> res = new ArrayList<String>();

		if (getTableName(c) == null || getTableName(c).isEmpty()) {
			return null;
		}
		String table = (tableAlias == null || tableAlias.equals("")) ? getTableName(c)
				: tableAlias;

		List<Column> columns = getColumnAnnotations(c, hasAutogeneratedKey);
		if (columns == null || columns.isEmpty()) {
			logger.log(Level.WARNING, "cannot find columns in table "
					+ getTableName(c));
			return null;
		}
		for (Column column : columns) {
			res.add(table + "." + column.name());
		}

		if (hasForeignKey) {
			List<ForeignEntity> foreignKeys = getForeignEntityAnnotations(c);
			if (foreignKeys != null && !foreignKeys.isEmpty()) {
				for (ForeignEntity foreignKey : foreignKeys) {
					res.add(table + "." + foreignKey.name());
				}
			}
		}
		return res;
	}

	/**
	 * get all columns from table c and all other referenced tables
	 * 
	 * @param c
	 * @param tableAlias
	 * @return column names with table alias or name
	 */
	public static List<String> getAllColumns(final Class<?> c) {
		List<String> res = new ArrayList<String>();
		res.addAll(getColumns(c, true, true, null));

		List<ForeignKey> foreignKeys = getAllForeignKeys(c, null);
		for (ForeignKey key : foreignKeys) {
			ForeignEntity entity = key.getForeignEntity();
			Class<?> foreignTable = entity.referencedTable();
			String foreignAlias = getAliasOfForeignTable(key);
			res.addAll(getColumns(foreignTable, true, true, foreignAlias));
		}

		return res;
	}

	/**
	 * 
	 * @param c
	 * @param alias
	 *            set alias of table c
	 * @return
	 */
	public static List<ForeignKey> getAllForeignKeys(final Class<?> c,
			final String alias) {
		List<ForeignKey> res = new ArrayList<ForeignKey>();
		String table = getTableName(c);
		if (table == null || table.isEmpty()) {
			return null;
		}

		List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
		// add foreign keys of original table c
		List<ForeignEntity> foreignEntities = getForeignEntityAnnotations(c);
		for (ForeignEntity entity : foreignEntities) {
			foreignKeys.add(new ForeignKey(table, alias, entity));
		}

		// check if each foreign table has more foreign keys
		while (!foreignKeys.isEmpty()) {
			List<ForeignKey> moreKeys = new ArrayList<ForeignKey>();
			for (ForeignKey key : foreignKeys) {
				Class<?> referencedTable = key.getForeignEntity()
						.referencedTable();
				if (hasForeignEntityAnnotation(referencedTable)) {
					String referencedAlias = getAliasOfForeignTable(key);
					List<ForeignEntity> moreEntities = getForeignEntityAnnotations(referencedTable);
					for (ForeignEntity entity : moreEntities) {
						moreKeys.add(new ForeignKey(
								getTableName(referencedTable), referencedAlias,
								entity));
					}
				}
				res.add(key);
			}
			foreignKeys = moreKeys;
		}

		return res;
	}

	/**
	 * get an alias of a foreign key's table or auto generate one if it does not
	 * have any alias.
	 * 
	 * Auto-generated Alias Format
	 * 
	 * if the original table has an alias, use original_table_alias +
	 * foreign_table_name, otherwise use original_table_name +
	 * foreign_table_name;
	 * 
	 * e.g "OrderShop" means an alias for a Shop table which is referred by an
	 * Order table
	 * 
	 * @param key
	 * @return
	 */
	public static String getAliasOfForeignTable(final ForeignKey key) {
		String originalAlias = key.getForeignEntity().referencedTableAlias();
		if (originalAlias == null || originalAlias.equals("")) {
			String tmp = getTableName(key.getForeignEntity().referencedTable());
			return key.getAlias() == null || key.getAlias().equals("") ? key
					.getTable() + tmp : key.getAlias() + tmp;
		} else {
			return originalAlias;
		}
	}

	public static Class<?> getColumnTypeByName(final Class<?> c,
			final String name) {
		List<Column> columnList = getColumnAnnotations(c, true);
		for (Column column : columnList) {
			if (column.name().equals(name)) {
				switch (column.type()) {
				case BOOLEAN:
					return Boolean.class;
				case INTEGER:
					return Integer.class;
				case LONG:
					return Long.class;
				case FLOAT:
					return Float.class;
				case TIMESTAMP:
					return Timestamp.class;
				default:
					return String.class;
				}
			}
		}
		if (hasForeignEntityAnnotation(c)) {
			List<ForeignEntity> entities = getForeignEntityAnnotations(c);
			for (ForeignEntity entity : entities) {
				if (entity.name().equals(name)) {
					return Long.class;
				}
			}
		}
		logger.log(Level.WARNING,
				"cannot find " + name + " column in " + c.getName());
		return null;
	}

	private static Table getTableAnnotation(final Class<?> c) {
		if (c.isAnnotationPresent(Table.class)) {
			return c.getAnnotation(Table.class);
		}
		return null;
	}

	private static List<Column> getColumnAnnotations(final Class<?> c,
			final boolean hasAutogeneratedKey) {
		List<Column> res = new ArrayList<Column>();

		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (!hasAutogeneratedKey
						&& field.isAnnotationPresent(AutoGenerate.class)) {
					continue;
				}
				res.add(column);
			}
		}
		return res;
	}

	private static List<ForeignEntity> getForeignEntityAnnotations(
			final Class<?> c) {
		List<ForeignEntity> res = new ArrayList<ForeignEntity>();

		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignEntity.class)) {
				ForeignEntity column = field.getAnnotation(ForeignEntity.class);
				res.add(column);
			}
		}
		return res;
	}

	private static boolean hasForeignEntityAnnotation(final Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignEntity.class)) {
				return true;
			}
		}
		return false;
	}
}
