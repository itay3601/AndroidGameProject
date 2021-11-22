package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Finger {
    private Bitmap finger;
    private int x,y,width,height;
    public Finger(Resources res) {
        x=700;
        y=1200;
        width=200;
        height=200;
        finger= BitmapFactory.decodeResource(res,R.drawable.finger);
        finger=Bitmap.createScaledBitmap(finger,width,height,false);


    }

    public Bitmap getFinger() {
        return finger;
    }

    public void setFinger(Bitmap finger) {
        this.finger = finger;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
