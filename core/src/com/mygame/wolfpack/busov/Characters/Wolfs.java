package com.mygame.wolfpack.busov.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Backgrounds.PlayRightHexagon;
import com.mygame.wolfpack.busov.RightHexagon;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class Wolfs {

    public Texture texture;
    public static int height = 50;
    public int x,y;
    public int game_cell;
    private Background background;
    public boolean step = false;


    public Wolfs(int game_cell, Background background){
        texture = new Texture("Wolfs.png");
        this.game_cell = game_cell;
        this.background = background;
        x = background.play[game_cell].x - height/2;
        y = background.play[game_cell].y - height/2;
    }



    public void step(int x, int y){
        for (int playRightHexagon : background.play[game_cell].neighbors){
            if (background.play[playRightHexagon].Belong(x,y, PlayRightHexagon.height))
                game_cell = playRightHexagon;
        }
    }
}
