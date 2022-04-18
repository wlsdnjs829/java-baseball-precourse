package baseball.model;

import baseball.enums.BallTypeCode;

/**
 * 스트라이크 객체, 볼 타입 상속
 */
public class Strike extends BallType {

    protected Strike() {
        super(BallTypeCode.STRIKE);
    }

    /**
     * 횟수 조회
     *
     * @return 횟수
     */
    @Override
    public int getCount() {
        return super.getCount();
    }

}
