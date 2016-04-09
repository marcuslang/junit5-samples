package com.example.project.owntests;


import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExceptionHandlerExtensionPoint;
import org.junit.gen5.api.extension.ExtendWith;
import org.junit.gen5.api.extension.TestExtensionContext;
import org.junit.gen5.junit4.runner.JUnit5;
import org.junit.runner.RunWith;

@RunWith(JUnit5.class)
public class JunitTest001 {

    @Test
    public void testWithException001() {
        Thread thread = null;
        Assertions.assertThrows(NullPointerException.class, () -> thread.start());
    }

    @Test
    @ExtendWith(IgnoreNPEExtension.class)
    public void testWithException002() {
        Thread thread = null;
        thread.start();
    }

    @Test
    @ExtendWith(IgnoreNPEExtension.class)
    public void testWithException003() {
        final int[] array = new int[1];
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int result = array[2];
        });
    }


}

class IgnoreNPEExtension implements ExceptionHandlerExtensionPoint {

    @Override
    public void handleException(TestExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof NullPointerException) {
            System.out.println("catched NPE");
            return;
        }
        throw throwable;
    }
}