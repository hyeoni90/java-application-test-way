package com.hyeonah.myway.junit5;

/**
 * Created by hyeonahlee on 2021-01-02.
 */
public class CustomTagTest {

    @FastTest
    public void fast_test() {
        System.out.println("fast");
    }

    @SlowTest
    public void slow_test() {
        System.out.println("slow");
    }
}
