package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Steps {

    private int x, y, width, height;
    private static int heightStep=0;
    private    Bitmap step;
    private static int conterStep=0;
    private static int speedMove=3;

    public Steps() {
    }

    public Steps(Resources res) {
        Random rnd=new Random();
        conterStep++;
        if(speedMove<37)
            if(conterStep%10==0){
                speedMove+=1;
            }
        if(conterStep<21) {

            y = 1500 - heightStep;
            heightStep += 270;
            x = rnd.nextInt(500);
            // x=500;
            step = BitmapFactory.decodeResource(res, R.drawable.goodpipe);
            width = step.getWidth();
            height = step.getHeight();
            Random rndWidh = new Random();
            width = 300;
            //width /= (rndWidh.nextInt(5)+5);
            height = 70;
            step = Bitmap.createScaledBitmap(step, width, height, false);

        }else if(conterStep<52){
            y = 1500 - heightStep;
            heightStep += 270;
            x = rnd.nextInt(500);
            // x=500;
            step = BitmapFactory.decodeResource(res, R.drawable.step_brown);
            width = 300;
            height = 70;
            step = Bitmap.createScaledBitmap(step, width, height, false);
        }else if(conterStep<76){
            y = 1500 - heightStep;
            heightStep += 270;
            x = rnd.nextInt(500);
            // x=500;
            step = BitmapFactory.decodeResource(res, R.drawable.pngfuel);
            width = 300;
            height = 100;
            step = Bitmap.createScaledBitmap(step, width, height, false);
        }else if(conterStep<101){  y = 1500 - heightStep;
            heightStep += 270;
            x = rnd.nextInt(500);
            // x=500;
            step = BitmapFactory.decodeResource(res, R.drawable.cloud);
            width = 300;
            height = 100;
            step = Bitmap.createScaledBitmap(step, width, height, false);
        }else{
            y = 1500 - heightStep;
            heightStep += 270;
            x = rnd.nextInt(500);
            // x=500;
            step = BitmapFactory.decodeResource(res, R.drawable.spaceship);
            width = 300;
            height = 100;
            step = Bitmap.createScaledBitmap(step, width, height, false);
        }

    }



    public static int getSpeedMove() {
        return speedMove;
    }

    public static void setSpeedMove(int speedMove) {
        Steps.speedMove = speedMove;
    }

    public int getConterStep() {
        return conterStep;
    }

    public void setConterStep(int conterStep) {
        this.conterStep = conterStep;
    }

    public static int getHeightStep() {
        return heightStep;
    }

    public static void setHeightStep(int heightStep) {
        Steps.heightStep = heightStep;
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

    public Bitmap getStep() {
        return step;
    }

    public void setStep(Bitmap step) {
        this.step = step;
    }
}
