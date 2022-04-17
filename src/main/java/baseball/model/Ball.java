package baseball.model;

import baseball.enums.BallTypeCode;

/**
 * 볼 객체, 볼 타입 상속
 */
public class Ball extends BallType {

    protected Ball() {
        super(BallTypeCode.BALL);
    }

}
