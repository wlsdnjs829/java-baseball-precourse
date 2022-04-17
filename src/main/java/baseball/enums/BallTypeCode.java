package baseball.enums;

/**
 * 볼 타입 코드
 */
public enum BallTypeCode {

    BALL("볼"),
    STRIKE("스트라이크");

    private final String word;

    BallTypeCode(String word) {
        this.word = word;
    }

    public String getBallTypeCodeWord() {
        return word;
    }

}
