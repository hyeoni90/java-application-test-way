package com.hyeonah.myway.junit5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * Created by hyeonahlee on 2021-01-05.
 */
//@ExtendWith(FindSlowTestExtension.class)
class ReportExtensionTest {

    int num = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    void create1() {
        System.out.println(this);
        System.out.println(num++);

        assertThat(num).isEqualTo(2);
    }

    @Test
    void create2() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
        System.out.println("create1" + num++);
    }

}
