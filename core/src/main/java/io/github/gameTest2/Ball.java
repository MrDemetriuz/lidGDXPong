package io.github.gameTest2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    int speedControl;
    Color color;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = Color.WHITE;
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x,y,size);
        shape.setColor(color);
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;

        if (x - size <= 0 || x + size >= Gdx.graphics.getWidth()){
            xSpeed = -xSpeed;
        }

        if ( y + size >= Gdx.graphics.getHeight()){
            ySpeed = -ySpeed;
        }
        if (y - size <= 0 ){
            ySpeed = -ySpeed;
        }
    }

    public boolean collidesWith(Paddle paddle){
        return (x + size >= paddle.x  &&
            x - size <= paddle.x+ paddle.width &&
            y + size >= paddle.y &&
            y - size <= paddle.y + paddle.height );
    }

    public boolean isMovingUp(){
        return (ySpeed > 0);
    }

    public boolean isUpOf(Paddle paddle){
        return (y > (paddle.y + paddle.height/2));
    }

    public int differenceOfY(Paddle paddle){
        System.out.println("Y Paddle - Y Ball = "+ ((Math.abs((paddle.y + paddle.height/2) - y)/10)+1));
        return ((Math.abs((paddle.y + paddle.height/2) - y)/7) + 1);
    }

    public void collisionCheck(Paddle paddle){
        if (collidesWith(paddle)){
            ySpeed = differenceOfY(paddle);

            if (isUpOf(paddle)){
                color = Color.BLUE;
                if (!isMovingUp()){
                    ySpeed = -ySpeed;
                }
                xSpeed = -xSpeed;
                /*if (speedControl < 4) {
                    xSpeed = xSpeed > 0 ? xSpeed += 1 : xSpeed - +1;
                    ySpeed = ySpeed > 0 ? ySpeed += 1 : ySpeed - +1;
                }
                speedControl ++;*/
            }

            if (!isUpOf(paddle)){
                color = Color.RED;
                if (isMovingUp()){
                    ySpeed = -ySpeed;
                }
                xSpeed = -xSpeed;
                /*if (speedControl < 4) {
                    xSpeed = xSpeed > 0 ? xSpeed += 1 : xSpeed - +1;
                    ySpeed = ySpeed > 0 ? ySpeed += 1 : ySpeed - +1;
                }
                speedControl ++;*/
            }
        }
    }

}
