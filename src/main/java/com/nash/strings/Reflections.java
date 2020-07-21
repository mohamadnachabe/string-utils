package com.nash.strings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;

public class Reflections {

    public static String asString(Object object) {
        return asString(object, "");
    }

    private static String asString(Object object, String trailing) {

        StringBuilder stringBuilder = new StringBuilder();

        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        for(Field field : declaredFields) {
            String fieldName = field.getName();

            Object fieldValue = getValue(object, aClass, fieldName);

            if (field.getType().isPrimitive() || field.getType() == String.class) {

                stringBuilder.append(trailing);
                stringBuilder.append(String.format("%s = %s\n", fieldName, fieldValue));

            } else {

                String join = String.join("", Collections.nCopies(fieldName.length() + 4, " "));
                stringBuilder.append(trailing);
                stringBuilder.append(fieldName);
                stringBuilder.append(" = ");

                if (fieldValue == null) {
                    stringBuilder.append("null");
                } else {
                    stringBuilder.append('\n');
                    stringBuilder.append(asString(fieldValue, trailing.concat(join)));
                }
            }
        }

        return stringBuilder.toString();
    }

    private static Object getValue(Object object, Class<?> aClass, String fieldName) {
        String getterMethod = String.format(
                "get%s%s", String.valueOf(fieldName.charAt(0)).toUpperCase(), fieldName.substring(1));

        Object fieldValue;
        try {
            Method method = aClass.getMethod(getterMethod);
            fieldValue = method.invoke(object);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return fieldValue;
    }

}
