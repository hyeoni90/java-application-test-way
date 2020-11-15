package com.hyeonah.myway;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReportTest {

    @Test
    @DisplayName("보고서 만들기 >_<")
    void create_new_report() {
        Report report = new Report(-10);

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
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Report(-20));

        assertEquals("limit은 0 보다 커야 합니다.", exception.getMessage());
    }

    @Test
    void create2() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Report(10);
            Thread.sleep(300);
        });
    }

    @Test
    void create3() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Report(10);
            Thread.sleep(300);
        });

        // TODO: 2020/11/15 ThreadLocal, transaction과 별도의 thread로 실행 됨
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
