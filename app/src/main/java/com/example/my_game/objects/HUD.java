package com.example.my_game.objects;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_game.R;

public class HUD {
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    CoreFW coreFW;

    private final int HIEGHT_HUD = 50;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer){
        this.passedDistance = passedDistance;
        this.currentSpeedPlayer = currentSpeedPlayer;
        this.currentShieldsPlayer = currentShieldsPlayer;
    }

    public void drawing(GraphicsFW graphicsFW){
        graphicsFW.drawLine(0,graphicsFW.getWidthFrameBuffer() ,HIEGHT_HUD ,HIEGHT_HUD, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentShields)+":"+currentShieldsPlayer,
                30,30,16,null,Color.GREEN);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentSpeed)+":"+currentSpeedPlayer,
                300,30,16,null,Color.GREEN);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_passedDistance)+":"+passedDistance,
                400,30,16,null,Color.GREEN);
    }

    public int getHIEGHT_HUD() {
        return HIEGHT_HUD;
    }
}
