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
    static double Re_height = (Game.ScreenHeight - height) / 15;
    boolean facet = false;
    boolean left_facet = false;
    boolean big_facet = false;
    public boolean red = false;
    public ArrayList<Integer> neighbors;
    private int name;
    public boolean wolf_here = false;

    public int Re_x, Re_y;
    private Texture texture;


    public PlayRightHexagon(int x, int y, int name){
        neighbors = new ArrayList<Integer>();
        if (name == RightHexagon.forest)
            texture = new Texture("forest.png");
        if (name == RightHexagon.grass)
            texture = new Texture("grass.png");
        if (name == RightHexagon.left_grass)
            texture = new Texture("left_grass.png");
        if (name == RightHexagon.right_grass)
            texture = new Texture("right_grass.png");
        if (name == RightHexagon.play)
            texture = new Texture("play.png");
        this.name = name;
        this.x = x;
        this.y = y;
        Re_x = x - height/2;
        Re_y = y - height/2;
    }

    public void setTexture(int name){
        if (name == RightHexagon.forest)
            texture = new Texture("forest.png");
        if (name == RightHexagon.grass)
            texture = new Texture("grass.png");
        if (name == RightHexagon.left_grass)
            texture = new Texture("left_grass.png");
        if (name == RightHexagon.right_grass)
            texture = new Texture("right_grass.png");
        if (name == RightHexagon.play)
            texture = new Texture("play.png");
    }

    public Texture getTexture(){
        return texture;
    }

    public int getIntTexture(){
        return name;
    }
}

