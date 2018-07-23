package com.mygame.wolfpack.busov.Hexagons;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.RightHexagon;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class LevelRightHexagon extends RightHexagon {

    public Texture texture;
    public int number;
    public int Re_x,Re_y;
    public static int height = 90;

    public LevelRightHexagon(int x, int y, int number){
        texture = new Texture("level.png");
        this.number = number;
        this.x = x;
        this.y = y;
        Re_x = x - height/2;
        Re_y = y - height/2;
    }
}

