package io.github.gameTest2;

import com.badlogic.gdx.Gdx;

public class ScoreSystem {

    boolean player1Score;
    boolean player2Score;
    int player1Counter;
    int player2Counter;

    public ScoreSystem() {
        this.player1Score = false;
        this.player2Score = false;
        this.player1Counter = 0;
        this.player2Counter = 0;
    }

    public String drawPointsPlayer1(Ball ball){
        score(ball);

        if (player1Score){
            System.out.println("P1");
            player1Counter ++;
            player1Score = false;
            ball.resetBall(this);
        }
        return String.format("%d",player1Counter);
    }


    public String drawPointsPlayer2(Ball ball){
        score(ball);

        if (player2Score){
            System.out.println("P2");
            player2Counter++;
            player2Score = false;
            ball.resetBall(this);
        }
        return String.format("%d",player2Counter);
    }

    public void score(Ball ball){
        if (ball.x + ball.size >= Gdx.graphics.getWidth()){
            player1Score = true;
        }
        if (ball.x - ball.size <= 0){
            player2Score = true;
        }

    }
}
