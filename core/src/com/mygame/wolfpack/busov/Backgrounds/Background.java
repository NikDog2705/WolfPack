package com.mygame.wolfpack.busov.Backgrounds;

import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public class Background {
    ArrayList <Integer> forest;
    ArrayList <Integer> mountain;
    public BackGroundRightHexagon templ [];
    public PlayRightHexagon play[];
    public static int null_field_y;

    public Background(){
        templ = new BackGroundRightHexagon[33];
        play = new PlayRightHexagon[120];
        TemplateInitialization();
        PlayInitialization();
    }

    public Background(ArrayList<Integer> forest){
        this.forest = forest;
        templ = new BackGroundRightHexagon[33];
        play = new PlayRightHexagon[120];
        TemplateInitialization();
        PlayInitialization();
        Forest();
    }

    public Background(ArrayList<Integer> forest, ArrayList<Integer> mountain){
        this.forest = forest;
        this.mountain = mountain;
        templ = new BackGroundRightHexagon[33];
        play = new PlayRightHexagon[120];
        TemplateInitialization();
        PlayInitialization();
        Mount();
        Forest();
    }

    private void Mount(){
        for (int i = 0; i < mountain.size(); ++i){
            templ[mountain.get(i)].setTexture(RightHexagon.mountain);
        }
    }

    private void Forest(){
        for (int i = 0; i < forest.size(); ++i){
            play[forest.get(i)].setTexture(RightHexagon.forest);
        }
    }

    private void TemplateInitialization(){
        null_field_y = (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight - com.mygame.wolfpack.busov.Game.ScreenHeight);
        for (int i = 0; i < 33; ++i){
            if (i >= 0 && i < 9){
                templ[i] = new BackGroundRightHexagon(BackGroundRightHexagon.height * i,
                        (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight + BackGroundRightHexagon.height /4), RightHexagon.water);
                continue;
            }
            if (i >= 9 && i < 17){
              templ[i] = new BackGroundRightHexagon((int)com.mygame.wolfpack.busov.Game.ScreenWidht,
                      (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight -  BackGroundRightHexagon.height / 2
                              - BackGroundRightHexagon.Re_height * (i - 9) * 2 - BackGroundRightHexagon.Re_height),
                      RightHexagon.water);
                continue;
            }
            if (i >= 17 && i <25 ){
                templ[i] = new BackGroundRightHexagon((int) com.mygame.wolfpack.busov.Game.ScreenWidht
                        - BackGroundRightHexagon.height * (i - 17) - BackGroundRightHexagon.height/2,
                        null_field_y - BackGroundRightHexagon.height/4, RightHexagon.water);
                continue;
            }
            if (i >= 25 && i < 33) {
                templ[i] = new BackGroundRightHexagon(0,
                        (int)(null_field_y + BackGroundRightHexagon.Re_height * (i - 25) * 2 + BackGroundRightHexagon.height/2),
                        RightHexagon.water);
                continue;
            }
        }
    }


    private void PlayCreat(){
        null_field_y = (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight - com.mygame.wolfpack.busov.Game.ScreenHeight);
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                play[i * 15 + j] = new PlayRightHexagon((int)(PlayRightHexagon.height / 2 + PlayRightHexagon.height * j),
                        (int)(Game.Re_ScreenHeight - PlayRightHexagon.height/2 - PlayRightHexagon.Re_height * 2* i),
                        RightHexagon.grass);
            }
            for (int j = 8; j < 15; ++j){
                play[i*15 + j] = new PlayRightHexagon((int)(PlayRightHexagon.height + PlayRightHexagon.height *(j - 8)),
                        (int)(Game.Re_ScreenHeight - PlayRightHexagon.height/2 - PlayRightHexagon.Re_height
                                - PlayRightHexagon.Re_height * 2 * i),RightHexagon.grass);
            }
        }
    }

    private void PlayFacet(){
        int k = 0;
        for (int i = 0; i < 16; ++i){
            play[k].left_facet = true;
            if (i % 2 == 0){
                play[k].setTexture(RightHexagon.left_grass);
                play[k].facet = true;
                play[k].big_facet = true;
                k += 8;
            }
            else {
                play[k].facet = true;
                k += 7;
            }
        }
        k = 7;
        for (int i = 0; i < 16; ++i){
            if (i % 2 == 0){
                play[k].setTexture(RightHexagon.right_grass);
                play[k].facet = true;
                play[k].big_facet = true;
                k += 7;
            }
            else {
                play[k].facet = true;
                k += 8;
            }
        }
    }

    private void PlayNormalNeighbors(){
        for (int i = 0; i < 120; ++i){
            if (play[i].facet)
                continue;
            if (i - 1 > -1)
                play[i].neighbors.add(i - 1);
            if (i + 1 < 120)
                play[i].neighbors.add(i + 1);
            if (i - 8 > -1)
                play[i].neighbors.add(i - 8);
            if (i + 8 < 120)
                play[i].neighbors.add(i + 8);
            if (i - 7 > -1)
                play[i].neighbors.add(i - 7);
            if (i + 7 < 120)
                play[i].neighbors.add(i + 7);
        }
    }

    private void PlayFacetNeighbors(){
        for (int i = 0; i < 120; ++i){
            if (!play[i].facet)
                continue;
            if (i - 1 > -1 && !play[i].left_facet)
                play[i].neighbors.add(i - 1);
            if (i + 1 < 120&& play[i].left_facet)
                play[i].neighbors.add(i + 1);
            if (i - 8 > -1 && (!play[i].big_facet || !play[i].left_facet))
                play[i].neighbors.add(i - 8);
            if (i + 8 < 120 && (!play[i].big_facet || play[i].left_facet))
                play[i].neighbors.add(i + 8);
            if (i - 7 > -1 && (!play[i].big_facet || play[i].left_facet))
                play[i].neighbors.add(i - 7);
            if (i + 7 < 120 && (!play[i].big_facet || !play[i].left_facet))
                play[i].neighbors.add(i + 7);
        }
    }

    private void PlayInitialization(){
       PlayCreat();
       PlayFacet();
       PlayNormalNeighbors();
       PlayFacetNeighbors();
    }


    public void draw(com.mygame.wolfpack.busov.Game game){
        for (BackGroundRightHexagon backGroundHexagon : templ)
            game.spriteBatch.draw(backGroundHexagon.texture, backGroundHexagon.x, backGroundHexagon.y);
        for (PlayRightHexagon playRightHexagon : play)
            game.spriteBatch.draw(playRightHexagon.texture, playRightHexagon.x, playRightHexagon.y);
        int i = 0;
    }
}
