package com.mygame.wolfpack.busov.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Backgrounds.Background;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class Wolfs {

    public Texture texture;
    public static int height = 50;
    public int x,y;
    public int game_cell;
    public Background background;
    public ArrayList<Integer> wolf_cell;
    public boolean step = true;

    public Wolfs(int game_cell, Background background, ArrayList<Integer> wolf_cell){
        texture = new Texture("Wolfs.png");
        this.game_cell = game_cell;
        this.background = background;
        this.wolf_cell = wolf_cell;
        x = background.play[game_cell].x - height/2;
        y = background.play[game_cell].y - height/2;
    }

    public void update(){

    }

    private void Bfs(){

    }
}
