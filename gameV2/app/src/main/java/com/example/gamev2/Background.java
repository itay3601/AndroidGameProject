package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.sql.SQLOutput;

public class Background {
    private int x = 0, y = 0;

    private Bitmap backgraund;
    private Bitmap backgraund2;

    private Bitmap backgroundHowToPlay;
    private Steps stepsconter=new Steps();

    public Background( int screenX, int screemY, Resources res,boolean howToPlay) {
        backgroundHowToPlay = BitmapFactory.decodeResource(res, R.drawable.new_how_to_play_beackground);
        backgroundHowToPlay = Bitmap.createScaledBitmap(backgroundHowToPlay, screenX, screemY, false);
    }

    Background(int screenX, int screemY, Resources res) {

        ///////beckgroun111////
        if(stepsconter.getConterStep()>70){
            backgraund = BitmapFactory.decodeResource(res, R.drawable.space);
            backgraund = Bitmap.createScaledBitmap(backgraund, screenX, screemY, false);

        }else if (stepsconter.getConterStep() > 20) {

            backgraund = BitmapFactory.decodeResource(res, R.drawable.beakgraund3);
            backgraund = Bitmap.createScaledBitmap(backgraund, screenX, screemY, false);
        }
        else{
            backgraund = BitmapFactory.decodeResource(res, R.drawable.blou_wall);
            backgraund = Bitmap.createScaledBitmap(backgraund, screenX, screemY, false);
//////beground 2////////
        }
        if(stepsconter.getConterStep()>50){
            backgraund2 = BitmapFactory.decodeResource(res, R.drawable.sky);
            backgraund2 = Bitmap.createScaledBitmap(backgraund2, screenX, screemY, false);

        }
        else  {


            backgraund2 = BitmapFactory.decodeResource(res, R.drawable.good_wall);
            backgraund2 = Bitmap.createScaledBitmap(backgraund2, screenX, screemY, false);
        }
    }



    public Bitmap getBackgraund2() {
        return backgraund2;
    }

    public void setBackgraund2(Bitmap backgraund2) {
        this.backgraund2 = backgraund2;
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

    public Bitmap getBackgraund() {
        return backgraund;
    }

    public void setBackgraund(Bitmap backgraund) {
        this.backgraund = backgraund;
    }

    public Bitmap getBackgroundHowToPlay() {
        return backgroundHowToPlay;
    }

    public void setBackgroundHowToPlay(Bitmap backgroundHowToPlay) {
        this.backgroundHowToPlay = backgroundHowToPlay;
    }


}