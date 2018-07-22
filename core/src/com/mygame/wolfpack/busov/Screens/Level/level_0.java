package com.mygame.wolfpack.busov.Screens.Level;

import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Plays;
import com.badlogic.gdx.Screen;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class level_0 extends Plays {


    public level_0(Game game) {
        super(game, 4, 0);
    }

    @Override
    protected void AddBackground() {
        for (int i = 23; i < 30; ++i)
            mountain.add(i);
        for (int i = 90; i < 98; ++i)
            mountain.add(i);
        forest.add(53);
        forest.add(61);
        forest.add(69);
        forest.add(77);
        forest.add(70);
        forest.add(63);
        forest.add(71);
        forest.add(79);
        forest.add(72);
        forest.add(65);
        forest.add(58);
    }

    @Override
    protected void AddAnimals() {
        deer.add(33);
        deerPack.add(78);
    }
}
