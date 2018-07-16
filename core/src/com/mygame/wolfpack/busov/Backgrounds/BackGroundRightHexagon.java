package com.mygame.wolfpack.busov.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class BackGroundRightHexagon extends RightHexagon {

    public static int height = 75;
    public static double Re_height = (Game.ScreenHeight - height) / 15;
    
    public Texture texture;


    public BackGroundRightHexagon(int x, int y, int name){
        if (name == RightHexagon.mountain)
            texture = new Texture("mountain.png");
        if (name == RightHexagon.water)
            texture = new Texture("water.png");
        this.x = x - height/2;
        this.y = y - height/2;
    }

    public void setTexture(int name){
        if (name == RightHexagon.mountain)
            texture = new Texture("mountain.png");
        if (name == RightHexagon.water)
            texture = new Texture("water.png");
    }
}
