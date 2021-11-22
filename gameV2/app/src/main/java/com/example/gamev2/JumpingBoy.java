package com.example.gamev2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class JumpingBoy {
    private int x,y,width,height;
    private boolean isJumpin=false;
    private boolean isSand=true;
    private static boolean typeJumpinBoy1;
    private Bitmap jampboy,marioStanding,marioJumping,peachStanding,peachJumping;
    public  JumpingBoy( Resources res){
        if(!typeJumpinBoy1) {
            x = 350;
            y = 1300;
            jampboy = BitmapFactory.decodeResource(res, R.drawable.littleboy);
            peachStanding = BitmapFactory.decodeResource(res,R.drawable.princess_peach_standing);
            peachJumping = BitmapFactory.decodeResource(res,R.drawable.princess_peach_jumping);
            //marioStanding = BitmapFactory.decodeResource(res,R.drawable.mario_standing);
           // marioJumping = BitmapFactory.decodeResource(res,R.drawable.mario_jumping);
            width = jampboy.getWidth();
            height = jampboy.getHeight();


            width = 200;
            height = 200;
            jampboy = Bitmap.createScaledBitmap(jampboy, width, height, false);
            peachJumping = Bitmap.createScaledBitmap(peachJumping,width,height,false);
            peachStanding = Bitmap.createScaledBitmap(peachStanding,width,height,false);
           // marioJumping = Bitmap.createScaledBitmap(marioJumping,width,height,false);
            //marioStanding = Bitmap.createScaledBitmap(marioStanding,width,height,false);

        }else{
            x = 350;
            y = 1300;
            jampboy = BitmapFactory.decodeResource(res, R.drawable.jumping2);
            width = jampboy.getWidth();
            height = jampboy.getHeight();
            marioStanding = BitmapFactory.decodeResource(res,R.drawable.mario_standing);
            marioJumping = BitmapFactory.decodeResource(res,R.drawable.mario_jumping);


            width = 200;
            height = 200;
            jampboy = Bitmap.createScaledBitmap(jampboy, width, height, false);
            marioJumping = Bitmap.createScaledBitmap(marioJumping,width,height,false);
            marioStanding = Bitmap.createScaledBitmap(marioStanding,width,height,false);
        }


    }

    public JumpingBoy() {

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

    public boolean isJumpin() {
        return isJumpin;
    }

    public void setJumpin(boolean jumpin) {
        isJumpin = jumpin;
    }

    public Bitmap getJampboy() {
        return jampboy;
    }

    public Bitmap getMarioStanding(){return marioStanding;}

    public Bitmap getMarioJumping(){return marioJumping;}

    public Bitmap getPeachStanding(){return peachStanding;}

    public Bitmap getPeachJumping(){return peachJumping;}

    public void setJampboy(Bitmap jampboy) {
        this.jampboy = jampboy;
    }

    public boolean isSand() {
        return isSand;
    }

    public void setSand(boolean sand) {
        isSand = sand;
    }


    public static boolean isTypeJumpinBoy1() {
        return typeJumpinBoy1;
    }

    public boolean getType(){return typeJumpinBoy1;}

    public static void setTypeJumpinBoy1(boolean typeJumpinBoy1) {
        JumpingBoy.typeJumpinBoy1 = typeJumpinBoy1;
    }
}
