package com.hyeonah.myway.junit5;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Created by hyeonahlee on 2021-01-03.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
class InstanceTest {

    int num = 10;

    @Test
    void create_one() {
        System.out.println(this);
        System.out.println(num++);
        System.out.println(num);
    }

    @Test
    void create_two() {
        System.out.println(this);
        System.out.println(num++);
        System.out.println(num);
    }

}
