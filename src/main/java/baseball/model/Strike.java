package baseball.model;

import baseball.enums.BallTypeCode;
import baseball.model.BallType;

/**
 * 스트라이크 객체, 볼 타입 상속
 */
public class Strike extends BallType {

    protected Strike() {
        super(BallTypeCode.STRIKE);
    }

    /**
     * 게임 종료 여부 반환
     *
     * @param gameAnswerSize (정답 사이즈, 유동적으로 변경)
     * @return isGameOver 게임 종료 여부
     */
    public boolean isGameOver(int gameAnswerSize) {
        return this.getCount() == gameAnswerSize;
    }

}
