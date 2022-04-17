package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

/**
 * 숫자 야구 객체
 */
public class BaseBallGame {

    private final List<Integer> answer;

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

}
