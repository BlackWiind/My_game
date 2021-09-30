package com.example.my_game.utilits;

import android.content.SharedPreferences;

import com.example.my_framework.CoreFW;

public class SettingsGame {
    public static int[] distance = {0000,0000,0000,0000,0000};
    
    public static void saveSettings(CoreFW coreFW){
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        editor.clear();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance" + i,distance[i]);
        }
        editor.apply();
    }

    public static void loadSettings(CoreFW coreFW){
        for (int i = 0; i < 5; i++) {
            distance[i] = coreFW.getSharedPreferences()
                    .getInt("passedDistance" + i, distance[i]);
        }
    }

    public static void addDistance(int values){
        for (int i = 0; i < 5; i++) {
            if(distance[i] == values) break;
            if (distance[i]<values){
                for (int j = 4; j > 5 ; j--) {
                    distance[i] = distance[j-1];
                }
                distance[i] = values;
                break;
            }
        }
    }
}
