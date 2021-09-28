package com.example.my_game.generators;

import com.example.my_framework.GraphicsFW;
import com.example.my_game.objects.Enemy;

import java.util.ArrayList;

public class GeneratorEnemy {

    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        this.minScreenY = minScreenY;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer){

        if (enemyArrayList.size() < 3) {
            addMeteor(speedPlayer,3);
        }

        for(int i = 0; i < enemyArrayList.size(); i++){
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    private void addMeteor(double speedPlayer, int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++){
            enemyArrayList.add(new Enemy(maxScreenX,maxScreenY,minScreenY,1));
        }
    }

    public void drawing(GraphicsFW graphicsFW){
        for (int i = 0; i < enemyArrayList.size(); i++){
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }


    public void hitPlayer(Enemy enemy) {
        for(int i = 0;i <enemyArrayList.size();i++){
            enemyArrayList.remove(enemy);
        }
    }
}
