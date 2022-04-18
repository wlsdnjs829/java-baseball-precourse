package baseball.view;

import camp.nextstep.edu.missionutils.Console;

import static java.lang.System.out;

/**
 * 게임 뷰 객체
 */
public final class GameView {

    private static final String CLEAR_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! ";
    private static final String CHOICE_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String ENTER_DIGITS = "숫자를 입력해주세요 : ";
    private static final String GAME_END_MESSAGE = "게임 종료";

    private GameView() {
        throw new ClassCastException();
    }

    /**
     * 메시지 입력
     */
    public static String enterMessage() {
        return Console.readLine();
    }

    /**
     * 숫자 입력 메시지 출력
     */
    public static void printEnterDigitsMessage() {
        out.print(ENTER_DIGITS);
    }

    /**
     * 선택 메시지 출력
     */
    public static void printChoiceMessage() {
        out.println(CHOICE_MESSAGE);
    }

    /**
     * 클리어 및 종료 메시지 출력
     */
    public static void printClearAndEndMessage() {
        out.println(CLEAR_MESSAGE + GAME_END_MESSAGE);
    }

    /**
     * 메시지 출력
     */
    public static void printMessage(String message) {
        out.println(message);
    }

}
