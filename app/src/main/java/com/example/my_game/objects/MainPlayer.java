package com.example.my_game.objects;

import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilTimerDelayFW;
import com.example.my_game.clases.GameManager;
import com.example.my_game.utilits.UtilResource;
import com.example.my_framework.AnimationGameFW;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -4;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    AnimationGameFW animMainPlayer;
    AnimationGameFW animPlayerBoost;
    AnimationGameFW animExplosionPlayer;

    boolean boosting;
    boolean hitEnemy;
    boolean isGameOver;
    private int shields;

    CoreFW coreFW;
    UtilTimerDelayFW timerOnShieldHit;
    UtilTimerDelayFW timerOnGameOver;


    public MainPlayer(int maxScreenX, int maxScreenY, int minScreenY, CoreFW coreFW) {
        objX=50;
        objY=100;
        speed = 3;
        shields = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;

        radius = UtilResource.spritePlayer.get(0).getHeight()/4;

        timerOnShieldHit = new UtilTimerDelayFW();
        timerOnGameOver = new UtilTimerDelayFW();

        this.coreFW = coreFW;
        this.minScreenY = minScreenY;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
        animMainPlayer = new AnimationGameFW(speed,
                UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));

        animPlayerBoost = new AnimationGameFW(speed,
                UtilResource.spritePlayerBoost.get(0),
                UtilResource.spritePlayerBoost.get(1),
                UtilResource.spritePlayerBoost.get(2),
                UtilResource.spritePlayerBoost.get(3));

        animExplosionPlayer = new AnimationGameFW(speed,
                UtilResource.spriteExplosionPlayer.get(0),
                UtilResource.spriteExplosionPlayer.get(1),
                UtilResource.spriteExplosionPlayer.get(2),
                UtilResource.spriteExplosionPlayer.get(3));
    }

    public void update(){
        if (coreFW.getTouchListenerFW().getTouchDown(0,maxScreenY,maxScreenX,maxScreenY)){
            startBoosting();
        }
        if (coreFW.getTouchListenerFW().getTouchUp(0,maxScreenY,maxScreenX,maxScreenY)){
            stopBoosting();
        }

        if(boosting){
            speed+=0.1;
        } else speed-= 3;

        if (speed>MAX_SPEED){
            speed = MAX_SPEED;
        }
        if(speed<MIN_SPEED){
            speed = MIN_SPEED;
        }

        objY -= speed+GRAVITY;
        if (objY<minScreenY){
            objY = minScreenY;
        }
        if (objY>maxScreenY){
            objY = maxScreenY;
        }

        if(boosting){
            animPlayerBoost.runAnimation();
        } else animMainPlayer.runAnimation();

        hitBox = new Rect(objX,objY,
                UtilResource.spritePlayer.get(0).getWidth(),
                UtilResource.spritePlayer.get(0).getHeight());
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW){
        if(!isGameOver){
            if(!hitEnemy){
                if(boosting){
                    animPlayerBoost.drawingAnimation(graphicsFW,objX,objY);
                } else animMainPlayer.drawingAnimation(graphicsFW,objX,objY);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy,objX,objY);
                if(timerOnShieldHit.timerDelay(0.5)){
                    hitEnemy = false;
                } else {
                    hitEnemy = true;
                }
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW,objX,objY);
            if(timerOnGameOver.timerDelay((1))){
                GameManager.gameOver = true;
            }
        }

    }

    public double getSpeedPlayer(){
        return speed;
    }
    public int getShieldsPlayer(){ return  shields;}

    public void hitEnemy() {
        shields--;
        if(shields<0){
            isGameOver = true;
        }
        hitEnemy = true;
        timerOnShieldHit.startTimer();
    }
}
