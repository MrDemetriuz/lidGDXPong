package io.github.gameTest2;

import com.badlogic.gdx.Gdx;
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

    public void playerMovement(){
        y = (Gdx.graphics.getHeight() - Gdx.input.getY()) - height / 2;
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
