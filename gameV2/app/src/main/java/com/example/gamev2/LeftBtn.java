package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class LeftBtn {
    private int x,y,width,height;
    private boolean ispress=false;
    private Bitmap leftBitmap;
    public  LeftBtn( Resources res){
        x=0;y=1600;
        leftBitmap= BitmapFactory.decodeResource(res,R.drawable.signleft);
        width=leftBitmap.getWidth();
        height=leftBitmap.getHeight();
        System.out.println(height);
        //width/=2;
        //height/=2;
        leftBitmap=Bitmap.createScaledBitmap(leftBitmap,width,height,false);




    }

    public boolean isIspress() {
        return ispress;
    }

    public void setIspress(boolean ispress) {
        this.ispress = ispress;
    }

    public Bitmap getLeftBitmap() {
        return leftBitmap;
    }

    public void setLeftBitmap(Bitmap leftBitmap) {
        this.leftBitmap = leftBitmap;
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
}
