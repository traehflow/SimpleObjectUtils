package com.proba;

import com.proba.dimtest.annotations.Test;
import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.io.IOException;

public class SampleTestClass {

    @Test
    public void testSomehting(){
        System.out.println("Test something.");
    }

    @Test
    public void testSomehting2(){
        System.out.println("Test something 2.");
    }

    @Test(expected = IOException.class)
    public void testSomethingThrowable(){
        System.out.println("Test something throwable.");
    }


    public void publicTestHelper(){
        System.out.println("publicTestHelper");
    }

    private void privateTestHelper(){
        System.out.println("privateTestHelper");
    }
}
