package baseball.utils;

/**
 * 유효성 체크 유틸
 */
public class ValidUtil {

    private ValidUtil() {
        throw new ClassCastException();
    }

    /**
     *  참인 경우, IllegalArgumentException 처리
     */
    public static void throwIllegalArgumentExceptionIfTrue(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException();
        }
    }

}
