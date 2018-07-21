package com.mygame.wolfpack.busov.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygame.wolfpack.busov.Backgrounds.Background;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by Master_Igor on 27.06.2018.
 */

public class Deer {

    public Texture texture;
    public static int height = 50;
    public int x, y;
    public int game_cell;
    private Background background;
    private ArrayList<Wolfs> wolf;
    private ArrayList<DeerPack> deerPacks;
    public boolean step = true;
    private boolean red_cell = false;
    private boolean have_step = false;

    public Deer(int game_cell, Background background, ArrayList<Wolfs> wolf, ArrayList<DeerPack> deerPacks) {
        texture = new Texture("Deer.png");
        this.wolf = wolf;
        this.deerPacks = deerPacks;
        this.background = background;
        this.game_cell = game_cell;
        x = background.play[game_cell].x - height/2;
        y = background.play[game_cell].y - height/2;
    }

    private void Step(){
        DeerPack deerPack = new DeerPack();
        int min = 666;
        for (DeerPack deers: deerPacks) {
            Bfs(deers);
        }
        for (DeerPack deers: deerPacks)
            if (deers.length < min) {
                min = deers.length;
                deerPack = deers;
            }
        if (deerPack.path.size() > 1) {
            if (deerPack.path.size() > 2) {
                if (!background.play[deerPack.path.get(deerPack.path.size() - 3)].red || deerPack.path.size() - 3 == 0)
                    game_cell = deerPack.path.get(deerPack.path.size() - 3);
                else
                    game_cell = deerPack.path.get(deerPack.path.size() - 2);
            }
            else
                game_cell = deerPack.path.get(deerPack.path.size() - 2);
        }
        if (!have_step && red_cell){
            for (int i = 0; i < background.play[game_cell].neighbors.size(); ++i){
                if (!background.play[background.play[game_cell].neighbors.get(i)].red) {
                    game_cell = background.play[game_cell].neighbors.get(i);
                    have_step = true;
                    break;
                }
            }
        }
        if (!have_step && red_cell){
            for (int i = 0; i < background.play[game_cell].neighbors.size(); ++i)
                for (int j = 0; j < background.play[background.play[game_cell].neighbors.get(i)].neighbors.size(); ++j)
                    if (!background.play[background.play[background.play[game_cell].neighbors.get(i)].neighbors.get(j)].red) {
                        game_cell = background.play[background.play[game_cell].neighbors.get(i)].neighbors.get(j);
                        break;
                    }
        }
        x = background.play[game_cell].x - height/2;
        y = background.play[game_cell].y - height/2;
    }

    private void Red(){
        for (int i = 0; i < wolf.size(); ++i) {
            background.play[wolf.get(i).game_cell].red = true;
            for (int j = 0; j < background.play[wolf.get(i).game_cell].neighbors.size(); ++j) {
                if (background.play[wolf.get(i).game_cell].neighbors.get(j) != game_cell)
                    background.play[background.play[wolf.get(i).game_cell].neighbors.get(j)].red = true;
                else
                    red_cell = true;
            }
            for (DeerPack deerPack : deerPacks)
                for (int j = 0; j < background.play[wolf.get(i).game_cell].neighbors.size(); ++j) {
                if (background.play[deerPack.game_cell] == background.play[wolf.get(i).game_cell]) {
                    background.play[background.play[wolf.get(i).game_cell].neighbors.get(j)].red = false;
                }
            }
        }
    }

    private void UnRed(){
        for (int i = 0; i < wolf.size(); ++i) {
            background.play[wolf.get(i).game_cell].red = false;
            for (int j = 0; j < background.play[wolf.get(i).game_cell].neighbors.size(); ++j) {
                background.play[background.play[wolf.get(i).game_cell].neighbors.get(j)].red = false;
            }
        }
    }

    public void update() {
        if (!step) {
            Red();
            Step();
            Cleaner();
            step = true;
        }
    }

    private void Bfs (DeerPack deerPack) {
        ArrayDeque<Integer> q = new ArrayDeque <Integer>();
        q.push(game_cell);
        boolean[] used = new boolean [120];
        boolean b = false;
        int[] p = new int[120];
        int[] d = new int[120];
        int[] length = new int[120];
        d[game_cell] = 0;
        p[game_cell] = -1;
        used[game_cell] = true;

         while (!q.isEmpty() && !b) {
            int v = q.poll();
            for (int i = 0; i < background.play[v].neighbors.size(); ++i) {
                int to = background.play[v].neighbors.get(i);
                if (!used[to]) {
                    used[to] = true;
                    p[to] = v;
                    d[to] = d[v] + 1;
                    length[to] = length[v] + 1;
                    if (d[to] % 2 == 0 && !background.play[to].red)
                        --length[to];
                    if (!(background.play[to].red && background.play[v].red))
                        q.offer(to);
                }
                if (to == deerPack.game_cell) {
                    b = true;
                    break;
                }
            }
         }
         deerPack.d = d[deerPack.game_cell];
         deerPack.length = length[deerPack.game_cell];
         if (b) {
             for (int v = deerPack.game_cell; v != -1; v = p[v]) {
                 deerPack.path.add(v);
             }
             have_step = true;
         }
    }

    private void Cleaner(){
        for (DeerPack deerPack: deerPacks) {
            while (deerPack.path.size() != 0)
                deerPack.path.remove(0);
            deerPack.d = 0;
        }
        UnRed();
        have_step = false;
        red_cell = false;
    }

}
