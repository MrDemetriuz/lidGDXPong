package io.github.gameTest2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    ShapeRenderer shape;
    SpriteBatch batch;
    Texture pongOverlay;
    Ball ball;
    Paddle paddlePlayer;
    Paddle paddleAI;
    BitmapFont text;
    BitmapFont text2;
    ScoreSystem score;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        pongOverlay = new Texture(Gdx.files.internal("PongOverlay.png"));
        ball = new Ball(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() / 2,7,4,1);

        paddlePlayer = new Paddle(10,Gdx.graphics.getHeight()/2,10,70);
        paddleAI = new Paddle(Gdx.graphics.getWidth() - 20,Gdx.graphics.getHeight()/2,10,70);

        score = new ScoreSystem();

        text = new BitmapFont();
        text.setColor(1f,1f,1f,1f);
        text.getData().setScale(4f);
        text2 = new BitmapFont();
        text2.setColor(1f,1f,1f,1f);
        text2.getData().setScale(4f);

    }

    @Override
    public void render() {
        String ballY = ("Ball y = "+ ball.y);
        String paddleHalfY = ("Paddle Y = "+ (paddlePlayer.y + paddlePlayer.height/2));
        String ballYSpeed = ("Ball Y speed = "+ ball.ySpeed);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        ball.update();
        ball.collisionCheck(paddlePlayer);
        ball.collisionCheck(paddleAI);

        paddlePlayer.draw(shape);
        //paddlePlayer.playerMovementMouse();
        paddlePlayer.playerMovementKeyboard();

        paddleAI.draw(shape);
        //paddleAI.player2MovementKeyboard();
        paddleAI.AIMovement(ball);
        shape.end();

        batch.begin();
        //text.draw(batch,ballY,30,(Gdx.graphics.getHeight()-30));
        //text.draw(batch,paddleHalfY,30,(Gdx.graphics.getHeight() - 60));
        //text.draw(batch,ballYSpeed,30,(Gdx.graphics.getHeight() - 90));
        text.draw(batch,score.drawPointsPlayer1(ball),Gdx.graphics.getWidth()/2 -65,Gdx.graphics.getHeight() - 50);
        text2.draw(batch,score.drawPointsPlayer2(ball),Gdx.graphics.getWidth()/2 +30,Gdx.graphics.getHeight() - 50);
        batch.draw(pongOverlay,0,0);
        batch.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
    }
}
