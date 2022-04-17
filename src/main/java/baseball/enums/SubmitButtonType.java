package baseball.enums;

import baseball.model.BaseBallGame;

import java.util.ArrayList;
import java.util.List;

/**
 * 제출 버튼 타입
 */
public enum SubmitButtonType {

    RESTART_BUTTON("1") {
        @Override
        public void apply(BaseBallGame baseBallGame) {
            baseBallGame.gameStart();
        }
    },

    END_BUTTON("2") {
        @Override
        public void apply(BaseBallGame baseBallGame) {
            baseBallGame.printGameEndMessage();
        }
    };

    private final String value;

    private String getValue() {
        return value;
    }

    public abstract void apply(BaseBallGame baseBallGame);

    SubmitButtonType(String value) {
        this.value = value;
    }

    /**
     * value 값으로 enum 조회
     *
     * @param value (enum value)
     * @return SubmitButtonType enum type 반환
     */
    public static SubmitButtonType getEnumByValue(String value) {
        final List<SubmitButtonType> submitButtonTypes = new ArrayList<>();
        for (SubmitButtonType submitButtonType : SubmitButtonType.values()) {
            addSubmitButtonType(value, submitButtonTypes, submitButtonType);
        }

        if (submitButtonTypes.isEmpty()) {
            return null;
        }

        return submitButtonTypes.get(0);
    }

    /* 제출 버튼 타입 추가 */
    private static void addSubmitButtonType(String value, List<SubmitButtonType> submitButtonTypes,
                                            SubmitButtonType submitButtonType) {
        String typeValue = submitButtonType.getValue();

        if (typeValue.equals(value)) {
            submitButtonTypes.add(submitButtonType);
        }
    }

}
