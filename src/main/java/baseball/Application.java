package baseball;

import baseball.model.BaseBallGame;

public class Application {

    public static void main(String[] args) {
        final BaseBallGame baseBallGame = new BaseBallGame();
        baseBallGame.gameStart();
    }
    
}
