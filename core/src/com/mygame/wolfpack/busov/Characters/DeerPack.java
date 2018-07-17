package com.mygame.wolfpack.busov.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Backgrounds.Background;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 16.07.2018.
 */

public class DeerPack {
    public Texture texture;
    public static int height = 50;
    public int x,y;
    public int game_cell;
    public Background background;
    public int d = 0;
    public int length = 0;
    public ArrayList<Integer> path;

    public DeerPack(){
        path = new ArrayList<Integer>();
        texture = new Texture("DeerPack.png");
    }

    public DeerPack(int game_cell, Background background){
        texture = new Texture("DeerPack.png");
        this.game_cell = game_cell;
        this.background = background;
        path = new ArrayList<Integer>();
        x = background.play[game_cell].Re_x - height/2;
        y = background.play[game_cell].Re_y - height/2;
    }
}
