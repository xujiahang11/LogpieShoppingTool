package com.logpie.framework.db.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.model.LogpieModel;

public class DatabaseUtil {

	private static final Logger logger = Logger.getLogger(DatabaseUtil.class
			.getName());

	/**
	 * get columns of Class c without foreign key columns
	 * 
	 * @param c
	 * @param hasAutogeneratedKey
	 * @return column names with table alias or name
	 */
	public static List<String> getBasicColumns(Class<?> c,
			boolean hasAutogeneratedKey) {
		List<String> res = new ArrayList<String>();
		List<Column> columnList = getColumnAnnotations(c, hasAutogeneratedKey);
		for (Column column : columnList) {
			res.add(getTableAliasOrName(c) + "." + column.name());
		}
		return res;
	}

	/**
	 * get all columns of Class c, included basic columns of all referenced
	 * tables
	 * 
	 * @param c
	 * @return column names with table alias or name
	 */
	public static List<String> getAllColumns(Class<?> c) {
		List<String> res = new ArrayList<String>();

		// add columns in current table
		List<Column> keys = getColumnAnnotations(c, true);
		if (keys == null || keys.isEmpty()) {
			logger.log(Level.SEVERE, "cannot find columns in table "
					+ getTableAnnotation(c));
			return null;
		}
		for (Column key : keys) {
			res.add(getTableAliasOrName(c) + "." + key.name());
		}

		// add columns in related tables
		List<ForeignEntity> foreignEntities = getForeignEntityAnnotations(c);
		if (!foreignEntities.isEmpty()) {
			for (ForeignEntity entity : foreignEntities) {
				List<Column> referencedKeys = new ArrayList<Column>();
				referencedKeys.addAll(getColumnAnnotations(
						entity.referencedTable(), true));
				for (Column key : referencedKeys) {
					res.add(getTableAliasOrName(entity.referencedTable()) + "."
							+ key.name());
				}
			}
		}

		return res;
	}

	// TODO add foreign key pair
	public static Map<String, String> getColumnAndValuePairs(LogpieModel model,
			boolean hasAutogeneratedKey) {

		StringBuffer keyBuffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		Map<String, Object> modelMap = getModelMap(model, hasAutogeneratedKey);
		if (modelMap == null || modelMap.isEmpty()) {
			logger.log(Level.SEVERE, "cannot get model map in table "
					+ getTableAnnotation(model.getClass()));
			return null;
		}

		for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
			// check value of corresponding property is null
			if (entry.getValue() == null) {
				continue;
			}
			if (keyBuffer.length() > 0) {
				keyBuffer.append(", ");
				valueBuffer.append(", ");
			}

			keyBuffer.append(entry.getKey());

			Class<?> valueType = getBasicColumnTypeByName(model.getClass(),
					entry.getKey());
			if (valueType == null) {
				logger.log(Level.SEVERE, entry.getKey()
						+ " has no corresponding columns");
				return null;
			} else if (valueType == Boolean.class) {
				Boolean value = (Boolean) entry.getValue();
				valueBuffer.append(value.booleanValue());
			} else if (valueType == Long.class) {
				Long value = (Long) entry.getValue();
				valueBuffer.append(value.longValue());
			} else if (valueType == Integer.class) {
				Integer value = (Integer) entry.getValue();
				valueBuffer.append(value.intValue());
			} else if (valueType == Float.class) {
				Float value = (Float) entry.getValue();
				valueBuffer.append(value.floatValue());
			} else if (valueType == Timestamp.class) {
				Timestamp value = (Timestamp) entry.getValue();
				valueBuffer.append(value.getTimestamp().toString());
			} else {
				valueBuffer.append("'");
				valueBuffer.append(entry.getValue().toString());
				valueBuffer.append("'");
			}
		}

		Map<String, String> res = new HashMap<String, String>();
		res.put("column", keyBuffer.toString());
		res.put("value", valueBuffer.toString());
		return res;
	}

	public static Class<?> getBasicColumnTypeByName(Class<?> c, String name) {
		List<Column> columnList = getColumnAnnotations(c, true);
		for (Column column : columnList) {
			if (column.name().equals(name)) {
				switch (column.type()) {
				case BOOLEAN:
					return Boolean.class;
				case INT:
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
			List<ForeignEntity> entityList = getForeignEntityAnnotations(c);
			for (ForeignEntity entity : entityList) {
				if (entity.name().equals(name)) {
					return Long.class;
				}
			}
		}
		logger.log(Level.WARNING,
				"cannot find " + name + " column in " + c.getName());
		return null;
	}

	public static String getID(Class<?> c) {
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

	public static String getTableName(Class<?> c) {
		if (getTableAnnotation(c) == null) {
			logger.log(Level.WARNING, "cannot find any table");
			return null;
		}
		return getTableAnnotation(c).name();
	}

	/**
	 * build a map between database keys and java entities
	 * 
	 * @param model
	 *            is related java entity instance
	 * @param hasAutogeneratedKey
	 *            determines whether the map contains auto-generated keys
	 * @return a hash-map which is mapping database key to java entity, where
	 *         key is database key name and value is java entity
	 */
	public static Map<String, Object> getModelMap(LogpieModel model,
			boolean hasAutogeneratedKey) {
		final Map<String, Object> result = new HashMap<String, Object>();
		Field[] fields = model.getClass().getDeclaredFields();
		for (Field field : fields) {
			// find every field of this model corresponding database column
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (!hasAutogeneratedKey
						&& field.isAnnotationPresent(AutoGenerate.class)) {
					continue;
				}
				// get value of this field
				Object value = runGetter(field, model);
				result.put(column.name(), value);
			}
			// find every field of model corresponding foreign key
			else if (field.isAnnotationPresent(ForeignEntity.class)) {
				// get this foreign table model
				LogpieModel entity = (LogpieModel) runGetter(field, model);
				ForeignEntity column = field.getAnnotation(ForeignEntity.class);
				// get fields of table
				Field[] referencedFields = column.referencedTable()
						.getDeclaredFields();
				for (Field f : referencedFields) {
					// find the field representing ID of foreign table
					if (f.isAnnotationPresent(ID.class)) {
						result.put(column.name(), runGetter(f, entity));
						break;
					}
				}
			}

		}
		return result;
	}

	protected static List<Column> getColumnAnnotations(Class<?> c,
			boolean hasAutogeneratedKey) {
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

	protected static List<ForeignEntity> getForeignEntityAnnotations(Class<?> c) {
		List<ForeignEntity> res = new ArrayList<ForeignEntity>();

		List<ForeignEntity> keys = new ArrayList<ForeignEntity>();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignEntity.class)) {
				ForeignEntity column = field.getAnnotation(ForeignEntity.class);
				keys.add(column);
				res.add(column);
			}
		}
		while (!keys.isEmpty()) {
			List<ForeignEntity> moreKeys = new ArrayList<ForeignEntity>();
			for (ForeignEntity key : keys) {
				Class<?> tableClass = key.referencedTable();
				if (hasForeignEntityAnnotation(tableClass)) {
					moreKeys.addAll(getForeignEntityAnnotations(tableClass));
				}
			}
			keys = moreKeys;
			res.addAll(keys);
		}
		return res;
	}

	protected static boolean hasForeignEntityAnnotation(Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignEntity.class)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param c
	 * @return table alias if alias existed, otherwise will return table name
	 */
	protected static String getTableAliasOrName(Class<?> c) {
		if (getTableAnnotation(c) == null) {
			logger.log(Level.WARNING, "cannot find any table");
			return null;
		}
		return getTableAnnotation(c).tableAlias().isEmpty() ? getTableAnnotation(
				c).name()
				: getTableAnnotation(c).tableAlias();
	}

	protected static boolean hasTableAlias(Class<?> c) {
		if (getTableAnnotation(c) == null
				|| getTableAnnotation(c).tableAlias().isEmpty()) {
			return false;
		}
		return true;
	}

	protected static Table getTableAnnotation(Class<?> c) {
		if (c.isAnnotationPresent(Table.class)) {
			return c.getAnnotation(Table.class);
		}
		return null;
	}

	private static Object runGetter(Field field, Object model) {
		for (Method method : model.getClass().getMethods()) {
			if (method.getName().startsWith("get")
					&& method.getName().length() == field.getName().length() + 3) {
				if (method.getName().toLowerCase()
						.endsWith(field.getName().toLowerCase())) {
					try {
						return method.invoke(model);
					} catch (IllegalAccessException e) {
						logger.log(Level.SEVERE,
								"cannot get access to this method");
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						logger.log(Level.SEVERE,
								"passed illegal argument to this method");
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						logger.log(Level.SEVERE,
								"this method cannot be invoked");
						e.printStackTrace();
					}
				}
			}
		}
		logger.log(Level.WARNING, "cannot find getter method for this field");
		return null;
	}
}
