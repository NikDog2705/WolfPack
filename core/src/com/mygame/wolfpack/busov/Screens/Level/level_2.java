package com.mygame.wolfpack.busov.Screens.Level;

import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Plays;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class level_2 extends Plays {


    public level_2(Game game) {
        super(game, 4, 2);
    }

    @Override
    protected void AddBackground() {
        forest.add(39);
        forest.add(41);
        forest.add(40);
        forest.add(103);
        mountain.add(43);
        mountain.add(42);
    }

    @Override
    protected void AddAnimals() {
        deerPack.add(47);
        deerPack.add(119);
        deer.add(35);
    }
}
