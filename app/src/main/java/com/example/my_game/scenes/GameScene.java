package com.example.my_game.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_game.R;
import com.example.my_game.clases.GameManager;
import com.example.my_game.generators.GeneratorBackground;
import com.example.my_game.utilits.SettingsGame;

public class GameScene extends SceneFW {

    enum GameState{
        READY,
        RUNNING,
        PAUSE,
        GAMEOVER
    }

    GameState gameState;
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW,sceneWidth,sceneHeight);
    }

    @Override
    public void update() {
        if(gameState==GameState.READY){
            updateStateReady();
        }
        if(gameState==GameState.RUNNING){
            updateStateRunning();
        }
        if(gameState==GameState.PAUSE){
            updateStatePause();
        }
        if(gameState==GameState.GAMEOVER){
            updateStateGameOver();
        }

    }

    private void updateStateReady() {
        if(coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)){
            gameState = GameState.RUNNING;
        }

    }

    private void updateStateRunning() {

        gameManager.update();
        if(GameManager.gameOver){
            gameState = gameState.GAMEOVER;
        }
    }

    private void updateStatePause() {
    }

    private void updateStateGameOver() {

        SettingsGame.addDistance(gameManager.getPassedDistance());
        if(coreFW.getTouchListenerFW().getTouchUp(250,360,300,35)){
            coreFW.setScene(new GameScene(coreFW));
        }
        if(coreFW.getTouchListenerFW().getTouchUp(250,420,300,35)){
            coreFW.setScene(new MainMenuScene(coreFW));
        }

    }

    @Override
    public void drawing() {

        if(gameState==GameState.READY){
            drawingStateReady();
        }
        if(gameState==GameState.RUNNING){
            drawingStateRunning();
        }
        if(gameState==GameState.PAUSE){
            drawingStatePause();
        }
        if(gameState==GameState.GAMEOVER){
            drawingStateGameOver();
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_Ready),
                200,250,60,null,Color.WHITE);
    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        gameManager.drawing(coreFW,graphicsFW);
    }

    private void drawingStatePause() {
    }

    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver),
                250,100, 30,null,Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart),
                250,360, 30,null,Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit),
                250,420, 30,null,Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance)
                        + ":  " + gameManager.getPassedDistance(),
                250,200, 20,null,Color.WHITE);

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
