package com.example.my_game.objects;

import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;


public class Star extends ObjectFW {
    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 3;
        this.objX = UtilRandomFW.getCasualNumber(maxScreenX);
        this.objY = UtilRandomFW.getGap(minScreenY, maxScreenY);
    }

    public void update(double speedPlayer){
        objX-=speedPlayer;
        objX-=speed;
        if(objX<minScreenX){
            this.objX = maxScreenX;
            this.objY = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
    }

    public int getX(){
        return objX;
    }

    public int getY(){
        return objY;
    }
}
