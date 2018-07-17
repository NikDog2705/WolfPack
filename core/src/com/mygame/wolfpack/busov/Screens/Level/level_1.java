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

import java.util.ArrayList;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class level_1 extends Plays implements Screen {

    public level_1(Game game) {
        super(game);

    }

    @Override
    protected void AddBackground() {
        forest.add(39);
        forest.add(41);
        forest.add(40);
        mountain.add(31);
    }

    @Override
    protected void AddAnimals() {
        wolfs.add(new Wolfs(55,background));
        wolfs.add(new Wolfs(58,background));
        wolfs.add(new Wolfs(34,background));
        deerPack.add(new DeerPack(41,background));
        deerPack.add(new DeerPack(119,background));
        deer.add(new Deer(72,background,wolfs,deerPack));
    }

    @Override
    protected void update() {
        for (Deer deers : deer)
            deers.update();
        if (Gdx.input.justTouched())
            for (Deer deers : deer) {
                deers.step = false;
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
            for (DeerPack deerPack : deerPack)
                game.spriteBatch.draw(deerPack.texture, deerPack.x, deerPack.y);
            for (Deer deer : deer)
                game.spriteBatch.draw(deer.texture, deer.x, deer.y);
            for (Wolfs wolf : wolfs)
                game.spriteBatch.draw(wolf.texture, wolf.x, wolf.y);
        game.spriteBatch.end();

        update();
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
