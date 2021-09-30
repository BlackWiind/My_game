package com.example.my_game.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.SceneFW;
import com.example.my_game.R;
import com.example.my_game.utilits.SettingsGame;

public class TopDistance extends SceneFW {

    String[] numbers = new String[5];

    public TopDistance(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i+1) + "." + SettingsGame.distance[i];
        }
    }

    @Override
    public void update() {
        if(coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)){
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.drawText(coreFW.getString(R.string.txt_result_top_distance),
                120,200,40,null, Color.GREEN);
        graphicsFW.drawText(String.valueOf(numbers[0]),
                120,250,30,null, Color.GREEN);
        graphicsFW.drawText(String.valueOf(numbers[1]),
                120,300,30,null, Color.GREEN);
        graphicsFW.drawText(String.valueOf(numbers[2]),
                120,350,30,null, Color.GREEN);
        graphicsFW.drawText(String.valueOf(numbers[3]),
                120,400,30,null, Color.GREEN);
        graphicsFW.drawText(String.valueOf(numbers[4]),
                120,450,30,null, Color.GREEN);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
}
