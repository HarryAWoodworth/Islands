package com.harrywoodworth.atoll.game.island;

import android.util.Log;
import com.harrywoodworth.atoll.game.island.landTypes.IslandLandType;
import com.harrywoodworth.atoll.game.utility.NullPoint;
import com.harrywoodworth.atoll.game.utility.Point;

import java.util.ArrayList;
import java.util.Random;

public class CreationPoint extends Point {

    private ArrayList<Point> adjacencyList;
    private IslandLandType type;

    public CreationPoint(int col, int row, IslandLandType _type) {
        super(col,row);
        adjacencyList = new ArrayList<>();
        type = _type;
    }

    /// Return a random point from AdjacencyList after updating the list
    public Point getRandomPoint(IslandLandType[][] mat) {
        findCoverableAdjacent(mat);

        // Return NullPoint if empty adjacency list
        if(adjacencyList.isEmpty()) {
            Log.e(TAG, "Adjacency List is Empty CPoint [" + col + "][" + row + "]");
            return new NullPoint();
        }
        // Return element if size is 1
        if (adjacencyList.size() == 1) {
            return adjacencyList.get(0);
        }

        // Otherwise return a random Point in list
        Random r = new Random();
        return adjacencyList.get(r.nextInt(adjacencyList.size()));
    }

    /// Recalculate coverable points adjacent to this point
    private void findCoverableAdjacent(IslandLandType[][] mat) {
        adjacencyList.clear();
        IslandLandType t;
        // Left
        if(col > 0) {
            t = mat[col-1][row];
            Log.d(TAG,"mat[" + (col-1) + "][" + row + "] is type " + t.getName());
            if(type.higherPrecedence(t))
                adjacencyList.add(new Point(col - 1, row));
        }
        // Right
        if(col < mat.length-1) {
            t = mat[col+1][row];
            Log.d(TAG,"mat[" + (col+1) + "][" + row + "] is type " + t.getName());
            if(type.higherPrecedence(t))
                adjacencyList.add(new Point(col+1,row));
        }
        // Down
        if(row < mat.length-1) {
            t = mat[col][row+1];
            Log.d(TAG,"mat[" + col + "][" + (row+1) + "] is type " + t.getName());
            if(type.higherPrecedence(t))
                adjacencyList.add(new Point(col,row+1));
        }
        // Up
        if(row > 0) {
            t = mat[col][row-1];
            Log.d(TAG,"mat[" + col + "][" + (row-1) + "] is type " + t.getName());
            if(type.higherPrecedence(t))
                adjacencyList.add(new Point(col,row-1));
        }
    }

    public boolean emptyAdjacency(IslandLandType[][] mat) {
        findCoverableAdjacent(mat);
        return adjacencyList.isEmpty();
    }

    private static final String TAG = "CREATION_POINT";

}
