package com.mygame.wolfpack.busov;

import com.mygame.wolfpack.busov.Backgrounds.Background;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public abstract class Plays {
    protected Game game;
    protected Background background;
    protected ArrayList<Integer> forest; // Add forest cells
    protected ArrayList<Integer> mountain; // Add mountains cells

    public Plays(Game game){
        this.game = game;
        forest = new ArrayList<Integer>();
        mountain = new ArrayList<Integer>();
        Add();
        background = new Background(forest, mountain);
    }
    protected abstract void Add();
}
