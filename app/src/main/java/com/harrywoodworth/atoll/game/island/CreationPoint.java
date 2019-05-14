package com.harrywoodworth.atoll.game.island;

import android.util.Log;
import com.harrywoodworth.atoll.game.island.landTypes.IslandLandType;
import com.harrywoodworth.atoll.game.utility.NullPoint;
import com.harrywoodworth.atoll.game.utility.Point;

import java.util.ArrayList;
import java.util.Random;

public class CreationPoint extends Point {

    private ArrayList<CreationPoint> adjacencyList;
    public IslandLandType type;

    public CreationPoint(int col, int row, IslandLandType _type) {
        super(col,row);
        adjacencyList = new ArrayList<>();
        type = _type;
    }

    /// Return a random point from AdjacencyList after updating the list
    public CreationPoint getRandomPoint(IslandLandType[][] mat) {
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
            //Log.d(TAG,"mat[" + (col-1) + "][" + row + "] is type " + t.getName());
            if(type.higherPrecedence(t)) {
                Log.d(TAG,type.getName() + " is higher precedence than " + t.getName());
                adjacencyList.add(new CreationPoint(col - 1, row, t));
            } else {
                Log.d(TAG,type.getName() + " is NOT higher precedence than " + t.getName() + " at " + col + "," + row);
            }
        }
        // Right
        if(col < mat.length-1) {
            t = mat[col+1][row];
            //Log.d(TAG,"mat[" + (col+1) + "][" + row + "] is type " + t.getName());
            if(type.higherPrecedence(t)) {
                Log.d(TAG,type.getName() + " is higher precedence than " + t.getName());
                adjacencyList.add(new CreationPoint(col + 1, row, t));
            } else {
                Log.d(TAG,type.getName() + " is NOT higher precedence than " + t.getName() + " at " + col + "," + row);
            }
        }
        // Down
        if(row < mat[0].length-1) {
            t = mat[col][row+1];
            //Log.d(TAG,"mat[" + col + "][" + (row+1) + "] is type " + t.getName());
            if(type.higherPrecedence(t)) {
                Log.d(TAG,type.getName() + " is higher precedence than " + t.getName());
                adjacencyList.add(new CreationPoint(col, row + 1, t));
            } else {
                Log.d(TAG,type.getName() + " is NOT higher precedence than " + t.getName() + " at " + col + "," + row);
            }
        }
        // Up
        if(row > 0) {
            t = mat[col][row-1];
            //Log.d(TAG,"mat[" + col + "][" + (row-1) + "] is type " + t.getName());
            if(type.higherPrecedence(t)) {
                Log.d(TAG,type.getName() + " is higher precedence than " + t.getName());
                adjacencyList.add(new CreationPoint(col, row - 1, t));
            } else {
                Log.d(TAG,type.getName() + " is NOT higher precedence than " + t.getName() + " at " + col + "," + row);
            }
        }
    }

    public boolean emptyAdjacency(IslandLandType[][] mat) {
        findCoverableAdjacent(mat);
        return adjacencyList.isEmpty();
    }

    public ArrayList<CreationPoint> getAdjacencyList(IslandLandType[][] mat) {
        findCoverableAdjacent(mat);
        return adjacencyList;
    }

    private static final String TAG = "CREATION_POINT";

}
