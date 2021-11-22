package com.example.gamev2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class GameViewTest extends SurfaceView implements Runnable, DialogInterface.OnClickListener {

    Runnable runnable ;

    private PropertyChangeSupport pcs;

    private boolean MotionControll;

    private Steps root;
    private Thread thread;

    private boolean isPlaying = true;
    private Background background1, background2;
    private int screemX, screemY;
    private Paint paint;
    private JumpingBoy jumpingBoy;
    private LeftBtn leftBtn;
    private RightBtn rightBtn;
    private int numEvent;
    private boolean isStand=true;

    private boolean flag=false;
    private long eventDurationleft = 1;
    private long eventDurationright = 1;

    private Resources res;
    private  int numberOfStepJumpinngBoy;
    private Steps[] arrSteps;
    private int[] arrStepY;
    private  int[] arrStepX;
    private int corentIndexStep=0;
    private  boolean startUpdateMove=false;
    private int correntY=0;
    private int numStep=7;
    private Steps firststep;
    private Steps stepSpeed=new Steps(getResources());;
    private   int  score;
    static  int   counter=0;

    private boolean startBeckgraund=true;
    private boolean startBeckgraund3=true;
    private boolean startBeckgraund4=true;
    private boolean startBeckgraund5=true;

    private Paint TextPaint;


    private left_btn_thread leftBtnThread;
    private right_btn_thread rightBtnThread;
    private Thread left_btn_t;
    private Thread right_btn_t;








    public GameViewTest(Context context, int screemX, int screemY) {
        super(context);
        isStand=true;
        res = getResources();
        TextPaint = new Paint();
        TextPaint.setTextSize(100);
        TextPaint.setColor(getResources().getColor(R.color.colorAccent));

        MotionControll = true;
        pcs = new PropertyChangeSupport(this);
        score = 0;

        this.screemX = screemX;
        this.screemY = screemY;
        background1 = new Background(screemX, screemY, getResources());
        background2 = new Background(screemX, screemY, getResources());
        background2.setY(screemY);
        paint = new Paint();
        jumpingBoy = new JumpingBoy(getResources());
        rightBtn = new RightBtn(getResources());
        leftBtn = new LeftBtn(getResources());
        root=new Steps();







        arrSteps = new Steps[numStep];
        for (int z = 0; z < numStep; z++) {
            arrSteps[z] = new Steps(res);

        }
        int t=0;
        arrStepY=new int[numStep];
        for(int z=0;z<numStep;z++){
            arrStepY[z]=arrSteps[z].getY()-t;
            t+=300;

        }
        arrStepX=new int[numStep];
        for(int z=0;z<numStep;z++){
            arrStepX[z]=arrSteps[z].getX();
            Log.e(""+z+":",""+arrStepX[z]);
        }
        if(!isPlaying){

            stepSpeed.setConterStep(0);
            stepSpeed.setHeightStep(0);
        }


        leftBtnThread =  new left_btn_thread(leftBtn,jumpingBoy,isStand,arrStepX,corentIndexStep);
        rightBtnThread = new right_btn_thread(rightBtn,jumpingBoy,isStand,arrStepX,corentIndexStep);
        left_btn_t = new Thread(leftBtnThread);
        right_btn_t = new Thread(rightBtnThread);
        left_btn_t.start();
        right_btn_t.start();


    }





    public void setMotionControl(boolean MotionControl)
    {
        MotionControll = MotionControl;
    }
    @Override
    public void run() {
        if(!MotionControll){
            rightBtnThread.setGo(false);
            leftBtnThread.setGo(false);
        }
        while (isPlaying) {
            update();
            draw();
            sleep();
            if(MotionControll)
                 amIstanding();
            //jumpingUp();
        }
        draw();
        pcs.firePropertyChange("Game Over",false,true);
    }

    private void update() {
        if((root.getConterStep()>20)&&(startBeckgraund)) {
            updateBeckgrund(background1,background2);
            if(background2.getY()>=0){
                startBeckgraund=false;
                background2.setY(0);
                background1=new Background(screemX,screemY,getResources());
                background1.setY(screemY);
            }
        }
//        if(!isPlaying){
//
//            stepSpeed.setConterStep(0);
//            stepSpeed.setHeightStep(0);
//        }



        if((root.getConterStep()>50)&&(startBeckgraund3)) {

            updateBeckgrund(background2,background1);
            if(background1.getY()>=0){
                startBeckgraund3=false;
                background1.setY(0);
                background2=new Background(screemX,screemY,getResources());
                background2.setY(screemY);
            }
        }
        if((root.getConterStep()>75)&&(startBeckgraund4)) {

            updateBeckgrund(background1,background2);
            if(background2.getY()>=0){
                startBeckgraund4=false;
                background2.setY(0);
                background1=new Background(screemX,screemY,getResources());
                background1.setY(screemY);
            }
        }
        if((root.getConterStep()>100)&&(startBeckgraund5)) {

            updateBeckgrund(background2,background1);
            if(background1.getY()>=0){
                startBeckgraund5=false;
                background1.setY(0);

            }
        }






        for(int z=0;z<numStep;z++){
            arrStepX[z]=arrSteps[z].getX();}
        for(int z=0;z<numStep;z++){
            arrStepY[z]=arrSteps[z].getY();}
        jumpingUp();


        if(numberOfStepJumpinngBoy>2) {
            startUpdateMove = true;
        }
        jumpingUp();


        if(startUpdateMove) {
            for (int IndexToSpeedStep = 0; IndexToSpeedStep < numStep; IndexToSpeedStep++) {
                arrSteps[IndexToSpeedStep].setY(arrSteps[IndexToSpeedStep].getY() + stepSpeed.getSpeedMove());
                arrStepY[IndexToSpeedStep] = arrStepY[IndexToSpeedStep] + stepSpeed.getSpeedMove();
                if(arrSteps[IndexToSpeedStep].getY()>1700){
                    arrSteps[IndexToSpeedStep]=new Steps(res);
                    counter++;
                }



            }
            arrSteps[0].setHeightStep(arrSteps[0].getHeightStep()-stepSpeed.getSpeedMove());


            // firststep.setY(firststep.getY()+3);

            jumpingBoy.setY(jumpingBoy.getY() + stepSpeed.getSpeedMove());

            jumpingUp();


        }

        jumpingUp();


        if (eventDurationleft == 0) {
            //leftBtn.setIspress(true);
            if(!MotionControll){
                leftBtn.setIspress(true);
                leftbtn();
            }
          //  leftbtn();

        }
        if (eventDurationright == 0) {
            //rightBtn.setIspress(true);
            if(!MotionControll){
                rightBtn.setIspress(true);
                rightbtn();
            }
          //  rightbtn();


        }





        //updateBeckgrund();
        jumpingUp();
        if(!MotionControll)
        {
            leftbtn();
            rightbtn();
        }
        //leftbtn();
        //rightbtn();



    }

    private void draw () {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();


            canvas.drawBitmap(background2.getBackgraund2(), background2.getX(), background2.getY(), paint);
            canvas.drawBitmap(background1.getBackgraund(), background1.getX(), background1.getY(), paint);


            for(int i=0;i<arrSteps.length;i++) {

                canvas.drawBitmap(arrSteps[i].getStep(), arrSteps[i].getX(), arrSteps[i].getY(), paint);
            }

            canvas.drawText(Integer.toString(stepSpeed.getConterStep()) ,100,100,TextPaint);

            //



            if(jumpingBoy.getType()) {
                if (!jumpingBoy.isJumpin()) {
                    if (isStand)
                        canvas.drawBitmap(jumpingBoy.getMarioStanding(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                    else
                        canvas.drawBitmap(jumpingBoy.getMarioJumping(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                } else {
                    canvas.drawBitmap(jumpingBoy.getMarioJumping(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                }
            }
            else
            {
                if (!jumpingBoy.isJumpin()) {
                    if (isStand)
                        canvas.drawBitmap(jumpingBoy.getPeachStanding(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                    else
                        canvas.drawBitmap(jumpingBoy.getPeachJumping(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                } else {
                    canvas.drawBitmap(jumpingBoy.getPeachJumping(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
                }
            }
              //canvas.drawBitmap(jumpingBoy.getJampboy(), jumpingBoy.getX(), jumpingBoy.getY(), paint);
            if(!MotionControll)
            {
                canvas.drawBitmap(leftBtn.getLeftBitmap(), leftBtn.getX(), leftBtn.getY(), paint);
                canvas.drawBitmap(rightBtn.getRightBitmap(), rightBtn.getX(), rightBtn.getY(), paint);
            }

            //canvas.drawBitmap(leftBtn.getLeftBitmap(), leftBtn.getX(), leftBtn.getY(), paint);
           // canvas.drawBitmap(rightBtn.getRightBitmap(), rightBtn.getX(), rightBtn.getY(), paint);
            getHolder().unlockCanvasAndPost(canvas);

        }

    }
    public  void newGame(){
        isPlaying=true;
        thread=new Thread(this);

        thread.start();
    }

    public void sleep () {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void resume () {
        isPlaying=true;
        // Thread = new Thread(runnable);
        // thread.start();
    }

    public void pause () throws InterruptedException {
        isPlaying=false;

    }
    public void destroy(){

        isPlaying=false;
        stepSpeed.setConterStep(0);
        stepSpeed.setHeightStep(0);
        thread.interrupt();



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onTouchEvent (MotionEvent event){
        numEvent = event.getActionButton();
        float x = event.getX();
        float y = event.getY();
        Log.e("","X = "+x);
        Log.e("","Y = "+y);


        if (numEvent == MotionEvent.ACTION_DOWN) {

            //   System.out.println(eventDuration);
            if ((x > 0) && (x <= 288) && (y > 1600) && (y < 1888)) {
                eventDurationleft = event.getEventTime() - event.getDownTime();
                Log.e("" ,""+eventDurationleft );
                if(!MotionControll){
                    leftBtn.setIspress(true);
                }
                //leftBtn.setIspress(true);
            } else if ((x > 792) && (x <= 1080) && (y > 1600) && (y < 1888)) {
                eventDurationright = event.getEventTime() - event.getDownTime();
                Log.e("" ,""+eventDurationright );
                if(!MotionControll){
                    rightBtn.setIspress(true);
                }
                //rightBtn.setIspress(true);

            } else if(!jumpingBoy.isJumpin()){
                jumpingBoy.setJumpin(true);
                correntY=jumpingBoy.getY();



            }

            if(!MotionControll)
            {
                rightbtn();
                leftbtn();
            }
           // rightbtn();
           // leftbtn();
        }

        return true;
    }

    public void leftbtn () {
        if(leftBtn.isIspress()&&(jumpingBoy.isJumpin())) {
            for (int i = 0; i < 7+(stepSpeed.getSpeedMove()/3); i++){//size jump
                jumpingBoy.setY(jumpingBoy.getY()-i);
                jumpingBoy.setX(jumpingBoy.getX() -i);
            }


        }else if(leftBtn.isIspress()&&(!jumpingBoy.isJumpin())){
            for (int i = 0; i < 7+(stepSpeed.getSpeedMove()/3); i++)//size jump
                jumpingBoy.setX(jumpingBoy.getX() -i);}
        leftBtn.setIspress(false);
        if (jumpingBoy.getX() < 0)
            jumpingBoy.setX(0);
        if(flag){

            if(jumpingBoy.getX() < arrStepX[numberOfStepJumpinngBoy]-100){
                isStand=false;
                flag=false;
            }
        }

    }



    public void rightbtn () {
        if(rightBtn.isIspress()&&(jumpingBoy.isJumpin())) {
            for (int i = 0; i < 7+(stepSpeed.getSpeedMove()/3); i++){//size jump

                jumpingBoy.setY(jumpingBoy.getY()-i);
                jumpingBoy.setX(jumpingBoy.getX() + i);}


        }else if(rightBtn.isIspress()&&(!jumpingBoy.isJumpin())){
            for (int i = 0; i < 7+(stepSpeed.getSpeedMove()/3); i++)//size jump
                jumpingBoy.setX(jumpingBoy.getX() + i);}
        rightBtn.setIspress(false);
        if (jumpingBoy.getX() > 800)
            jumpingBoy.setX(800);
        if(flag){


            if(jumpingBoy.getX() > arrStepX[numberOfStepJumpinngBoy]+200) {

                isStand=false;
                flag=false;

            }

        }
    }

    public void amIstanding()
    {
        //jumpingUp();
        if(flag && !jumpingBoy.isJumpin()){


            if(jumpingBoy.getX() > arrStepX[numberOfStepJumpinngBoy]+200) {

                isStand=false;
                flag=false;

            }
            else if(jumpingBoy.getX() < arrStepX[numberOfStepJumpinngBoy]-100){
                isStand=false;
                flag=false;
            }
        }
    }

    public void jumpingUp () {


        if (jumpingBoy.isJumpin() && (isStand)) {
            jumpingBoy.setY(jumpingBoy.getY() -7);
            isJunpingBoyOnStep();
            if(!MotionControll){
                rightbtn();
                leftbtn();
            }
            //rightbtn();
            //leftbtn();


            if(jumpingBoy.getY()<correntY-500){

                jumpingBoy.setJumpin(false);
                isStand=false;
            }

        }

        if((!isStand)) {// dwon to begining
            jumpingBoy.setY(jumpingBoy.getY() + 3+(stepSpeed.getSpeedMove()-1));
            isStand = false;
            jumpingBoy.setJumpin(false);
            if (isJunpingBoyOnStep()) {
                jumpingBoy.setY(arrStepY[numberOfStepJumpinngBoy] - 180);


                correntY = jumpingBoy.getY();
                isStand = true;
                flag = true;

            }
            isJunpingBoyOnStep();


            /*}else if(jumpingBoy.getY()>arrStepY[numberOfStepJumpinngBoy]){
                if(numberOfStepJumpinngBoy>0){
                    numberOfStepJumpinngBoy--;

                }else{
                    numberOfStepJumpinngBoy=6;
                }
                if(inLimitOfStep()){

                    jumpingBoy.setY(arrStepY[numberOfStepJumpinngBoy]-200);
                    isStand=true;
                    flag=true;

                    correntY=jumpingBoy.getY();

                    if (numberOfStepJumpinngBoy < 500) {
                        numberOfStepJumpinngBoy= (numberOfStepJumpinngBoy+1)%7;
                    }else{
                        if(numberOfStepJumpinngBoy>0){
                            numberOfStepJumpinngBoy--;
                        }else {
                            numberOfStepJumpinngBoy=6;
                        }
                    }
                }
            }

        }*/

        }


        if (jumpingBoy.getY() < 0)
            isStand=false;
        if ((jumpingBoy.getY() > 1300)&&(!startUpdateMove)) {
            jumpingBoy.setY(1300);
            flag = false;
            isStand = true;
            correntY = jumpingBoy.getY();


        }else if(jumpingBoy.getY()>screemY&&(startUpdateMove)){
            isPlaying=false;
            //pcs.firePropertyChange("Game Over",false,true);

        }


    }

    public boolean getIsplaying()
    {
        return isPlaying;
    }
    public boolean isJunpingBoyOnStep() {

        for (int i = 0; i < arrSteps.length; i++) {
            if (((jumpingBoy.getX() >= arrStepX[i] - 100)) && (jumpingBoy.getX() <= arrStepX[i] + 200) && (jumpingBoy.getY() + 200 < arrStepY[i]) && (jumpingBoy.getY() > arrStepY[i] - 220)) {
                numberOfStepJumpinngBoy = i;

                return true;

            }


        }
        return false;
    }





    public void updateBeckgrund (Background beck1,Background beck2) {
        beck1.setY(beck1.getY() + stepSpeed.getSpeedMove());
        beck2.setY(beck2.getY() + stepSpeed.getSpeedMove());


        if (beck1.getBackgraund().getHeight() - beck1.getY() <= 0) {
            beck1.setY(-screemY);
        }
        if (beck2.getBackgraund().getHeight() - beck2.getY() <= 0) {
            beck2.setY(-screemY);
        }

    }






    public boolean inLimitOfStep() {
        if (((jumpingBoy.getX() >= arrStepX[numberOfStepJumpinngBoy] - 100)) && (jumpingBoy.getX() <= arrStepX[numberOfStepJumpinngBoy] + 200) && (jumpingBoy.getY() + 200 < arrStepY[numberOfStepJumpinngBoy]) && (jumpingBoy.getY() > arrStepY[numberOfStepJumpinngBoy] - 500)) {
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void SensorTo_rightButton(boolean b){rightBtn.setIspress(b);}

    public void SensorTo_leftButton(boolean b){leftBtn.setIspress(b);}


    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==DialogInterface.BUTTON_POSITIVE){

        }
    }


    public void addPropertyChangeListener(java.beans.PropertyChangeListener propertyChangeListener)
    {
        pcs.addPropertyChangeListener(propertyChangeListener);
    }


}




