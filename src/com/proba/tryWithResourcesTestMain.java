package com.proba;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class tryWithResourcesTestMain {

    private static class AutoCloseable1 implements AutoCloseable {

        private boolean supposedToCrashOnClose;

        AutoCloseable1(boolean suposedToCrash) {
            supposedToCrashOnClose = suposedToCrash;
        }

        @Override
        public void close() throws Exception {
            System.out.println("Closing AutoCloseable1");
            if (supposedToCrashOnClose) {
                throw new RuntimeException("Crashing A1");
            }
            System.out.println("Closed AutoCloseable1");
        }
    }

    private static class AutoCloseable2 implements AutoCloseable {
        private boolean supposedToCrashOnClose;

        AutoCloseable2(boolean suposedToCrash) {
            supposedToCrashOnClose = suposedToCrash;
        }

        @Override
        public void close() throws Exception {
            System.out.println("Closing AutoCloseable2");
            if (supposedToCrashOnClose) {
                throw new RuntimeException("Crashing A2");
            }
            System.out.println("Closed AutoCloseable2");
        }
    }

    private static class AutoCloseable3 implements AutoCloseable {
        private boolean supposedToCrashOnClose;

        AutoCloseable3(boolean suposedToCrash) {
            supposedToCrashOnClose = suposedToCrash;
        }

        @Override
        public void close() throws Exception {
            System.out.println("Closing AutoCloseable3");
            if (supposedToCrashOnClose) {
                throw new RuntimeException("Crashing A3");
            }
            System.out.println("Closed AutoCloseable3");
        }
    }


    static Stack<Integer> stack = new Stack<>();
    private static Integer pop(){
        if(stack.empty()){
            return  null;
        }
        return stack.pop();
    }
    public static void main(String[] args) {

        stack.add(4);
        stack.add(8);
        stack.add(24);
        Integer v = null;
        while((v = pop()) != null){
            System.out.println("Value: " + v);
        }

        try (
                AutoCloseable a1 = new AutoCloseable1(false);
                AutoCloseable a2 = new AutoCloseable2(true);
                AutoCloseable a3 = new AutoCloseable3(false);
        ) {

            System.out.println("Doing something with resources");
            System.out.println(a1);
            System.out.println(a2);
            System.out.println(a3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
