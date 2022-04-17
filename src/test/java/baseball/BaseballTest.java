package baseball;

import baseball.model.BaseBallGame;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseballTest extends NsTest {

    @Test
    @DisplayName("3자리 수 추출 시 정상적인 값이 추출되었는지 확인")
    void 정해진_수로_이루어진_3자리의_수_추출() {
        BaseBallGame baseBallGame = new BaseBallGame();
        final List<Integer> digits = baseBallGame.getThreeNumberExtraction();
        assertNotNull(digits); // 리스트가 생성되어 있는지 확인한다.
        assertEquals(3, digits.size()); // 3자리 수 인지 판단한다.

        final Set<Integer> distinctDigits = new HashSet<>();

        for (Integer digit : digits) {
            assertTrue(0 < digit && digit < 10);
            distinctDigits.add(digit);
        }

        assertEquals(3, distinctDigits.size()); // 중복된 수가 있는지 판단한다.
    }

    @Test
    @DisplayName("숫자 야구 룰에 의거하여 상황에 따른 동작을 진행하는지 확인")
    void 숫자_야구_시작() {
        BaseBallGame baseBallGame = getBaseBallGame();

        baseBallGame.playGame(Arrays.asList(1, 4, 5));
        assertThat(output()).contains("1스트라이크");
        init();

        baseBallGame.playGame(Arrays.asList(1, 3, 4));
        assertThat(output()).contains("1볼 1스트라이크");
        init();

        baseBallGame.playGame(Arrays.asList(1, 3, 2));
        assertThat(output()).contains("2볼 1스트라이크");
        init();

        baseBallGame.playGame(Arrays.asList(1, 2, 4));
        assertThat(output()).contains("2스트라이크");
        init();

        baseBallGame.playGame(Arrays.asList(2, 4, 5));
        assertThat(output()).contains("1볼");
        init();

        baseBallGame.playGame(Arrays.asList(2, 1, 4));
        assertThat(output()).contains("2볼");
        init();

        baseBallGame.playGame(Arrays.asList(3, 1, 2));
        assertThat(output()).contains("3볼");
        init();

        baseBallGame.playGame(Arrays.asList(1, 2, 3));
        assertThat(output()).contains("3스트라이크");
        init();

        baseBallGame.playGame(Arrays.asList(5, 6, 7));
        assertThat(output()).contains("낫싱");
    }

    @Test
    @DisplayName("숫자 야구 룰에 의거하여 상황에 따른 동작을 진행하는지 확인")
    void 반복_기능() {
        BaseBallGame baseBallGame = getBaseBallGame();

        baseBallGame.playGame(Arrays.asList(1, 4, 5));
        Assertions.assertFalse(baseBallGame.isGameOver());
        init();

        baseBallGame.playGame(Arrays.asList(1, 3, 4));
        Assertions.assertFalse(baseBallGame.isGameOver());
        init();

        baseBallGame.playGame(Arrays.asList(1, 3, 2));
        Assertions.assertFalse(baseBallGame.isGameOver());
        init();

        baseBallGame.playGame(Arrays.asList(1, 2, 3));
        Assertions.assertTrue(baseBallGame.isGameOver());
    }

    @Test
    @DisplayName("게임을 다시 시작하거나 종료할 수 있는 기능이 정상적으로 되는지 확인")
    void 게임_다시_시작_혹은_종료_기능() {
        BaseBallGame baseBallGame = getBaseBallGame();

        baseBallGame.playGame(Arrays.asList(1, 2, 3));
        init();
        baseBallGame.askGameContinue(1);
        assertThat(output()).contains("숫자를 입력해주세요");
        init();

        baseBallGame.playGame(Arrays.asList(1, 2, 3));
        baseBallGame.askGameContinue(2);
        assertThat(output()).contains("게임 종료");
        init();
    }

    /* 숫자 야구 조회 */
    private BaseBallGame getBaseBallGame() {
        init();
        return new BaseBallGame(Arrays.asList(1, 2, 3));
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

}
