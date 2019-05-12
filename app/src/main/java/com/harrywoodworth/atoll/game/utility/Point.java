package com.harrywoodworth.atoll.game.utility;

public class Point {

    public int col;
    public int row;

    public Point(int _col, int _row){
        col = _col;
        row = _row;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            Point p = (Point)obj;
            return p.col == this.col && p.row == this.row;
        }
        return false;
    }
}
