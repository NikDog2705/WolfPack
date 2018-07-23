package com.mygame.wolfpack.busov.Screens.GameOverScreens;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;

/**
 * Created by Master_Igor on 23.07.2018.
 */

public class GameWin extends GameOver {

    private Game game;
    private Texture texture;

    public GameWin(Game game, int level_number){
        super(level_number,game);
        texture = new Texture("Winner.png");
        this.game = game;
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