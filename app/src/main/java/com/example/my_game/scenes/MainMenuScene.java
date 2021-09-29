package com.example.my_game.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_game.R;

public class MainMenuScene extends SceneFW {

    public MainMenuScene(CoreFW coreFW){
        super(coreFW);
    }
    @Override
    public void update() {
        if(coreFW.getTouchListenerFW().getTouchUp(100,200,300,60)){
            coreFW.setScene(new GameScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_newGame),100,200,60,null,Color.BLUE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_settings),100,300,60,null,Color.BLUE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_leaderboard),100,400,60,null,Color.BLUE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_exitGame),100,500,60,null,Color.BLUE);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
