package com.logpie.framework.db.support;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.Assert;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.KVP;
import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.Table;
import org.springframework.util.StringUtils;

public class ModelUtil {

	private static final Logger logger = Logger.getLogger(ModelUtil.class
			.getName());
	private static final String SET = "set";
	private static final String GET = "get";

	/**
	 * get a list of Key-Value Pair objects for model
	 * 
	 * @param model
	 *            is related java entity instance
	 * @param hasAutogeneratedKey
	 *            determines whether the map contains auto-generated keys
	 * @return a list of KVP objects, where key is database key name and value
	 *         is java entity
	 */
	public static List<KVP> getModelKVP(final Model model,
			final boolean hasAutogeneratedKey) {
		Assert.notNull(model, "Model must not be null");

		Table table = new Table(model.getClass());
		List<KVP> result = new ArrayList<>();

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
				result.add(new KVP(table, column.name(), value));
			}
			// find every field of model corresponding foreign key
			else if (field.isAnnotationPresent(ForeignKeyColumn.class)) {

				// get this foreign table model
				Model entity = (Model) runGetter(field, model);
				ForeignKeyColumn column = field.getAnnotation(ForeignKeyColumn.class);

				if (entity == null) {
					result.add(new KVP(table, column.name(), null));
				} else {
					// get fields of table
					Field[] referencedFields = column.referencedEntityClass()
							.getDeclaredFields();

					for (Field f : referencedFields) {
						// find the field representing ID of foreign table
						if (f.isAnnotationPresent(ID.class)) {
							result.add(new KVP(table, column.name(), runGetter(
									f, entity)));
							break;
						}
					}
				}
			}

		}
		return result;
	}

	public static Long getIdValue(final Model model) {
		Assert.notNull(model, "Model must not be null");

		Field[] fields = model.getClass().getDeclaredFields();
		for (Field field : fields) {

			if (field.isAnnotationPresent(ID.class)
					&& field.isAnnotationPresent(Column.class)) {
				return (Long) runGetter(field, model);
			}
		}
		logger.log(Level.WARNING, "cannot find any ID from this table");
		return null;
	}

	private static Object runGetter(final Field field, final Model model) {
		Assert.notNull(model, "Model must not be null");

		for (Method method : model.getClass().getMethods()) {
			final String currentMethodName = method.getName().toLowerCase();
			final String targetMethodName = (GET + field.getName()).toLowerCase();
			if (currentMethodName.equals(targetMethodName)) {
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

		logger.log(Level.WARNING, "cannot find getter method for this field");
		return null;
	}

	public static void runSetter(final Field field, final Object model, final Class<?> valueClazz, final Object value) {
		Assert.notNull(model, "Model must not be null");

		final String targetMethodName = SET + StringUtils.capitalize(field.getName());

		try {
			final Method setMethod = model.getClass().getMethod(targetMethodName, valueClazz);
			setMethod.invoke(model, value);
			return;
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE,
					"Cannot get access to this method, you need to set public to the setters");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE,
					"Passed illegal argument to this method:" + targetMethodName);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.log(Level.SEVERE,
					"This method cannot be invoked" + targetMethodName);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.log(Level.SEVERE, "cannot find setter method: " + targetMethodName);
			e.printStackTrace();
		}

	}
}
