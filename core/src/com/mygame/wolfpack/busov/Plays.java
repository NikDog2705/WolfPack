package com.mygame.wolfpack.busov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygame.wolfpack.busov.Backgrounds.BackGroundRightHexagon;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Backgrounds.PlayRightHexagon;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public abstract class Plays extends InputAdapter implements Screen {
    protected Game game;
    protected Background background;
    protected ArrayList<Integer> forest; // Add forest cells
    protected ArrayList<Integer> mountain; // Add mountains cells
    protected ArrayList<Integer> deer;
    protected ArrayList<Integer> deerPack;
    private ArrayList<Deer> deers;
    private ArrayList<DeerPack> deerPacks;
    private ArrayList<Wolfs> wolfs;
    private int play_button = 10;
    private int wolf_count;
    private boolean create = true;
    private boolean wolf_click = false;
    private Wolfs wolf_clicked;
    private boolean step_deer = false;
    private int count_wolf_step;
    private int const_count_wolf;

    public Plays(final Game game, final int wolf_count){
        this.game = game;
        const_count_wolf = wolf_count;
        count_wolf_step = wolf_count;
        this.wolf_count = wolf_count;
        forest = new ArrayList<Integer>();
        mountain = new ArrayList<Integer>();
        deer = new ArrayList<Integer>();
        deerPack = new ArrayList<Integer>();
        deers = new ArrayList<Deer>();
        deerPacks = new ArrayList<DeerPack>();
        wolfs = new ArrayList<Wolfs>();
        AddBackground();
        background = new Background(forest, mountain);
        background.templ[play_button].setTexture(RightHexagon.play);
        AddAnimals();
        AnimalsInitialization();

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int x,int y,int pointer,int button) {
                y =(int) ((Gdx.graphics.getHeight() - y) * ((Game.Re_ScreenHeight / Gdx.graphics.getHeight())));
                x = (int) (x * (Game.Re_ScreenWidht / Gdx.graphics.getWidth()));
                if (create)
                   Create(x,y);
                else{
                    if (!step_deer ) {
                        WolfUpdate(x, y);
                        if(background.templ[play_button].Belong(x,y,PlayRightHexagon.height))
                            step_deer = true;
                        if (count_wolf_step >= const_count_wolf) {
                            step_deer = true;
                            count_wolf_step = 0;
                        }
                    }
                }
                return true;
            }
        });

    }

    private void WolfUpdate(int x, int y){
        if (!wolf_click)
            for (Wolfs wolf : wolfs){
                if (background.play[wolf.game_cell].Belong(x,y,PlayRightHexagon.height)
                        && !wolf.step) {
                    wolf_click = true;
                    wolf.step = true;
                    wolf_clicked = wolf;
                }
            }
        else{
            if (background.play[wolf_clicked.game_cell].Belong(x,y,PlayRightHexagon.height))
                wolf_click = false;
            for (int playRightHexagon : background.play[wolf_clicked.game_cell].neighbors)
                if (background.play[playRightHexagon].Belong(x,y,PlayRightHexagon.height)
                        && !background.play[playRightHexagon].wolf_here && !background.play[playRightHexagon].deerPack_here) {
                    background.play[wolf_clicked.game_cell].wolf_here = false;
                    wolfs.remove(wolf_clicked);
                    wolfs.add(new Wolfs(playRightHexagon, background));
                    wolf_click = false;
                    ++count_wolf_step;
                }
        }
    }

    private void AnimalsInitialization(){
        for (int deerPack : deerPack) {
            deerPacks.add(new DeerPack(deerPack, background));
            background.play[deerPacks.get(deerPacks.size() - 1).game_cell].deerPack_here = true;
        }
        for (int deer : deer)
            deers.add(new Deer(deer,background,wolfs,deerPacks));
    }

    private void Create(int x, int y){
        boolean b = false;
        if (background.templ[play_button].Belong(x, y, BackGroundRightHexagon.height) && wolf_count <= 0) {
            create = false;
        }
        if (!wolf_click) {
            for (int forest : forest) {
                if (background.play[forest].Belong(x, y, PlayRightHexagon.height)
                        && wolf_count > 0 && !background.play[forest].wolf_here){
                    wolfs.add(new Wolfs(forest, background));
                    --wolf_count;
                    background.play[forest].wolf_here = true;
                    b = true;
                }
            }
            if (!b)
                for (Wolfs wolf : wolfs) {
                    if (background.play[wolf.game_cell].Belong(x, y, PlayRightHexagon.height)) {
                        wolf_click = true;
                        wolf_clicked = wolf;
                    }
                }
        }
        else{
            if (background.play[wolf_clicked.game_cell].Belong(x,y,PlayRightHexagon.height))
                wolf_click = false;
            for (int forest : forest)
                if (background.play[forest].Belong(x,y,PlayRightHexagon.height)
                        && !background.play[forest].wolf_here){
                    background.play[wolf_clicked.game_cell].wolf_here = false;
                    wolfs.remove(wolf_clicked);
                    wolfs.add(new Wolfs(forest, background));
                    wolf_click = false;
                }
        }
    }

    protected void Deerupdate() {
        if (step_deer) {
            for (Deer deers : deers) {
                deers.update();
                deers.step = false;
            }
            for (Wolfs wolf: wolfs)
                wolf.step = false;
            count_wolf_step = wolf_count;
            step_deer = false;
        }
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        game.spriteBatch.begin();
        background.draw(game);
        for (DeerPack deerPack : deerPacks)
            game.spriteBatch.draw(deerPack.texture, deerPack.x, deerPack.y);
        for (Deer deer : deers)
            game.spriteBatch.draw(deer.texture, deer.x, deer.y);
        for (Wolfs wolf : wolfs)
            game.spriteBatch.draw(wolf.texture, wolf.x, wolf.y);
        game.spriteBatch.end();

        if (!create)
            Deerupdate();
    }

    protected abstract void AddBackground();
    protected abstract void AddAnimals();

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

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
