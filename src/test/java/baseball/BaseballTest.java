package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseballTest extends NsTest {

    @Test
    @DisplayName("3자리 수 추출 시 정상적인 값이 추출되었는지 확인")
    void 정해진_수로_이루어진_3자리의_수_추출() {
        int value = getThreeNumberExtarction();
        String[] digits = {String.valueOf(value)};
        assertEquals(3, digits.length); // 3자리 수 인지 판단한다

        final Set<Integer> distinctDigits = new HashSet<>();

        for (String digit : digits) {
            int unit = Integer.parseInt(digit);
            assertTrue(0 < unit && unit < 10);
            distinctDigits.add(unit);
        }

        assertEquals(3, distinctDigits.size()); // 중복된 수가 있는지 판단한다.
    }

    @Test
    @DisplayName("숫자 야구 룰에 의거하여 상황에 따른 동작을 진행하는지 확인")
    void 숫자_야구_시작() {
        init();
        BaseBallGame baseBallGame = new BaseBallGame(123);

        baseBallGame.play(145);
        assertThat(output()).contains("1스트라이크");
        init();

        baseBallGame.play(134);
        assertThat(output()).contains("1볼 1스트라이크");
        init();

        baseBallGame.play(132);
        assertThat(output()).contains("2볼 1스트라이크");
        init();

        baseBallGame.play(124);
        assertThat(output()).contains("2스트라이크");
        init();

        baseBallGame.play(245);
        assertThat(output()).contains("1볼");
        init();

        baseBallGame.play(214);
        assertThat(output()).contains("2볼");
        init();

        baseBallGame.play(312);
        assertThat(output()).contains("3볼");
        init();

        baseBallGame.play(123);
        assertThat(output()).contains("3스트라이크");
        init();

        baseBallGame.play(567);
        assertThat(output()).contains("낫싱");
    }

    @Test
    @DisplayName("숫자 야구 룰에 의거하여 상황에 따른 동작을 진행하는지 확인")
    void 반복_기능() {
        init();
        BaseBallGame baseBallGame = new BaseBallGame(123);

        baseBallGame.play(145);
        assertThat(output()).contains("숫자를 입력해주세요");
        init();

        baseBallGame.play(134);
        assertThat(output()).contains("숫자를 입력해주세요");
        init();

        baseBallGame.play(132);
        assertThat(output()).contains("숫자를 입력해주세요");
        init();

        baseBallGame.play(123);
        assertThat(output()).doesNotContain("숫자를 입력해주세요");
    }

    @Test
    @DisplayName("게임을 다시 시작하거나 종료할 수 있는 기능이 정상적으로 되는지 확인")
    void 게임_다시_시작_혹은_종료_기능() {
        init();
        BaseBallGame baseBallGame = new BaseBallGame(123);

        baseBallGame.play(123);
        init();
        baseBallGame.askGameContinue(1);
        assertThat(output()).contains("숫자를 입력해주세요");
        init();

        baseBallGame.play(123);
        baseBallGame.askGameContinue(2);
        assertThat(output()).contains("게임 종료");
        init();
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

}
