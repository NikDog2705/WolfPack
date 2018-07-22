package com.mygame.wolfpack.busov.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Hexagons.GO_TO_Menu;
import com.mygame.wolfpack.busov.Hexagons.Music;
import com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon;
import com.mygame.wolfpack.busov.Hexagons.Restart;
import com.mygame.wolfpack.busov.Hexagons.ResumeGame;
import com.mygame.wolfpack.busov.Plays;
import com.mygame.wolfpack.busov.Screens.Level.level_0;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Level.level_2;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class Menu {

    public Texture texture;
    public boolean menu = false;
    public int x = PlayRightHexagon.height;
    public int y = (int)(PlayRightHexagon.Re_height * 5) - 7;
    private GO_TO_Menu go_to_menu;
    private Restart restart;
    private ResumeGame resumeGame;
    private Music m;
    public int Height = 355;
    public int Weight = 450;
    private Game game;
    private int belong_x, belong_y;
    private int level_number;

    public Menu(Game game, int level_number){
        this.level_number = level_number;
        this.game = game;
        texture = new Texture("Menu.png");
        go_to_menu = new GO_TO_Menu((int)( Weight) / 2 + x - GO_TO_Menu.height / 2, y + (int)(Height/2) + 40);
        restart = new Restart((int)( Weight) / 2 + x + Restart.height/2,y + (int) (Height/2) + 40);
        resumeGame = new ResumeGame((int)( Weight) / 2 + x, y + (int) (Height/2) + 66 - ResumeGame.height);
    }

    public void show(int x, int y){
        belong_x = x;
        belong_y = y;
        if (menu) {
            if (go_to_menu.Belong(x, y, GO_TO_Menu.height))
                game.setScreen(new Levels(game));
            if (resumeGame.Belong(x, y, ResumeGame.height))
                menu = false;
            if (restart.Belong(belong_x, belong_y, Restart.height)) {
                if (level_number == 0)
                    game.setScreen(new level_0(game));
                if (level_number == 1)
                    game.setScreen(new level_1(game));
            }
        }
    }

    public void show(SpriteBatch spriteBatch){
        if (menu) {
            if (go_to_menu.Belong(belong_x, belong_y, GO_TO_Menu.height))
                game.setScreen(new Levels(game));
            if (restart.Belong(belong_x, belong_y, Restart.height)) {
                if (level_number == 0)
                   game.setScreen(new level_0(game));
                if (level_number == 1)
                    game.setScreen(new level_1(game));
                if (level_number == 2)
                    game.setScreen(new level_2(game));
            }
            if (resumeGame.Belong(x, y, ResumeGame.height))
                menu = false;
            draw(spriteBatch);
        }
    }

    private void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(texture,x,y);
        spriteBatch.draw(restart.texture, restart.Re_x, restart.Re_y);
        spriteBatch.draw(go_to_menu.texture, go_to_menu.Re_x, go_to_menu.Re_y);
        spriteBatch.draw(resumeGame.texture, resumeGame.Re_x, resumeGame.Re_y);

    }

}
