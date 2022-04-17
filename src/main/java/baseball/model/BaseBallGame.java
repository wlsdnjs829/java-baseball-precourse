package baseball.model;

import baseball.utils.ValidUtil;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 숫자 야구 객체
 */
public class BaseBallGame {

    private List<Integer> answers;
    private final Strike strike;
    private final Ball ball;
    private boolean isGameOver;

    private static final String ENTER_NUMBER = "숫자를 입력해주세요 : ";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private static final String NOT_THING = "낫싱";
    private static final int GAME_ANSWER_SIZE = 3;

    public BaseBallGame() {
        this.answers = getThreeNumberExtraction();
        this.strike = new Strike();
        this.ball = new Ball();
    }

    public BaseBallGame(List<Integer> answers) {
        this.answers = answers;
        this.strike = new Strike();
        this.ball = new Ball();
    }

    /**
     * 세자리 숫자 추출
     */
    public List<Integer> getThreeNumberExtraction() {
        final List<Integer> digits = new ArrayList<>();

        while (digits.size() < GAME_ANSWER_SIZE) {
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
        final List<Integer> digits = getNonDuplicateDigitsThrowIfInvalid(submitNumber);
        playGame(digits);
    }

    /* 게임 시작 */
    public void playGame(List<Integer> digits) {
        for (Integer digit : digits) {
            int index = digits.indexOf(digit);
            playGameByIndex(digit, index);
        }

        final String ballState = ball.getBallState();
        final String strikeState = strike.getBallState();

        System.out.println(combineGameState(ballState, strikeState));
        ball.init();
        strike.init();
    }

    /* 게임 상태 조합 */
    private String combineGameState(String ballState, String strikeState) {
        if (Objects.nonNull(ballState)) {
            return ballState + SPACE + strikeState;
        }

        if (Objects.nonNull(strikeState)) {
            return strikeState;
        }

        return NOT_THING;
    }

    /* 인덱스 단위로 게임 */
    private void playGameByIndex(Integer digit, int index) {
        Integer answer = this.answers.get(index);

        if (answer.equals(digit)) {
            strike.increaseCount();
            return;
        }

        if (answers.contains(digit)) {
            ball.increaseCount();
        }
    }

    /* 중복 되지 않은 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private List<Integer> getNonDuplicateDigitsThrowIfInvalid(String submitNumber) {
        ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitNumber));

        String[] arraySubmitNumber = submitNumber.split(EMPTY);
        final Set<Integer> digits = getValidDigitsThrowIfInvalid(arraySubmitNumber);

        ValidUtil.throwIllegalArgumentExceptionIfTrue(digits.size() != GAME_ANSWER_SIZE);
        return Lists.newArrayList(digits);
    }

    /* 유효한 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private Set<Integer> getValidDigitsThrowIfInvalid(String[] arraySubmitNumber) {
        final Set<Integer> digits = new HashSet<>();

        for (String submitNum : arraySubmitNumber) {
            ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitNum));
            final int digit = Integer.parseInt(submitNum);
            ValidUtil.throwIllegalArgumentExceptionIfTrue(digit < 1 || digit > 9);
            digits.add(digit);
        }

        return digits;
    }

}
