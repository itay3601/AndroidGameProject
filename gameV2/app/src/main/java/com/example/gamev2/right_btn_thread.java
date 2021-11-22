package com.example.gamev2;

import java.beans.PropertyChangeSupport;

public class right_btn_thread implements Runnable {

    private RightBtn rightBtn;
    private JumpingBoy jumpingBoy;
    private boolean isStand;
    private int[] arrStepX;
    private int corentIndexStep;
    private boolean go = true;



    public right_btn_thread(RightBtn rightBtn,JumpingBoy jumpingBoy,boolean isStand,int []arrStepX,int corentIndexStep)
    {
        this.arrStepX = arrStepX;
        this.rightBtn = rightBtn;
        this.jumpingBoy = jumpingBoy;
        this.isStand = isStand;
        this.corentIndexStep = corentIndexStep;

    }
    @Override
    public void run() {
        while(go)
        {

            rightButtoncheck();
            //System.out.println("check right button");
        }
    }


    private void rightButtoncheck()
    {
        if (rightBtn.isIspress()) {
            //   while (eventDuration==0){
            //     jumpingBoy.setX(jumpingBoy.getX()+1);
            // }
            for (int i = 0; i < 9; i++)//size jump
                jumpingBoy.setX(jumpingBoy.getX() + i);
            rightBtn.setIspress(false);
            if (jumpingBoy.getX() > 800)
                jumpingBoy.setX(800);
            if(isStand){


                if(jumpingBoy.getX() > arrStepX[corentIndexStep]+200) {
                    isStand=false;

                }
            }
        }
    }
    public void setGo(boolean go)
    {
        this.go = go;
        System.out.println("this is go" + go);
    }




}
