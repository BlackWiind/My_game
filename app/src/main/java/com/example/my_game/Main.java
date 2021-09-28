package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.my_framework.CoreFW;
import com.example.my_framework.LoopFW;
import com.example.my_framework.SceneFW;
import com.example.my_game.clases.LoaderAssets;
import com.example.my_game.scenes.MainMenuScene;

public class Main extends CoreFW {

    public SceneFW getStartScene() {

        LoaderAssets loaderAssets = new LoaderAssets(this,this.getGraphicsFW());
        return  new MainMenuScene(this);
    }
}