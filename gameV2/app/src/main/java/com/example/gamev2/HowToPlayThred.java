package com.example.gamev2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class HowToPlayThred extends SurfaceView implements Runnable {
    private Thread thread;
    private Background background;
    private JumpingBoy jumpingBoy;
    private Finger finger;
    private Paint paint;
    private BubbleText bubbleText;
    private Paint TextPaint;
    private Resources res;


    private boolean isPlaying=true;


    public HowToPlayThred(Context context,int screemX,int screenY) {
        super(context);
        background=new Background(screemX,screenY,getResources(),true);
        jumpingBoy=new JumpingBoy(getResources());
        jumpingBoy.setY(1500);
        jumpingBoy.setX(350);
        finger=new Finger(getResources());
        res=getResources();


        bubbleText=new BubbleText(getResources());
        TextPaint = new Paint();
        TextPaint.setTextSize(40);
        TextPaint.setColor(getResources().getColor(R.color.colorAccent));







    }

    @Override
    public void run() {
        while (isPlaying) {
            sleep();
            draw();
            sleep();
            upFinger();

            draw();
            sleep();
            downFinger();
            draw();
            sleep();

            jumpingUp();
            draw();
            sleep();
            jumpingdowon();
            draw();
            sleep();
            goLeftbtn();
            draw();
            sleep();
            upFinger();
            draw();
            sleep();
            downFinger();
            draw();
            sleep();
            setJumpingBoyleft();
            draw();
            sleep();
            finger.setX(700);
            finger.setY(1200);
            draw();
            sleep();
            draw();
            sleep();
            upFinger();

            draw();
            sleep();
            downFinger();
            draw();
            sleep();
            jumpingUp();
            draw();
            sleep();
            jumpingBoy.setY(1300);
            draw();
            sleep();
            draw();
            sleep();
            upFinger();

            draw();
            sleep();
            downFinger();
            draw();
            sleep();
            jumpingBoy.setY(jumpingBoy.getY()-180);
            draw();
            sleep();
            gorightbtn();
            draw();
            sleep();
            draw();
            sleep();
            upFinger();

            draw();
            sleep();
            downFinger();
            draw();
            sleep();
            setJumpingRight();
            draw();
            sleep();
            jumpingBoy.setY(1050);



            isPlaying=false;




        }
        draw();


    }
    public void update(){}
    private void draw () {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(background.getBackgroundHowToPlay(), background.getX(), background.getY(), paint);
            canvas.drawBitmap(jumpingBoy.getJampboy(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
            canvas.drawBitmap(finger.getFinger(),finger.getX(),finger.getY(),paint);



            if(!isPlaying){
                canvas.drawBitmap(bubbleText.getBubleText(),bubbleText.getX(),bubbleText.getY(),paint);
                canvas.drawText(res.getString(R.string.help_me_climb_the_stairs)  ,450,820,TextPaint);
                canvas.drawText(res.getString(R.string.all_the_way_to_the_top),450,870,TextPaint);



            }
            getHolder().unlockCanvasAndPost(canvas);

        }

    }
    public  void newGame(){
        thread=new Thread(this);

        thread.start();
    }
    public void upFinger(){
        finger.setY(finger.getY()-70);
    }
    public void downFinger(){
        finger.setY(finger.getY()+50);
    }
    public  void sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void jumpingUp(){
        for(int i=0;i<25;i++){
            jumpingBoy.setY(jumpingBoy.getY()-i);

        }

    }
    public void jumpingdowon(){
        for(int i=0;i<25;i++){
            jumpingBoy.setY(jumpingBoy.getY()+i);

        }

    }
    public void goLeftbtn(){
        finger.setY(1800);
        finger.setX(50);

    }
    public void setJumpingBoyleft(){
        jumpingBoy.setX(jumpingBoy.getX()-200);
    }
    public void setJumpingBoyRight(){
        jumpingBoy.setX(jumpingBoy.getX()+200);
    }

    public void fingerMedle(){
        finger.setY(1500);
        finger.setX(350);
    }
    public void gorightbtn(){
        finger.setY(1800);
        finger.setX(900);


    }
    public void  setJumpingRight() {
        for (int i = 0; i < 20 ; i++) {//size jump

            jumpingBoy.setY(jumpingBoy.getY() - i);
            jumpingBoy.setX(jumpingBoy.getX() + i);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
