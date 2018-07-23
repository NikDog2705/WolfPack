package com.mygame.wolfpack.busov.Backgrounds;

import com.mygame.wolfpack.busov.Game;
import com.mygame.wolfpack.busov.RightHexagon;

import java.util.ArrayList;

/**
 * Created by Master_Igor on 22.06.2018.
 */

public class Background {
    private ArrayList <Integer> forest;
    private ArrayList <Integer> mountain;
    public com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon templ [];
    public com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon play[];
    private static int null_field_y;

    public Background(ArrayList<Integer> forest, ArrayList<Integer> mountain){
        this.forest = forest;
        this.mountain = mountain;
        templ = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon[33];
        play = new com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon[120];
        TemplateInitialization();
        PlayInitialization();
        Forest();
    }

    public Background(){
        forest = new ArrayList<Integer>();
        mountain = new ArrayList<Integer>();
        templ = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon[33];
        play = new com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon[120];
        TemplateInitialization();
        PlayInitialization();
    }

    private void Mount(){
        for (int i = 0; i < mountain.size(); ++i){
            play[mountain.get(i)].setTexture(RightHexagon.mountain);
        }
    }

    private void Forest(){
        for (int i = 0; i < forest.size(); ++i){
            play[forest.get(i)].setTexture(RightHexagon.forest);
        }
    }

    private void TemplateInitialization(){
        null_field_y = (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight - Game.GameScreenHeight);
        for (int i = 0; i < 33; ++i){
            if (i >= 0 && i < 9){
                templ[i] = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon(com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height * i,
                        (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight + com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height /4), RightHexagon.water);
                continue;
            }
            if (i >= 9 && i < 17){
              templ[i] = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon((int)com.mygame.wolfpack.busov.Game.ScreenWidht,
                      (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight -  com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height / 2
                              - com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.Re_height * (i - 9) * 2 - com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.Re_height),
                      RightHexagon.water);
                continue;
            }
            if (i >= 17 && i <25 ){
                templ[i] = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon((int) com.mygame.wolfpack.busov.Game.ScreenWidht
                        - com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height * (i - 17) - com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height/2,
                        null_field_y - com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height/4, RightHexagon.water);
                continue;
            }
            if (i >= 25 && i < 33) {
                templ[i] = new com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon(0,
                        (int)(null_field_y + com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.Re_height * (i - 25) * 2 + com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon.height/2),
                        RightHexagon.water);
            }
        }
    }


    private void PlayCreat(){
        null_field_y = (int)(com.mygame.wolfpack.busov.Game.Re_ScreenHeight - com.mygame.wolfpack.busov.Game.ScreenHeight);
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                play[i * 15 + j] = new com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon((com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height / 2 + com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height * j),
                        (int)(Game.Re_ScreenHeight - com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height/2 - com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.Re_height * 2* i),
                        RightHexagon.grass);
            }
            for (int j = 8; j < 15; ++j){
                play[i*15 + j] = new com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon((com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height + com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height *(j - 8)),
                        (int)(Game.Re_ScreenHeight - com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.height/2 - com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.Re_height
                                - com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon.Re_height * 2 * i),RightHexagon.grass);
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
                if ( !(play[i-1].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 1);
            if (i + 1 < 120)
                if ( !(play[i+1].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 1);
            if (i - 8 > -1)
                if ( !(play[i-8].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 8);
            if (i + 8 < 120)
                if ( !(play[i+8].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 8);
            if (i - 7 > -1)
                if ( !(play[i-7].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 7);
            if (i + 7 < 120)
                if ( !(play[i+7].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 7);
        }
    }

    private void PlayFacetNeighbors(){
        for (int i = 0; i < 120; ++i){
            if (!play[i].facet)
                continue;
            if (i - 1 > -1 && !play[i].left_facet)
                if ( !(play[i-1].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 1);
            if (i + 1 < 120&& play[i].left_facet)
                if ( !(play[i+1].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 1);
            if (i - 8 > -1 && (!play[i].big_facet || !play[i].left_facet))
                if ( !(play[i-8].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 8);
            if (i + 8 < 120 && (!play[i].big_facet || play[i].left_facet))
                if ( !(play[i+8].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 8);
            if (i - 7 > -1 && (!play[i].big_facet || play[i].left_facet))
                if ( !(play[i-7].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i - 7);
            if (i + 7 < 120 && (!play[i].big_facet || !play[i].left_facet))
                if ( !(play[i+7].getIntTexture() == RightHexagon.mountain))
                    play[i].neighbors.add(i + 7);
        }
    }

    private void PlayInitialization(){
       PlayCreat();
       PlayFacet();
       Mount();
       PlayNormalNeighbors();
       PlayFacetNeighbors();
    }


    public void draw(com.mygame.wolfpack.busov.Game game){
        for (com.mygame.wolfpack.busov.Hexagons.BackGroundRightHexagon backGroundHexagon : templ)
            game.spriteBatch.draw(backGroundHexagon.texture, backGroundHexagon.Re_x, backGroundHexagon.Re_y);
        for (com.mygame.wolfpack.busov.Hexagons.PlayRightHexagon playRightHexagon : play)
            game.spriteBatch.draw(playRightHexagon.getTexture(), playRightHexagon.Re_x, playRightHexagon.Re_y);
    }
}
