package com.mygame.wolfpack.busov;

import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public abstract class Plays {
    protected Game game;
    protected Background background;
    protected ArrayList<Integer> forest; // Add forest cells
    protected ArrayList<Integer> mountain; // Add mountains cells
    protected ArrayList<Deer> deer;
    protected ArrayList<DeerPack> deerPack;
    protected ArrayList<Wolfs> wolfs;

    public Plays(Game game){
        this.game = game;
        forest = new ArrayList<Integer>();
        mountain = new ArrayList<Integer>();
        deer = new ArrayList<Deer>();
        deerPack = new ArrayList<DeerPack>();
        wolfs = new ArrayList<Wolfs>();
        AddBackground();
        background = new Background(forest, mountain);
        AddAnimals();
    }
    protected abstract void AddBackground();
    protected abstract void AddAnimals();
    protected abstract void update();
}
