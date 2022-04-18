package baseball.model;

import baseball.enums.SubmitButtonType;
import baseball.utils.StringUtil;
import baseball.utils.ValidUtil;
import baseball.view.GameView;

import java.util.List;
import java.util.Objects;

/**
 * 숫자 야구 객체
 */
public class BaseBallGame {

    private List<Integer> answers;
    private final Ball ball = new Ball();
    private final Strike strike = new Strike();
    private boolean isGameOver;

    public BaseBallGame() {
        this.answers = AnswerDigits.getThreeNumberExtraction();
    }

    public BaseBallGame(List<Integer> answers) {
        this.answers = answers;
    }

    /* 숫자 야구 게임 초기화 */
    private void initBaseBallGame() {
        this.answers = AnswerDigits.getThreeNumberExtraction();
        this.ball.init();
        this.strike.init();
        this.isGameOver = false;
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
        GameView.printEnterDigitsMessage();
        final String submitNumber = GameView.enterMessage();
        return AnswerDigits.getNonDuplicateDigitsThrowIfInvalid(submitNumber);
    }

    /* 게임 종료 후 진행 작업 */
    private void processAfterPlayGame() {
        if (!isGameOver) {
            gameStart();
            return;
        }

        GameView.printClearAndEndMessage();
        GameView.printChoiceMessage();
        final String submitButton = GameView.enterMessage();
        askGameContinueIfRestartIsGameStart(submitButton);
    }

    /* 게임 재시작 여부 확인, 재시작 시 게임 다시 실행 */
    private void askGameContinueIfRestartIsGameStart(String submitButton) {
        final SubmitButtonType submitButtonType = askGameContinue(submitButton);

        if (SubmitButtonType.isRestartType(submitButtonType)) {
            initBaseBallGame();
            gameStart();
        }
    }

    /**
     * 계속 게임 진행할건지 확인
     *
     * @param submitButton (입력받은 버튼)
     */
    public SubmitButtonType askGameContinue(String submitButton) {
        final SubmitButtonType submitButtonType = SubmitButtonType.getEnumByValue(submitButton);
        ValidUtil.throwIllegalArgumentExceptionIfTrue(Objects.isNull(submitButtonType));
        return submitButtonType;
    }

    /* 게임 시작 */
    public void playGame(List<Integer> digits) {
        for (Integer digit : digits) {
            int index = digits.indexOf(digit);
            playGameByIndex(digit, index);
        }

        final String ballState = ball.getBallState();
        final String strikeState = strike.getBallState();
        GameView.printMessage(StringUtil.combineBallState(ballState, strikeState));
        this.isGameOver = strike.getCount() == AnswerDigits.GAME_ANSWER_SIZE;
        ballTypeInit();
    }

    /* 인덱스 단위로 게임 */
    private void playGameByIndex(Integer digit, int index) {
        final Integer answer = this.answers.get(index);

        if (answer.equals(digit)) {
            strike.increaseCount();
            return;
        }

        if (answers.contains(digit)) {
            ball.increaseCount();
        }
    }

    /* 볼 타입 초기화 */
    private void ballTypeInit() {
        ball.init();
        strike.init();
    }

}