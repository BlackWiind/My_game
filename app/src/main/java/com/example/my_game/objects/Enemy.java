package com.example.my_game.objects;

import android.graphics.Rect;

import com.example.my_framework.AnimationGameFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;
import com.example.my_game.utilits.UtilResource;

public class Enemy extends ObjectFW {

    AnimationGameFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        objX= maxScreenX;
        objY= UtilRandomFW.getGap(minScreenY,maxScreenY);
        switch (enemyType){
            case 1:
                speed = UtilRandomFW.getGap(2,6);
                animEnemy = new AnimationGameFW(3,
                        UtilResource.spriteEnemy.get(0),
                        UtilResource.spriteEnemy.get(1),
                        UtilResource.spriteEnemy.get(2),
                        UtilResource.spriteEnemy.get(3));
                break;
            case 2:
                speed = UtilRandomFW.getGap(4,9);
                break;
        }
        radius = UtilResource.spriteEnemy.get(0).getHeight()/2;
    }

    public void update(double speedPlayer){
        objX-=speed;
        objX-=speedPlayer;
        if(objX<minScreenX){
            objX = maxScreenX;
            objY = UtilRandomFW.getGap(minScreenY,maxScreenY);
        }
        animEnemy.runAnimation();

        hitBox = new Rect(objX,objY,
                UtilResource.spriteEnemy.get(0).getWidth(),
                UtilResource.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW){
        animEnemy.drawingAnimation(graphicsFW,objX,objY);
    }
}
