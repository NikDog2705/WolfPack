package com.mygame.wolfpack.busov.Screens.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Plays;
import com.mygame.wolfpack.busov.RightHexagon;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class level_1 extends Plays {

    public level_1(Game game) {
        super(game,3);
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
