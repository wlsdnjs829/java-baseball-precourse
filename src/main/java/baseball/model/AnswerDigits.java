package baseball.model;

import baseball.utils.ValidUtil;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 숫자 정답 객체
 */
public final class AnswerDigits {

    private static final String EMPTY = "";

    public static final int GAME_ANSWER_SIZE = 3;

    private AnswerDigits() {
        throw new ClassCastException();
    }

    /**
     * 세자리 숫자 추출
     */
    public static List<Integer> getThreeNumberExtraction() {
        final List<Integer> digits = new ArrayList<>();

        while (digits.size() < GAME_ANSWER_SIZE) {
            int digit = Randoms.pickNumberInRange(1, 9);
            addNonDuplicateDigit(digits, digit);
        }

        return digits;
    }

    /* 중복이 아닌 수 추가 */
    private static void addNonDuplicateDigit(List<Integer> digits, int digit) {
        if (digits.contains(digit)) {
            return;
        }

        digits.add(digit);
    }

    /**
     * 중복 되지 않은 숫자 리스트 조회, 유효하지 않을 시 예외 처리
     *
     * @param submitNumber (입력 받은 숫자)
     * @return 유효한 입력된 숫자 리스트
     */
    public static List<Integer> getNonDuplicateDigitsThrowIfInvalid(String submitNumber) {
        ValidUtil.checkValidNumberThrowIfInvalid(submitNumber);
        final String[] arraySubmitNumber = submitNumber.split(EMPTY);
        ValidUtil.throwIllegalArgumentExceptionIfTrue(arraySubmitNumber.length != GAME_ANSWER_SIZE);
        return getValidDigitsThrowIfInvalid(arraySubmitNumber);
    }

    /* 유효한 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private static List<Integer> getValidDigitsThrowIfInvalid(String[] arraySubmitNumber) {
        final List<Integer> digits = new ArrayList<>();

        for (String submitNum : arraySubmitNumber) {
            ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitNum));
            final int digit = Integer.parseInt(submitNum);
            ValidUtil.throwIllegalArgumentExceptionIfTrue(digit < 1 || digit > 9 || digits.contains(digit));
            digits.add(digit);
        }

        return digits;
    }

}
