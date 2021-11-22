package com.example.gamev2;

public class left_btn_thread implements Runnable {

    private LeftBtn leftBtn;
    private JumpingBoy jumpingBoy;
    private boolean isStand;
    private int[] arrStepX;
    private int corentIndexStep;
    private boolean go = true;

    public left_btn_thread(LeftBtn leftBtn, JumpingBoy jumpingBoy, boolean isStand, int[] arrStepX, int corentIndexStep) {
        this.leftBtn = leftBtn;
        this.jumpingBoy = jumpingBoy;
        this.isStand = isStand;
        this.arrStepX = arrStepX;
        this.corentIndexStep = corentIndexStep;
    }

    @Override
    public void run() {
        while (go) {
            leftBtnCheck();
            /* System.out.println("left button thread is going strong");*/
        }
    }

    private void leftBtnCheck() {
        if (leftBtn.isIspress()) {

//            while (eventDuration==0){
//                jumpingBoy.setX(jumpingBoy.getX()-1);
//            }
            for (int i = 0; i < 9; i++)//size jump
                jumpingBoy.setX(jumpingBoy.getX() - i);
            leftBtn.setIspress(false);
            if (jumpingBoy.getX() < 0)
                jumpingBoy.setX(0);
            if (isStand) {

                if (jumpingBoy.getX() < arrStepX[corentIndexStep] - 100) {
                    isStand = false;
                }
            }

        }
    }

    public void setGo(boolean go) {
        this.go = go;
    }

}