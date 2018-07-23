package com.mygame.wolfpack.busov.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.wolfpack.busov.Backgrounds.Background;
import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.Hexagons.GO_TO_Menu;
import com.mygame.wolfpack.busov.Hexagons.MenuHexagon;
import com.mygame.wolfpack.busov.Hexagons.Music;
import com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon;
import com.mygame.wolfpack.busov.Hexagons.Restart;
import com.mygame.wolfpack.busov.Hexagons.ResumeGame;
import com.mygame.wolfpack.busov.Plays;
import com.mygame.wolfpack.busov.RightHexagon;
import com.mygame.wolfpack.busov.Screens.Level.level_0;
import com.mygame.wolfpack.busov.Screens.Level.level_1;
import com.mygame.wolfpack.busov.Screens.Level.level_2;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.07.2018.
 */

public class Menu {

    public boolean menu = false;
    public int Height = (int) (PlayRightHexagon.Re_height * 5 + PlayRightHexagon.height);
    public int Weight = (int) Game.ScreenWidht;
    public int x;
    public int y;
    private GO_TO_Menu go_to_menu;
    private Restart restart;
    private ResumeGame resumeGame;
    private Music m;
    private Game game;
    private int belong_x, belong_y;
    private int level_number;
    private Background background;
    private ArrayList<MenuHexagon> menuHexagons;

    public Menu(Game game, int level_number, Background background){
        this.level_number = level_number;
        this.game = game;
        this.background = background;
        x = background.play[75].Re_x;
        y = background.play[75].Re_y;
        go_to_menu = new GO_TO_Menu(( Weight) / 2 + x - GO_TO_Menu.height / 2, y + Height/2 + 40);
        restart = new Restart( Weight / 2 + x + Restart.height/2,y + Height/2 + 40);
        resumeGame = new ResumeGame(Weight / 2 + x, y + Height/2 + 66 - ResumeGame.height);
        menuHexagons = new ArrayList<MenuHexagon>();
        MenuInit();
    }

    public void show(int x, int y){
        belong_x = x;
        belong_y = y;
        if (menu) {
            MenuInit();
            if (go_to_menu.Belong(x, y, GO_TO_Menu.height)) {
                menuHexagons.removeAll(menuHexagons);
                game.setScreen(new Levels(game));
            }
            if (resumeGame.Belong(x, y, ResumeGame.height)) {
                menuHexagons.removeAll(menuHexagons);
                menu = false;
            }
            if (restart.Belong(belong_x, belong_y, Restart.height)) {
                menuHexagons.removeAll(menuHexagons);
                if (level_number == 0)
                    game.setScreen(new level_0(game));
                if (level_number == 1)
                    game.setScreen(new level_1(game));
                if (level_number == 2)
                    game.setScreen(new level_2(game));
            }
        }
    }

    public void MenuInit(){
        for (int i = 38; i < 83; ++i){
            menuHexagons.add(new MenuHexagon(background.play[i].x, background.play[i].y));
        }
    }

    public void show(SpriteBatch spriteBatch){
        if (menu) {
            MenuInit();
            if (go_to_menu.Belong(x, y, GO_TO_Menu.height)) {
                menuHexagons.removeAll(menuHexagons);
                game.setScreen(new Levels(game));
            }
            if (resumeGame.Belong(x, y, ResumeGame.height)) {
                menuHexagons.removeAll(menuHexagons);
                menu = false;
            }
            if (restart.Belong(belong_x, belong_y, Restart.height)) {
                menuHexagons.removeAll(menuHexagons);
                if (level_number == 0)
                    game.setScreen(new level_0(game));
                if (level_number == 1)
                    game.setScreen(new level_1(game));
                if (level_number == 2)
                    game.setScreen(new level_2(game));
            }
            draw(spriteBatch);
        }
    }

    private void draw(SpriteBatch spriteBatch){
        for (MenuHexagon menuHexagon: menuHexagons)
            spriteBatch.draw(menuHexagon.texture, menuHexagon.Re_x, menuHexagon.Re_y);
        spriteBatch.draw(restart.texture, restart.Re_x, restart.Re_y);
        spriteBatch.draw(go_to_menu.texture, go_to_menu.Re_x, go_to_menu.Re_y);
        spriteBatch.draw(resumeGame.texture, resumeGame.Re_x, resumeGame.Re_y);

    }

    private void Clean(){
        menuHexagons.removeAll(menuHexagons);
    }

}
