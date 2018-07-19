package com.mygame.wolfpack.busov;

import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;

/**
 * Created by Master_Igor on 04.07.2018.
 */

public class RightHexagon {
    public static int grass = 0, left_grass = 1, right_grass = 2, forest = 3, water = 0, mountain = 1,play = 4;

    public int x,y;

    private double B(double k, int x, int y){
        return y - k * x;
    }


    private boolean left_plane(double k, int x, int y, int x1, int y1){
        double b = B(k, x1, y1);
        double x2 = (y - b) / k;
        if (x2 >= x)
            return true;
        else
            return false;
    }

    private boolean top_triangle(int x, int y, int x1,int y1, double height){
        double k = Math.tan(Math.toRadians(30));
        if (y >= y1 - height && y <= y1)
            if (!left_plane(k,x,y,x1,y1) && left_plane(-k,x,y,x1,y1))
                return true;
        return false;
    }

    private boolean down_triangle(int x, int y, int x1,int y1, double height){
        double k = Math.tan(Math.PI/6);
        if (y <= y1 + height && y >= y1)
            if (!left_plane(-k,x,y,x1,y1) && left_plane(k,x,y,x1,y1))
                return true;
        return false;
    }

    private boolean tetragon (int x, int y, double x1, double y1, double height, double weight){
        if (y >= y1 && y <= y1 + height)
            if (x >= x1 && x <= x1 + weight)
                return true;
        return false;
    }

     public boolean Belong(int x1,int y1, int height){
        if (top_triangle(x1,y1,x, y + height / 2, height / 4)
                || down_triangle(x1,y1,x, y - height / 2, height / 4)
                || tetragon(x1,y1,x - height / 2,y - height / 4, height/2, height))
            return true;
        return false;
    }
    
}
