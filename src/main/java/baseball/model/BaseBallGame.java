package baseball.model;

import baseball.utils.ValidUtil;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 숫자 야구 객체
 */
public class BaseBallGame {

    private final List<Integer> answer;

    private static final String ENTER_NUMBER = "숫자를 입력해주세요 : ";
    private static final String EMPTY = "";

    public BaseBallGame() {
        answer = getThreeNumberExtraction();
    }

    /**
     * 세자리 숫자 추출
     */
    public List<Integer> getThreeNumberExtraction() {
        final List<Integer> digits = new ArrayList<>();

        while (digits.size() < 3) {
            int digit = Randoms.pickNumberInRange(1, 9);
            addNonDuplicateDigit(digits, digit);
        }

        return digits;
    }

    /* 중복이 아닌 수 추가 */
    private void addNonDuplicateDigit(List<Integer> digits, int digit) {
        if (digits.contains(digit)) {
            return;
        }

        digits.add(digit);
    }

    /**
     * 게임 시작
     */
    public void gameStart() {
        System.out.print(ENTER_NUMBER);
        final String submitNumber = Console.readLine();
        final Set<Integer> digits = getNonDuplicateDigitsThrowIfInvalid(submitNumber);
        System.out.println(digits);
    }

    /* 중복 되지 않은 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private Set<Integer> getNonDuplicateDigitsThrowIfInvalid(String submitNumber) {
        ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitNumber));

        String[] arraySubmitNumber = submitNumber.split(EMPTY);
        final Set<Integer> digits = getValidDigitsThrowIfInvalid(arraySubmitNumber);

        ValidUtil.throwIllegalArgumentExceptionIfTrue(digits.size() != 3);
        return digits;
    }

    /* 유효한 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private Set<Integer> getValidDigitsThrowIfInvalid(String[] arraySubmitNumber) {
        final Set<Integer> digits = new HashSet<>();

        for (String submitNum : arraySubmitNumber) {
            final int digit = Integer.parseInt(submitNum);
            ValidUtil.throwIllegalArgumentExceptionIfTrue(digit < 1 || digit > 9);
            ValidUtil.throwIllegalArgumentExceptionIfTrue(digits.contains(digit));
            digits.add(digit);
        }

        return digits;
    }


}
