package com.mygame.wolfpack.busov.Screens.GameOverScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon;
import com.mygame.wolfpack.busov.Hexagons.GO_TO_Menu;
import com.mygame.wolfpack.busov.Hexagons.MenuHexagon;
import com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon;
import com.mygame.wolfpack.busov.Hexagons.Restart;
import com.mygame.wolfpack.busov.Hexagons.ResumeGame;
import com.mygame.wolfpack.busov.RightHexagon;
import com.mygame.wolfpack.busov.Screens.Level.level_0;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Level.level_2;
import com.mygame.wolfpack.busov.Screens.Levels;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public class GameOver extends InputAdapter implements Screen {

    private Game game;
    private int level_number;
    Background background;
    private Restart restart;
    private GO_TO_Menu go_to_menu;

    public GameOver(int level_number, Game game){
        this.game = game;
        this.level_number = level_number;
        background = new Background();
        restart = new Restart( background.play[62].Re_x, background.play[62].Re_y);
        go_to_menu = new GO_TO_Menu(background.play[66].Re_x, background.play[66].Re_y);

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int x,int y,int pointer,int button) {
                y =(int) ((Gdx.graphics.getHeight() - y) * ((Game.Re_ScreenHeight / Gdx.graphics.getHeight())));
                x = (int) (x * (Game.Re_ScreenWidht / Gdx.graphics.getWidth()));
               update(x, y);
                return true;
            }
        });

    }

    protected void WhiteBackground(){
        for (PlayRightHexagon playRightHexagon : background.play)
            playRightHexagon.setTexture(RightHexagon.menu);
    }

    private void update(int x, int y){
        if (go_to_menu.Belong(x, y, GO_TO_Menu.height)) {
            game.setScreen(new Levels(game));
        }
        if (restart.Belong(x,y, Restart.height)) {
            if (level_number == 0)
                game.setScreen(new level_0(game));
            if (level_number == 1)
                game.setScreen(new level_1(game));
            if (level_number == 2)
                game.setScreen(new level_2(game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        game.spriteBatch.begin();
            background.draw(game);
            game.spriteBatch.draw(restart.texture, restart.Re_x, restart.Re_y);
            game.spriteBatch.draw(go_to_menu.texture, go_to_menu.Re_x, go_to_menu.Re_y);
        game.spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        restart.texture.dispose();
        go_to_menu.texture.dispose();
        for (PlayRightHexagon playRightHexagon: background.play)
            playRightHexagon.getTexture().dispose();
        for (BackGroundRightHexagon backGroundRightHexagon: background.templ)
            backGroundRightHexagon.texture.dispose();
    }
}
