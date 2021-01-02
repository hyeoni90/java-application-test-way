package com.hyeonah.myway.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Created by hyeonahlee on 2021-01-03.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParameterTest {

    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"2021년", "새해", "복", "많이", "받으세요 :-)"})
    void parameterized_test_value (final String message){
        System.out.println(message);
    }

    /**
     * @ConvertWith: 명시적인
     *
     * @param report
     */
    @ParameterizedTest
    @ValueSource(ints = {100, 50, 80})
    void parameterized_test_convert(@ConvertWith(ReportConvert.class) final Report report) {
        System.out.println(report.getLimit());
    }

    @ParameterizedTest
    @CsvSource({"100, '주간 보고'","80, '일일 보고'"})
    void parameterized_csv_test(final Integer limit, final String name) {
        final Report report = new Report(limit, name);
        System.out.println(report.getLimit());
    }

    @ParameterizedTest
    @CsvSource({"100, '주간 보고'","80, '일일 보고'"})
    void parameterized_csv_aggregator(@AggregateWith(ReportAggregator.class) final Report report) {
        System.out.println(report);
    }

    /**
     * The Aggregator should be public class or static class 이어야 쓸 수 있다!
     */
    static class ReportAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(final ArgumentsAccessor accessor, final ParameterContext context)
            throws ArgumentsAggregationException {
            return new Report(accessor.getInteger(0), accessor.getString(1));
        }
    }

    static class ReportConvert extends SimpleArgumentConverter {

        @Override
        protected Object convert(final Object source, final Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Report.class, targetType, "convert to Report");
            return new Report(Integer.parseInt(source.toString()));
        }
    }
}
