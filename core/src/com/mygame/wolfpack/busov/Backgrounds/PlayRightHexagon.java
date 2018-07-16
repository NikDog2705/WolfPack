package com.mygame.wolfpack.busov.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 04.07.2018.
 */

public class PlayRightHexagon extends RightHexagon {
    public static int height = 75;
    public static int weight = 75;
    public static double Re_height = (Game.ScreenHeight - height) / 15;
    public boolean facet = false;
    public boolean left_facet = false;
    public boolean big_facet = false;
    public ArrayList<Integer> neighbors;

    public Texture texture;


    public PlayRightHexagon(int x, int y, String name){
        neighbors = new ArrayList<Integer>();
        if (name.equals("forest"))
            texture = new Texture("forest.png");
        if (name.equals("grass"))
            texture = new Texture("grass.png");
        if (name.equals("left_grass"))
            texture = new Texture("left_grass.png");
        if (name.equals("right_grass"))
            texture = new Texture("right_grass.png");
        this.x = x - weight/2;
        this.y = y - height/2;
    }

    public void setTexture(String s){
        texture = new Texture(s + ".png");
    }
}

