package baseball.model;

import baseball.enums.BallTypeCode;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 볼 타입
 */
public abstract class BallType {

    private AtomicInteger count;
    private final BallTypeCode ballTypeCode;

    private static final int ZERO = 0;

    protected BallType(BallTypeCode ballTypeCode) {
        this.count = new AtomicInteger();
        this.ballTypeCode = ballTypeCode;
    }

    /* 초기화 */
    protected void init() {
        this.count = new AtomicInteger();
    }

    /* 카운트 증가 */
    protected void increaseCount() {
        if (Objects.isNull(count)) {
            this.count = new AtomicInteger();
        }

        this.count.incrementAndGet();
    }

    /* 횟수 조회 */
    protected int getCount() {
        return count.get();
    }

    /* 볼 상태 조회 */
    protected String getBallState() {
        if (Objects.isNull(count)) {
            return null;
        }

        int countNum = count.get();
        if (countNum == ZERO) {
            return null;
        }

        return countNum + ballTypeCode.getBallTypeCodeWord();
    }

}
