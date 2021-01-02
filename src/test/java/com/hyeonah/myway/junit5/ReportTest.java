package com.hyeonah.myway.junit5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/**
 *
 * Description )
 *
 * Tag annotation: 테스트 그룹을 만들고, 해당 그룹만 테스트를 실행 할 수 있는 기능
 *
 *  - 테스트 메소드에 태그 추가 가능
 *  - 하나의 테스트 메소에 여러 태그 가능
 *  - cf. intellij or maven 에서 테스트 필터리 설정 가능
 *
 * References
 * - @link https://junit.org/junit5/docs/current/user-guide/#running-tests-tag-expressions
 * - @link https://www.baeldung.com/junit-filtering-tests
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ReportTest {

    @Test
    @DisplayName("보고서 만들기 >_<")
    void create_new_report() {
        final Report report = new Report(-10);

//        assertNotNull(report);
//        assertEquals(ReportStatus.DRAFT, report.getStatus(), () -> "보고서를 처음 만들면 " + ReportStatus.DRAFT + " 상태임!");
//        assertTrue(report.getLimit() > 0, () -> "보고서 최대 장수는 0보다 커야합니다.");

        // 테스트를 하나로 묶을 수 있음!
        assertAll(
                () -> assertNotNull(report),
                () -> assertEquals(ReportStatus.DRAFT, report.getStatus(),
                        () -> "보고서를 처음 만들면 " + ReportStatus.DRAFT + " 상태임!"),
                () -> assertTrue(report.getLimit() > 0, () -> "보고서 최대 장수는 0보다 커야합니다.")
        );
    }

    @Test
    void create1() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Report(-20));

        assertEquals("limit은 0 보다 커야 합니다.", exception.getMessage());
    }

    @Disabled
    @Test
    void create2() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Report(10);
            Thread.sleep(300);
        });
    }

    @Disabled
    @Test
    void create3() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Report(10);
            Thread.sleep(300);
        });

        // TODO: 2020/11/15 ThreadLocal, transaction과 별도의 thread로 실행 됨
    }

    @Test
    @DisplayName("assumeTrue 테스트")
    void create_test_env() {
        final String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv(test_env)));

        final Report report = new Report(10);
        assertThat(report.getLimit()).isGreaterThan(0);
    }

    @Test
    void create_assumingThat() {
        final String test_env = System.getenv("TEST_ENV");
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });

        assumingThat("hyeonah".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("EnabledOnOs 어노테이션으로 테스트 운영체제가 특정 OS 특화 테스트")
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void create_enabledOnOs() {
        final String test_env = System.getenv("TEST_ENV");
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });

        assumingThat("hyeonah".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("EnabledOnOs 어노테이션으로 특정 OS 테스트")
    @EnabledOnOs(OS.MAC)
    void create_enabled_on_mac() {
        final String test_env = System.getenv("TEST_ENV");
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });

        assumingThat("hyeonah".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("EnabledOnJre 어노테이션을 활용한 Java 버전 테스트")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    void create_enabled_on_jre() {
        final String test_env = System.getenv("TEST_ENV");
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv(test_env)),  () -> {
            final Report report = new Report(10);
            assertThat(report.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("EnabledIfEnvironmentVariable 어노테이션을 활용한 테스트")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "TEST_ENV")
    void create_enabled_if_env() {
        System.out.println("create_enabled_if_env");
    }

    @Test
    @DisplayName("tag - fast 테스트 ╯°□°）╯ ")
    @Tag("fast")
    void create_fast() {
        final Report report = new Report(100);
        assertThat(report.getLimit()).isGreaterThan(10);
    }

    @Test
    @DisplayName("tag - slow 테스트")
    @Tag("slow")
    void create_slow() {
        final Report report = new Report(100);
        assertThat(report.getLimit()).isGreaterThan(10);
    }

    /**
     * 테스트 클래스 전에 실행
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    /**
     * 테스트 클래스 후에 실행
     */
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    /**
     * 테스트 메서드 전 마다 실행
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    /**
     * 테스트 메서드 후 마다 실행
     */
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}
