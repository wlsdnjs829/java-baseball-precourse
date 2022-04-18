package baseball.utils;

import java.util.Objects;

/**
 * 문자열 유틸
 */
public class StringUtil {

    private static final String EMPTY = "";
    private static final String NOT_THING = "낫싱";
    private static final String SPACE = " ";

    private StringUtil() {
        throw new ClassCastException();
    }

    /**
     * 문자열 조회, 없는 경우 빈 문자열 반환
     *
     * @param string (문자열)
     * @return NULL인 경우 빈 문자열, NULL이 아닌 경우 해당 문자열 반환
     */
    public static String getStringIfNullIsEmpty(String string) {
        if (Objects.isNull(string)) {
            return EMPTY;
        }

        return string;
    }

    /**
     * 볼 상태 문자열 조합
     *
     * @param ballState   (볼 상태)
     * @param strikeState (스트라이크 상태)
     * @return 상태에 따른 문자열 반환
     */
    public static String combineBallState(String ballState, String strikeState) {
        if (Objects.isNull(ballState) && Objects.isNull(strikeState)) {
            return NOT_THING;
        }

        if (Objects.isNull(ballState)) {
            return strikeState;
        }

        return ballState + SPACE + StringUtil.getStringIfNullIsEmpty(strikeState);
    }

}
