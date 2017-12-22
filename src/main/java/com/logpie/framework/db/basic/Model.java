package com.logpie.framework.db.basic;

import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.support.ModelUtil;
import com.logpie.framework.db.support.ReflectionUtil;
import org.springframework.jdbc.core.RowMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

public class Model implements RowMapper<Model> {

    @Override
    public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Class clazz = this.getClass();
        final Model mappedObject = (Model)ReflectionUtil.buildInstanceByDefaultConstructor(clazz);
        // Call setter methods.
        if (mappedObject != null) {
            Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
                // Check if the current filed is mapped column filed
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    Column annotation = field.getAnnotation(Column.class);
                    Column.DataType dataType = annotation.type();
                    try {
                        switch (dataType)
                        {
                            case STRING: ModelUtil.runSetter(field, mappedObject, String.class, rs.getString(annotation.name()));break;
                            case LONG: ModelUtil.runSetter(field, mappedObject, Long.class, rs.getLong(annotation.name()));break;
                            case TIMESTAMP:ModelUtil.runSetter(field, mappedObject, Timestamp.class, rs.getTimestamp(annotation.name()));break;
                            case FLOAT: ModelUtil.runSetter(field, mappedObject, Float.class, rs.getFloat(annotation.name()));break;
                            case BOOLEAN: ModelUtil.runSetter(field, mappedObject, Boolean.class, rs.getBoolean(annotation.name()));break;
                            case INTEGER: ModelUtil.runSetter(field, mappedObject, Integer.class, rs.getInt(annotation.name()));break;
                        }

                    } catch (SQLException e) {
                        System.out.println("SQLException when trying to get object from ResultSet for key: " + columnAnnotation.name());
                        e.printStackTrace();
                    }
                }
                else {
                    ForeignKeyColumn foreignKeyColumnAnnotation = field.getAnnotation(ForeignKeyColumn.class);
                    if(foreignKeyColumnAnnotation != null) {
                        try {
                            final Class foreignEntityClass = foreignKeyColumnAnnotation.referencedEntityClass();
                            final RowMapper foreignEntityClassRowMapper = (RowMapper) ReflectionUtil.buildInstanceByDefaultConstructor(foreignEntityClass);
                            ModelUtil.runSetter(field, mappedObject, foreignEntityClass, foreignEntityClassRowMapper.mapRow(rs, rowNum));
                        } catch (SQLException e) {
                            System.out.println("SQLException when trying to get object from ResultSet for key: " + foreignKeyColumnAnnotation.name());
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return mappedObject;
    }
}
