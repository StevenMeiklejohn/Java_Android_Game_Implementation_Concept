
package example.codeclan.com.spacebastardsconceptbuild;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static example.codeclan.com.spacebastardsconceptbuild.R.drawable.player;


public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
    private Player player;
    private Paint paint;
    private ArrayList<Sprite> sprites;
    private ArrayList<Star> stars = new ArrayList<Star>();

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        sprites = new ArrayList<Sprite>();
        paint = new Paint();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
         });

//            createStars();
//        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1_sprite_sheet);
//        sprite = new Sprite(this, bmp);
    }

    private void createStars(){
        int numStars = 100;
        for (int i = 0; i < numStars; i++) {
            Star s = new Star(2160, 1120);
            stars.add(s);
        }
    }

    private void createBackground(Canvas canvas){
        canvas.drawRGB(0, 0, 0);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(), canvas.getHeight(), true);

        canvas.drawBitmap(scaledBitmap, 0, 0, null);
    }

    private void createSprites(){
        sprites.add(createSprite(R.drawable.enemy1_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy1_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy1_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy1_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy_triangle_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy_triangle_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy_triangle_sprite_sheet));
        sprites.add(createSprite(R.drawable.enemy_triangle_sprite_sheet));
//        sprites.add(createPlayer(R.drawable.player_sprite_sheet_120_60));
        createPlayer(R.drawable.player_sprite_sheet_120_60);

    }

    private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
    }

    private void createPlayer(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        this.player =  new Player(this, bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        createBackground(canvas);
//        canvas.drawColor(Color.BLACK);

//        paint.setColor(Color.WHITE);
////            draw all stars
//        for(Star s: stars){
//            paint.setStrokeWidth(s.getStarWidth());
//            canvas.drawPoint(s.getX(), s.getY(), paint);
//            s.update(5);
//        }
        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
        this.player.onDraw(canvas);
    }
}

