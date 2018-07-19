package com.mygame.wolfpack.busov.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class BackGroundRightHexagon extends RightHexagon {

    public static int height = 75;
    static double Re_height = (Game.ScreenHeight - height) / 15;
    
    Texture texture;

    public int Re_x, Re_y;

    BackGroundRightHexagon(int x, int y, int name){
        if (name == RightHexagon.water)
            texture = new Texture("water.png");
        if (name == RightHexagon.play)
            texture = new Texture("play.png");
        this.x = x;
        this.y = y;
        this.Re_x = x - height/2;
        this.Re_y = y - height/2;
    }

     public void setTexture(int name){
         if (name == RightHexagon.play)
             texture = new Texture("play.png");
        if (name == RightHexagon.water)
            texture = new Texture("water.png");
    }
}
