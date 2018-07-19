package com.mygame.wolfpack.busov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Backgrounds.PlayRightHexagon;
import com.mygame.wolfpack.busov.Characters.Deer;
import com.mygame.wolfpack.busov.Characters.DeerPack;
import com.mygame.wolfpack.busov.Characters.Wolfs;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public abstract class Plays extends InputAdapter{
    protected Game game;
    protected Background background;
    protected ArrayList<Integer> forest; // Add forest cells
    protected ArrayList<Integer> mountain; // Add mountains cells
    protected ArrayList<Deer> deer;
    protected ArrayList<DeerPack> deerPack;
    protected ArrayList<Wolfs> wolfs;
    private int play_button;
    private int wolf_count;
    protected boolean create = true;
    private boolean wolf_click = false;
    private Wolfs wolf_clicked;

    public Plays(final Game game, int wolf_count, final int play_button){
        this.game = game;
        this.play_button = play_button;
        this.wolf_count = wolf_count;
        forest = new ArrayList<Integer>();
        mountain = new ArrayList<Integer>();
        deer = new ArrayList<Deer>();
        deerPack = new ArrayList<DeerPack>();
        wolfs = new ArrayList<Wolfs>();
        AddBackground();
        background = new Background(forest, mountain);
        AddAnimals();

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int x,int y,int pointer,int button) {
                y =(int) ((Gdx.graphics.getHeight() - y) * ((Game.Re_ScreenHeight / Gdx.graphics.getHeight())));
                x = (int) (x * (Game.Re_ScreenWidht / Gdx.graphics.getWidth()));
                if (create) {
                   Create(x,y);
                }
                return true;
            }
        });

    }

    private void Create(int x, int y){
        if (background.play[play_button].Belong(x, y, PlayRightHexagon.height)) {
            create = false;
            background.play[play_button].setTexture(RightHexagon.grass);
        }
        if (!wolf_click) {
            for (int forest : forest) {
                if (background.play[forest].Belong(x, y, PlayRightHexagon.height)
                        && wolf_count > 0) {
                    wolfs.add(new Wolfs(forest, background));
                    --wolf_count;
                    background.play[forest].wolf_here = true;
                }
            }
            for (Wolfs wolf : wolfs) {
                if (background.play[wolf.game_cell].Belong(x, y, PlayRightHexagon.height)) {
                    wolf_click = true;
                    wolf_clicked = wolf;
                }
            }
        }
        else{
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

    protected abstract void AddBackground();
    protected abstract void AddAnimals();
    protected abstract void update();

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
}
