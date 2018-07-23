package com.mygame.wolfpack.busov.Hexagons;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class BackGroundRightHexagon extends RightHexagon {

    public static int height = 75;
    public static double Re_height = (Game.GameScreenHeight - height) / 15;
    private int name;
    
    public Texture texture;

    public int Re_x, Re_y;

    public BackGroundRightHexagon(int x, int y, int name){
        if (name == RightHexagon.water)
            texture = new Texture("water.png");
        if (name == RightHexagon.play)
            texture = new Texture("play.png");
        if (name == RightHexagon.move)
            texture = new Texture("move.png");
        if (name == RightHexagon.save)
            texture = new Texture("save.png");
        if (name == RightHexagon.setting)
            texture = new Texture("setting.png");
        this.name = name;
        this.x = x;
        this.y = y;
        this.Re_x = x - height/2;
        this.Re_y = y - height/2;
    }

     public void setTexture(int name){
         this.name = name;
         if (name == RightHexagon.water)
             texture = new Texture("water.png");
         if (name == RightHexagon.play)
             texture = new Texture("play.png");
         if (name == RightHexagon.move)
             texture = new Texture("move.png");
         if (name == RightHexagon.save)
             texture = new Texture("save.png");
         if (name == RightHexagon.setting)
             texture = new Texture("setting.png");
    }

    public int getIntTexture() {
         return name;
    }
}
