package com.logpie.framework.db.support;

import com.logpie.framework.db.basic.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {

    /**
     * Build a object by using its default constructor
     *
     * @param clazz
     * @param <T>
     * @return null if can't build the object.
     */
    public static Object buildInstanceByDefaultConstructor(Class<?> clazz) {

        try {
            final Constructor constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException ex) {
            System.out.println("No default constructor! You need to create a default constructor with no parameters");
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            System.out.println("Default constructor can't be accessed. Please set your default constructor as public.");
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            System.out.println("Can't instantiate the class.");
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            System.out.println("InvocationTargetException when calling the constructor");
            ex.printStackTrace();
        }
        return null;
    }
}
