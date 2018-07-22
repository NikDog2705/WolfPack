package com.mygame.wolfpack.busov.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Hexagons.LevelRighHexagon;
import com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon;
import com.mygame.wolfpack.busov.Screens.Level.level_0;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Level.level_2;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public class Levels extends InputAdapter implements Screen {

    private LevelRighHexagon[] levels;
    private Game game;
    private Texture texture;

    public Levels(Game game){
        levels = new LevelRighHexagon[3];
        this.game = game;
        LevelInit();
        texture = new Texture("level_bg.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int x,int y,int pointer,int button) {
                y =(int) ((Gdx.graphics.getHeight() - y) * ((Game.Re_ScreenHeight / Gdx.graphics.getHeight())));
                x = (int) (x * (Game.Re_ScreenWidht / Gdx.graphics.getWidth()));
                update(x,y);
                return true;
            }
        });
    }

    private void LevelInit(){
        levels[0] = new LevelRighHexagon((int)Game.ScreenWidht / 2, (int) ( Game.Re_ScreenHeight - LevelRighHexagon.height), 0);
        levels[1] = new LevelRighHexagon((int)Game.ScreenWidht / 2, (int) (Game.Re_ScreenHeight -  LevelRighHexagon.height * 2), 1);
        levels[2] = new LevelRighHexagon((int)Game.ScreenWidht / 2, (int) (Game.Re_ScreenHeight -  LevelRighHexagon.height * 3), 2);
    }

    private void update(int x, int y){
        if (levels[1].Belong(x,y,LevelRighHexagon.height))
            game.setScreen(new level_1(game));
        if (levels[0].Belong(x,y,LevelRighHexagon.height))
            game.setScreen(new level_0(game));
        if (levels[2].Belong(x,y,LevelRighHexagon.height))
            game.setScreen(new level_2(game));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        game.spriteBatch.begin();
        game.spriteBatch.draw(texture, 0, 0);
            for (LevelRighHexagon levelRighHexagon : levels) {
                game.spriteBatch.draw(levelRighHexagon.texture, levelRighHexagon.Re_x, levelRighHexagon.Re_y);
                game.bitmapFont.draw(game.spriteBatch, Integer.toString(levelRighHexagon.number),
                        levelRighHexagon.Re_x + LevelRighHexagon.height/2, levelRighHexagon.Re_y + LevelRighHexagon.height/2);
            }
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
        game.spriteBatch.dispose();
        game.bitmapFont.dispose();
        texture.dispose();
        for (LevelRighHexagon levelRighHexagon: levels)
            levelRighHexagon.texture.dispose();
    }
}
