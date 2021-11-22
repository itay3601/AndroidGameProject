package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BubbleText {
    private int x,y,width,height;
    private Bitmap bubleText;

    public BubbleText(Resources res) {
        x=370;
        y=700;
        width=600;
        height=400;
        bubleText= BitmapFactory.decodeResource(res,R.drawable.note);
        bubleText=Bitmap.createScaledBitmap(bubleText,width,height,false);
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

    public Bitmap getBubleText() {
        return bubleText;
    }

    public void setBubleText(Bitmap bubleText) {
        this.bubleText = bubleText;
    }
}
