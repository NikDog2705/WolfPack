package com.mygame.wolfpack.busov.Screens.GameOverScreens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Hexagons.GO_TO_Menu;
import com.mygame.wolfpack.busov.Hexagons.Music;
import com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon;
import com.mygame.wolfpack.busov.Hexagons.Restart;
import com.mygame.wolfpack.busov.Hexagons.ResumeGame;
import com.mygame.wolfpack.busov.Screens.Level.level_0;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Level.level_2;
import com.mygame.wolfpack.busov.Screens.Levels;

/**
 * Created by Master_Igor on 23.07.2018.
 */

public class GameLose extends GameOver {

    private Game game;
    private Texture texture;

    public GameLose(Game game, int level_number){
        super(level_number,game);
        texture = new Texture("Loser.png");
        this.game = game;
        WhiteBackground();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.spriteBatch.begin();
            game.spriteBatch.draw(texture, (int) (Game.Re_ScreenWidht / 2 - texture.getWidth() / 2), background.play[31].Re_y);
        game.spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }
}