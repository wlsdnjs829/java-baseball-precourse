package baseball.utils;

import java.util.Objects;

/**
 * 유효성 체크 유틸
 */
public final class ValidUtil {

    private static final String NUMBER_PATTERN = "[+-]?\\d*(\\.\\d+)?";

    private ValidUtil() {
        throw new ClassCastException();
    }

    /**
     * 참인 경우, IllegalArgumentException 처리
     */
    public static void throwIllegalArgumentExceptionIfTrue(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 유효한 숫자 패턴인지 확인, 유효하지 않을 시 예외 처리
     *
     * @param number (숫자 문자열)
     */
    public static void checkValidNumberThrowIfInvalid(String number) {
        if (Objects.isNull(number)) {
            throw new IllegalArgumentException();
        }

        throwIllegalArgumentExceptionIfTrue(!number.matches(NUMBER_PATTERN));
    }

}
