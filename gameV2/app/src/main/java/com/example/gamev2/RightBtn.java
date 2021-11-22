package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RightBtn {
    private int x,y,width,height;
    private boolean ispress=false;
    private Bitmap rightBitmap;
    public  RightBtn( Resources res){
        x=792;y=1600;
        rightBitmap= BitmapFactory.decodeResource(res,R.drawable.signright);
        width=rightBitmap.getWidth();
        height=rightBitmap.getHeight();
        System.out.println(width);
        //width/=2;
        //height/=2;
        rightBitmap=Bitmap.createScaledBitmap(rightBitmap,width,height,false);




    }

    public boolean isIspress() {
        return ispress;
    }

    public void setIspress(boolean ispress) {
        this.ispress = ispress;
    }

    public Bitmap getRightBitmap() {
        return rightBitmap;
    }

    public void setRightBitmap(Bitmap rightBitmap) {
        this.rightBitmap = rightBitmap;
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
