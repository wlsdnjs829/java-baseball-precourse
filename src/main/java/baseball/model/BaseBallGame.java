package baseball.model;

import baseball.enums.SubmitButtonType;
import baseball.utils.ValidUtil;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseball.enums.SubmitButtonType.RESTART_BUTTON;

/**
 * 숫자 야구 객체
 */
public class BaseBallGame {

    private List<Integer> answers;
    private final Strike strike;
    private final Ball ball;
    private boolean isGameOver;

    private static final String ENTER_DIGITS = "숫자를 입력해주세요 : ";
    private static final String CLEAR_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! ";
    private static final String GAME_END_MESSAGE = "게임 종료";
    private static final String CHOICE_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
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

    /* 숫자 야구 게임 초기화 */
    private void initBaseBallGame() {
        this.answers = getThreeNumberExtraction();
        this.strike.init();
        this.ball.init();
        this.isGameOver = false;
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
        playGame(enterDigits());
        processAfterPlayGame();
    }

    /* 숫자 입력 */
    private List<Integer> enterDigits() {
        System.out.print(ENTER_DIGITS);
        final String submitNumber = Console.readLine();
        return getNonDuplicateDigitsThrowIfInvalid(submitNumber);
    }

    /* 게임 종료 후 진행 작업 */
    private void processAfterPlayGame() {
        if (!isGameOver()) {
            gameStart();
            return;
        }

        System.out.println(CLEAR_MESSAGE + GAME_END_MESSAGE);
        System.out.println(CHOICE_MESSAGE);
        final String submitButton = Console.readLine();
        SubmitButtonType submitButtonType = askGameContinue(submitButton);
        submitButtonType.apply(this);
    }

    /**
     * 계속 게임 진행할건지 확인
     *
     * @param submitButton (입력받은 버튼)
     */
    public SubmitButtonType askGameContinue(String submitButton) {
        SubmitButtonType submitButtonType = SubmitButtonType.getEnumByValue(submitButton);
        ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitButtonType));
        initBaseBallGame();
        return submitButtonType;
    }

    /* 게임 시작 */
    public void playGame(List<Integer> digits) {
        for (Integer digit : digits) {
            int index = digits.indexOf(digit);
            playGameByIndex(digit, index);
        }

        String ballState = ball.getBallState();
        String strikeState = strike.getBallState();
        System.out.println(combineGameState(ballState, strikeState));
        this.isGameOver = strike.isGameOver(GAME_ANSWER_SIZE);
        ballTypeInit();
    }

    /* 볼 타입 초기화 */
    private void ballTypeInit() {
        ball.init();
        strike.init();
    }

    /* 게임 상태 조합 */
    private String combineGameState(String ballState, String strikeState) {
        if (Objects.isNull(ballState) && Objects.isNull(strikeState)) {
            return NOT_THING;
        }

        if (Objects.isNull(ballState)) {
            return strikeState;
        }

        return ballState + SPACE + getStringIfNullIsEmpty(strikeState);
    }

    /* 문자열 조회, 없는 경우 빈 문자열 반환 */
    private String getStringIfNullIsEmpty(String string) {
        if (Objects.isNull(string)) {
            return EMPTY;
        }

        return string;
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
        ValidUtil.throwIllegalArgumentExceptionIfTrue(arraySubmitNumber.length != GAME_ANSWER_SIZE);
        return getValidDigitsThrowIfInvalid(arraySubmitNumber);
    }

    /* 유효한 숫자 리스트 조회, 유효하지 않을 시 예외 처리 */
    private List<Integer> getValidDigitsThrowIfInvalid(String[] arraySubmitNumber) {
        final List<Integer> digits = new ArrayList<>();

        for (String submitNum : arraySubmitNumber) {
            ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitNum));
            final int digit = Integer.parseInt(submitNum);
            ValidUtil.throwIllegalArgumentExceptionIfTrue(digit < 1 || digit > 9 || digits.contains(digit));
            digits.add(digit);
        }

        return digits;
    }

    /**
     * 게임 종료 여부 조회
     * @return boolean true : 게임 종료 / false : 게임 유지
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * 게임 종료 메시지 프린트
     */
    public void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE);
    }

}
