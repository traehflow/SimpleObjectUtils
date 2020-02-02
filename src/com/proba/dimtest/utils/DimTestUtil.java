package com.proba.dimtest.utils;

import com.proba.SimpleObjectUtil;
import com.proba.dimtest.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DimTestUtil {

    private static class AssertFailedException extends RuntimeException {

        public AssertFailedException(){

        }
    }

    public static void assertTrue(boolean condition) {
        if(!condition) {
            throw new AssertFailedException();
        }
    }

    public static void assertFalse(boolean condition) {
        if(condition) {
            throw new AssertFailedException();
        }
    }


    private static List<Method> retrieveTestMethods(Object instance) {
        return SimpleObjectUtil.filterAllInstanceMethods(instance)
                .filter(x -> Arrays.stream(x.getAnnotations()).anyMatch(y -> y instanceof Test))
                .filter(x -> Modifier.isPublic(x.getModifiers()))
                .collect(Collectors.toList());
    }

    public static void testClass(Class clasz) {

        try {
            Constructor<?> constructor = clasz.getConstructor();
            Object instance = constructor.newInstance();
            runAllTestMethods(instance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static void runAllTestMethods(Object instance) {
        for (Method m : retrieveTestMethods(instance)) {
            Optional<Annotation> t = Arrays.stream(m.getAnnotations()).filter(x -> x instanceof Test).findFirst();
            Test test = null;
            Class<? extends Throwable> expected = null;
            if(t.isPresent()){
                test = ((Test)t.get());
                expected = test.expected();
            }
            t.get().annotationType();
            try {
                m.invoke(instance);

                if(!expected.equals(Test.None.class) ){
                    System.out.println( "Test " + m.getName() + " failed. Expected exception not thrown: " + expected.getName());
                }

            }catch (AssertFailedException e){
                System.out.println( "Test " + m.getName() + " failed.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if(e.getCause() instanceof  AssertFailedException) {
                    System.out.println( "Test " + m.getName() + " failed.");
                } else {
                    if(expected.equals(Test.None.class) ){
                        System.out.println( "Test " + m.getName() + " failed. Unexpected exception thrown: " + e.getClass().getName());
                    } else {
                        if(expected.getClass().equals(e.getClass())){
                            System.out.println( "Test " + m.getName() + " passed.");
                        } else {
                            System.out.println( "Test " + m.getName() + " failed. expected " + expected.getName() + " thrown " + e.getClass().getName());
                        }
                    }
                    e.printStackTrace();
                }
            }
        }
    }

}
