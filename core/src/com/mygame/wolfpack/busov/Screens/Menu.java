package com.mygame.wolfpack.busov.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Game;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public class Menu implements Screen {

    Game game;
    public Texture beg;

    public Menu(Game game){
        this.game = game;
//        beg = new Texture("beg.png");
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 223, 94, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
//        game.spriteBatch.draw(beg,0,0);
        game.spriteBatch.end();

        if(Gdx.input.isTouched()){
      //      game.setScreen(new Plays(game));
        }

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

    }
}
