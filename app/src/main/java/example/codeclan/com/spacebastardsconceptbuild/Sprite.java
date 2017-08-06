package example.codeclan.com.spacebastardsconceptbuild;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import static android.R.attr.src;


public class Sprite {
//    private static final int BMP_ROWS = 6;
//    private static final int BMP_COLUMNS = 4;
    private int x = 200;
    private int y = 200;
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Rect sourceRect;

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / 4;
        this.height = bmp.getHeight();
        sourceRect = new Rect(0, 0, width, height);
    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed) {
            xSpeed = -5;
        }
        if (x + xSpeed < 0) {
            xSpeed = 5;
        }
        x = x + xSpeed;
        if(currentFrame ==3) {
            currentFrame = 0;}
            else
            {
                currentFrame = ++currentFrame;
            }
        }


    public void onDraw(Canvas canvas) {
        update();
//        int srcX = currentFrame * width;
//        int srcY = height;
        this.sourceRect.left = currentFrame * width;

        this.sourceRect.right = this.sourceRect.left + width;

//        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, sourceRect, dst, null);
    }
}
