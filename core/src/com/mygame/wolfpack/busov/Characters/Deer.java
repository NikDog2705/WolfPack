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
    public Background background;
    public ArrayList<Wolfs> wolf;
    public ArrayList<DeerPack> deerPacks;
    public boolean step = true;

    public Deer(int game_cell, Background background, ArrayList<Wolfs> wolf, ArrayList<DeerPack> deerPacks) {
        texture = new Texture("Deer.png");
        this.wolf = wolf;
        this.deerPacks = deerPacks;
        this.background = background;
        this.game_cell = game_cell;
        x = background.play[game_cell].Re_x - height/2;
        y = background.play[game_cell].Re_y - height/2;
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
                if (!background.play[deerPack.path.get(deerPack.path.size() - 3)].red)
                    game_cell = deerPack.path.get(deerPack.path.size() - 3);
                else
                    game_cell = deerPack.path.get(deerPack.path.size() - 2);
            } else
                game_cell = deerPack.path.get(deerPack.path.size() - 2);
        }
        x = background.play[game_cell].Re_x - height/2;
        y = background.play[game_cell].Re_y - height/2;
    }

    private void Red(){
        boolean[] b = new boolean[120];
        for (int i = 0; i < wolf.size(); ++i) {
            background.play[wolf.get(i).game_cell].red = true;
            for (DeerPack deerPack : deerPacks) {
                if (wolf.get(i).game_cell == deerPack.game_cell)
                    background.play[wolf.get(i).game_cell].red = false;
                for (int j = 0; j < background.play[wolf.get(i).game_cell].neighbors.size(); ++j) {
                    if (!b[background.play[wolf.get(i).game_cell].neighbors.get(j)])
                        background.play[background.play[wolf.get(i).game_cell].neighbors.get(j)].red = true;
                    if (background.play[wolf.get(i).game_cell].neighbors.get(j) == deerPack.game_cell) {
                        background.play[background.play[wolf.get(i).game_cell].neighbors.get(j)].red = false;
                        b[background.play[wolf.get(i).game_cell].neighbors.get(j)] = true;
                    }
                }
            }
        }
    }

    public void update() {
        if (!step) {
            Red();
            Step();
            Cleaner();
        }
       step = true;
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
        for (int v = deerPack.game_cell; v != -1; v = p[v]){
            deerPack.path.add(v);
        }
    }

    private void Cleaner(){
        for (DeerPack deerPack: deerPacks) {
            deerPack.path.clear();
            deerPack.d = 0;
        }
    }

}
