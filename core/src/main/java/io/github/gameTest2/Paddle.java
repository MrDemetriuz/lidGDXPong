package io.github.gameTest2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int width;
    int height;
    int ySpeed;



    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ySpeed = 4;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x,y,width,height);
    }

    public void playerMovementMouse(){
        y = (Gdx.graphics.getHeight() - Gdx.input.getY()) - height / 2;
    }

    public void playerMovementKeyboard(){
        boolean wIsPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sIsPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        if (y + height >= Gdx.graphics.getHeight() ){
            y = Gdx.graphics.getHeight() - height;
        }
        if (y <= 0){
            y = 0;
        }
        if (wIsPressed){
            y += 3;
        }
        if (sIsPressed){
            y -= 3;
        }
    }
    public void player2MovementKeyboard(){
        boolean wIsPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean sIsPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (y + height >= Gdx.graphics.getHeight() ){
            y = Gdx.graphics.getHeight() - height;
        }
        if (y <= 0){
            y = 0;
        }
        if (wIsPressed){
            y += 3;
        }
        if (sIsPressed){
            y -= 3;
        }
    }

    public void AIMovement(Ball ball){
        y += ySpeed;

        if (y + height/2  < ball.y){
            ySpeed = ySpeed > 0? ySpeed: -ySpeed;
        }

        if (y + height/2 > ball.y){
            ySpeed = ySpeed > 0? -ySpeed: ySpeed;
        }

    }
}
