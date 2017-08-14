package example.codeclan.com.spacebastardsconceptbuild;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;


public class Player {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Rect sourceRect;

    public Player(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / 4;
        this.height = bmp.getHeight();
        sourceRect = new Rect(0, 0, width, height);
        setStartingPositionAndSpeed();
    }

    private void setStartingPositionAndSpeed(){
        x = 100;
        y = 500;
        xSpeed = 0;
        ySpeed = 0;
    }

    public void movePlayer(String direction){

    }

    public void stopMovePlayer(String direction){

    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed) {
            x = gameView.getWidth() - width;
        }

        if (x + xSpeed < 0){
            x = x + width;
        }
        if (y > gameView.getHeight() - height - ySpeed) {
            y = gameView.getHeight() - height;
        }

        if(y + ySpeed < 0){
            y = 0 + height;
        }
        x = x + xSpeed;
        y = y + ySpeed;
        if(currentFrame ==3) {
            currentFrame = 0;}
        else
        {
            currentFrame = ++currentFrame;
        }
    }


    public void onDraw(Canvas canvas) {
        update();
        this.sourceRect.left = currentFrame * width;
        this.sourceRect.right = this.sourceRect.left + width;
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, sourceRect, dst, null);
    }
}
