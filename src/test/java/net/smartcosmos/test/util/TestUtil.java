package net.smartcosmos.test.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.*;
import org.mockito.verification.VerificationMode;
import org.springframework.test.util.AopTestUtils;

/**
 * Utility methods for unit tests.
 */
public class TestUtil {

    public static byte[] json(Object object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    /**
     * <p>Verifies certain behavior happened at least once / exact number of times / never.</p>
     * <p>This is a helper method that uses {@link AopTestUtils} to work around an issue with Mockito and AOP like {@code @Retryable} that requires
     * unwrapping if the mock is created using {@code mock()}.</p>
     *
     * @param mock the mock
     * @param mode the mode
     * @param <T> the type of the wrapped object
     * @return mock object itself
     * @see Mockito#verify
     */
    public static <T> T unwrapAndVerify(T mock, VerificationMode mode) {

        return ((T) Mockito.verify(AopTestUtils.getTargetObject(mock), mode));
    }
}
