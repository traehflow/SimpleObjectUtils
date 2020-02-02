package com.proba;

import com.proba.dimtest.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleObjectUtil {

    @FunctionalInterface
    public interface MethodWorker {
        String method(Method m);
    }

    public static String defaultToString(Object instance) {
        StringBuilder sb = new StringBuilder();
        String s = Arrays.stream(instance.getClass().getDeclaredFields())
                .filter(x -> !Modifier.isFinal(x.getModifiers())
                        && !Modifier.isStatic(x.getModifiers())
                        && !Modifier.isTransient(x.getModifiers()))
                .map(
                        x -> {
                            try {
                                return x.getName() + ":" + x.get(instance);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                ).collect(Collectors.joining(", "));
        sb.append("[");
        sb.append(s);
        sb.append("]");

        return sb.toString();
    }

    public static Stream<Method> filterAllInstanceMethods(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(x -> !Modifier.isFinal(x.getModifiers())
                        && !Modifier.isStatic(x.getModifiers())
                        && !Modifier.isTransient(x.getModifiers())
                );
    }


    private static Stream<Method> filterAllGetters(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(x -> !Modifier.isFinal(x.getModifiers())
                        && !Modifier.isStatic(x.getModifiers())
                        && !Modifier.isTransient(x.getModifiers()))
                .filter(x -> x.getName().startsWith("get")
                        && !x.getName().equals("get")
                        && x.getParameterCount() == 0
                );
    }

    public static String dtoToString(Object instance) {
        StringBuilder sb = new StringBuilder();
        String s = filterAllGetters(instance)
                .map(
                        x -> {
                            try {
                                return propertyFromGetter(x) + ":" + x.invoke(instance);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                ).collect(Collectors.joining(", "));
        sb.append("[");
        sb.append(s);
        sb.append("]");

        return sb.toString();
    }

    private static boolean simpleEquals(Object o1, Object o2) {
        if (o1 ==  o2) return true;
        if (o1 == null || o2 == null) return false;
        return Objects.equals(o1, o2);
    }

    public static boolean equalsDtoObjects(Object o1, Object o2) {
        if (o1 == o2) return true;
        if (o1 == null || o2 == null) return false;
        if(!o1.getClass().equals(o2.getClass())) return false;
        Boolean result = filterAllGetters(o1).allMatch( x -> {
            try {
                return Objects.equals(x.invoke(o1), x.invoke(o2));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        });
        return result;


    }

    private static String propertyFromGetter(Method method) {
        return Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4);
    }


}
