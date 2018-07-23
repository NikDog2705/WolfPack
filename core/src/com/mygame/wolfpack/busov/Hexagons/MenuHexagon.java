package com.mygame.wolfpack.busov.Hexagons;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.RightHexagon;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class MenuHexagon extends RightHexagon {

    public Texture texture;
    public int Re_x,Re_y;
    public static int height = 75;

    public MenuHexagon(int x, int y){
        texture = new Texture("MenuHexagon.png");
        this.x = x;
        this.y = y;
        Re_x = x - height/2;
        Re_y = y - height/2;
    }

}
