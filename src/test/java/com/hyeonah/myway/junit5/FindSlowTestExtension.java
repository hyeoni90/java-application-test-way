package com.hyeonah.myway.junit5;

import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * Created by hyeonahlee on 2021-01-05.
 *
 * Find the slow running test code using lifecycle callbacks.
 * and Note to write the @SlowTest.
 *
 * References
 * {@link} https://junit.org/junit5/docs/current/user-guide/#extensions
 */
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME = "START_TIME";
//    private static final long THRESHOLD = 1000L;

    private final long THRESHOLD;

    public FindSlowTestExtension(final long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {
        final ExtensionContext.Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        /* Java Reflection */
        final Method requiredTestMethod = context.getRequiredTestMethod();
        final SlowTest slowTestAnnotation = requiredTestMethod.getAnnotation(SlowTest.class);

        final String testMethodName = requiredTestMethod.getName();
        final ExtensionContext.Store store = getStore(context);

        /*  Get and Remove START_TIME value in Store */
        final long startTime = store.remove(START_TIME, long.class);
        final long duration = System.currentTimeMillis() - startTime;

        if(duration > THRESHOLD && slowTestAnnotation == null) {
            System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodName);
        }
    }

    /**
     * Get value of Store
     * @param context
     * @return
     */
    private Store getStore(final ExtensionContext context) {
        final String testClassName = context.getRequiredTestClass().getName();
        final String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(Namespace.create(testClassName, testMethodName));
    }
}
