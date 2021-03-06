package com.example.came.cameselleabreujavier_proyecto.Scenes.GameElements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.came.cameselleabreujavier_proyecto.R;
import com.example.came.cameselleabreujavier_proyecto.SceneUtils.Utils;

public class Bullet {

    private Bitmap bitmapBullets;//Bulllets icon
    private int posX;//Position on horizontal axis
    private int posY;//Position on vertical axis
    private int screenWidth;//Screen width
    private int screenHeight;//Screen height
    private int speed;//Pixels added or substracted on movement
    private boolean catched = false;//Enabled if character intersect with life icon
    private boolean collisionable = true;//If character can to intersect with life icon
    private long actualTime = System.currentTimeMillis();
    private Rect rectBullet;//Rectangle that encloses life image
    private Paint p;//Life rectangle modifier
    private Utils u;//Utils class

    /**
     * Initialize live properties
     *
     * @param context      Context
     * @param screenWidth  Screen width
     * @param screenHeight Screen height
     */
    public Bullet(Context context, int screenWidth, int screenHeight) {
        u = new Utils(context);
        this.screenWidth = screenWidth;//1920
        this.screenHeight = screenHeight;//1080
        this.bitmapBullets = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullets);
        this.bitmapBullets = Bitmap.createScaledBitmap(bitmapBullets, screenWidth / 15, screenHeight / 15, false);
        this.posX = (int) (Math.random() * screenWidth * 20 + screenWidth * 2);
        this.posY = (int) (Math.random() * screenHeight * 8 / 10);
//        this.posY = (int) Math.cos(posX);
        while (this.posY < screenHeight * 6 / 10) {
            this.posY = (int) (Math.random() * screenHeight * 8 / 10);
        }
        this.catched = false;
        this.speed = (int) (Math.random() * 16 + 20);
        p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
    }

    /**
     * Life properties control
     */
    public void mover() {
//        Log.i("pos", posY + "");
        rectBullet = new Rect(posX, posY, posX + bitmapBullets.getWidth(), posY + bitmapBullets.getHeight());
        this.posX -= speed;
        if (posX + bitmapBullets.getWidth() < 0) {
            this.speed = (int) (Math.random() * 14 + 18);
            this.posX = (int) (Math.random() * screenWidth * 20 + screenWidth * 5);
            this.posY = (int) (Math.random() * screenHeight * 8 / 10);
//            this.posY = (int) Math.cos(posX);
            while (this.posY < screenHeight * 6 / 10) {
                this.posY = (int) (Math.random() * screenHeight * 8 / 10);
            }
            catched = false;
        }
        if (posX > 2000) setCollisionable(true);
    }

    /**
     * Paint life on screen
     *
     * @param c Canvas
     */
    public void dibujar(Canvas c) {
        if (!isCatched())
            c.drawBitmap(bitmapBullets, posX, posY, null);
//            c.drawRect(rectBullet, p);
    }

    /**
     * Returns life image
     *
     * @return Life image
     */
    public Bitmap getBitmapBullets() {
        return bitmapBullets;
    }

    /**
     * Set live image
     *
     * @param bitmapBullets Life image
     */
    public void setBitmapBullets(Bitmap bitmapBullets) {
        this.bitmapBullets = bitmapBullets;
    }

    /**
     * Returns position on horizontal axis
     *
     * @return Position X
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Set position on horizontal axis
     *
     * @param posX Position X
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns position on vertical axis
     *
     * @return Position Y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Set position on vertical axis
     *
     * @param posY Position Y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Returns catched boolean value
     *
     * @return Catched value
     */
    public boolean isCatched() {
        return catched;
    }

    /**
     * Set catched boolean value
     *
     * @param catched Boolean value
     */
    public void setCatched(boolean catched) {
        this.catched = catched;
    }

    /**
     * Returns life rectangle
     *
     * @return Life rectangle
     */
    public Rect getRectBullet() {
        return rectBullet;
    }

    /**
     * Set life rectangle
     *
     * @param rectBullet Life rectangle
     */
    public void setRectBullet(Rect rectBullet) {
        this.rectBullet = rectBullet;
    }

    /**
     * Returns collisionable boolean value
     *
     * @return Collisionable value
     */
    public boolean isCollisionable() {
        return collisionable;
    }

    /**
     * Set collisionable boolean value
     *
     * @param collisionable Boolean value
     */
    public void setCollisionable(boolean collisionable) {
        this.collisionable = collisionable;
    }
}
