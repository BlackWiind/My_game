package com.example.my_game.clases;

import com.example.my_framework.CollisionDetectFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_game.generators.GeneratorBackground;
import com.example.my_game.generators.GeneratorEnemy;
import com.example.my_game.objects.HUD;
import com.example.my_game.objects.MainPlayer;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public int getPassedDistance() {
        return passedDistance;
    }

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    MainPlayer mainPlayer;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        gameOver = false;
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = hud.getHIEGHT_HUD();
        mainPlayer = new MainPlayer(maxScreenX,maxScreenY,minScreenY,coreFW);
        generatorBackground = new GeneratorBackground(sceneWidth,sceneHeight, minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth,sceneHeight,minScreenY);
    }

    public void update(){

        generatorBackground.update(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());

        passedDistance+=mainPlayer.getSpeedPlayer();
        currentSpeedPlayer = (int)mainPlayer.getSpeedPlayer();
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance,currentSpeedPlayer,currentShieldsPlayer);

        checkHit();
    }

    private void checkHit(){
        for(int i = 0; i < generatorEnemy.enemyArrayList.size(); i++){
            if(CollisionDetectFW.collisionDetect(mainPlayer,generatorEnemy.enemyArrayList.get(i))){
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW){

        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }


}
