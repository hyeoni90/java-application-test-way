package com.hyeonah.myway.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Created by hyeonahlee on 2021-01-03.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InstanceWithOrderTest {

    int num = 1;

    @Order(3)
    @Test
    void create_third() {
        System.out.println(this);
        System.out.println(num++);

        assertThat(num).isEqualTo(4);

    }

    @Order(1)
    @Test
    void create_first() {
        System.out.println(this);
        System.out.println(num++);

        assertThat(num).isEqualTo(2);
    }

    @Order(2)
    @Test
    void create_second() {
        System.out.println(this);
        System.out.println(num++);

        assertThat(num).isEqualTo(3);
    }
}
