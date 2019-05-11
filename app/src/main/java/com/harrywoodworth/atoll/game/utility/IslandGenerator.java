package com.harrywoodworth.atoll.game.utility;

import android.util.Log;
import com.harrywoodworth.atoll.game.island.CreationPoint;
import com.harrywoodworth.atoll.game.island.ForestGrowth;
import com.harrywoodworth.atoll.game.island.Island;
import com.harrywoodworth.atoll.game.island.landTypes.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IslandGenerator {

    /// Create an Island within a square of size island_size
    /// Repeat a growth loop on the island growth_factor times
    /// Create the Island Code String and create an Island with it
    /// Return the Island / null if error occurred
    public static Island generateIsland(int island_size, GrowthPackage growth_package) {

        if(island_size < DesignManager.ISLAND_SIZE_MIN) {
            Log.e(TAG, "Island size too small: " + island_size + " Min is " + DesignManager.ISLAND_SIZE_MIN);
            return null;
        } else if (island_size > DesignManager.ISLAND_SIZE_MAX) {
            Log.e(TAG, "Island size too large: " + island_size + " Max is " + DesignManager.ISLAND_SIZE_MAX);
            return null;
        }

        // Create the island matrix and fill with spaces
        IslandLandType[][] islandMat = createMatrix(island_size);

        /* GENERATE SAND */
        islandMat = genSand(islandMat, island_size);

        /* GENERATE FOREST */
        islandMat = genForest(islandMat, growth_package.forest_growth);

        // Return a new Island from islandMat
        if(islandMat == null) {
            Log.e(TAG, "islandMat is null");
            return null;
        } else {
            return new Island(matrixToString(islandMat), islandMat.length, islandMat[0].length);
        }
    }

    /// Generate Sand
    private static IslandLandType[][] genSand(IslandLandType[][] islandMat, int island_size) {

        // Generate random sand coverage
        int sandCoverage = (int)(island_size * ThreadLocalRandom.current().nextDouble(DesignManager.ISLAND_CREATION_SAND_RANGE_LOW,DesignManager.ISLAND_CREATION_SAND_RANGE_HIGH));
        Log.d(TAG,"Sand Coverage " + sandCoverage);

        // Add sand seed at center
        ArrayList<CreationPoint> sandTracker = new ArrayList<>();
        islandMat[island_size/2][island_size/2] = new Sand();
        sandTracker.add(new CreationPoint(island_size/2,island_size/2, new Sand()));

        Point upperLeft = new Point(island_size/2,island_size/2);
        Point bottomRight = new Point(island_size/2,island_size/2);

        // Loop sand generation until coverage is met
        CreationPoint cPoint, tentativeCPoint;
        Point coverPoint;
        for(int i = 0; i < sandCoverage; i++) {
            // Throw error is sandTracker is empty
            if(sandTracker.isEmpty()) {
                Log.e(TAG, "Sand Tracker is empty on iteration " + i + " when generating sand");
                return null;
            }

            // Select random creation point from sandTracker
            Random r = new Random();
            cPoint = sandTracker.get(r.nextInt(sandTracker.size()));
            while(cPoint.emptyAdjacency(islandMat)) {
                sandTracker.remove(cPoint);
                cPoint = sandTracker.get(r.nextInt(sandTracker.size()));
                if(sandTracker.isEmpty()) {
                    Log.e(TAG, "Sand Tracker is empty while getting a new CPoint");
                    return null;
                }

            }

            // Get random point to cover in sand from cPoint
            coverPoint = cPoint.getRandomPoint(islandMat);
            // Error Check for NullPoint
            if(coverPoint instanceof NullPoint) {
                Log.e(TAG,"getRandomPoint() returned NullPoint");
                return null;
            }
            // Fill in random slot
            islandMat[coverPoint.col][coverPoint.row] = new Sand();
            // Update points
            if(coverPoint.col < upperLeft.col)
                upperLeft.col = coverPoint.col;
            if(coverPoint.row < upperLeft.row)
                upperLeft.row = coverPoint.row;
            if(coverPoint.col > bottomRight.col)
                bottomRight.col = coverPoint.col;
            if(coverPoint.row > bottomRight.row)
                bottomRight.row = coverPoint.row;
            // Add to sandTracker
            tentativeCPoint = new CreationPoint(coverPoint.col,coverPoint.row, new Sand());
            if(!tentativeCPoint.emptyAdjacency(islandMat)) {
                sandTracker.add(tentativeCPoint);
            }
            // if selected has no higher precedence adjacent then remove from list
            if(cPoint.emptyAdjacency(islandMat)) {
                sandTracker.remove(cPoint);
            }
        }

        // Crop islandMat
        return cropToIsland(islandMat, upperLeft, bottomRight);

    }

    /// Generate Forest
    private static IslandLandType[][] genForest(IslandLandType[][] islandMat, ForestGrowth forestGrowth) {
        return null;
    }

    /// Create, initialize, and return the island matrix
    private static IslandLandType[][] createMatrix(int island_size) {
        IslandLandType[][] islandMat = new IslandLandType[island_size][island_size];
        for(int col = 0; col < island_size; col++) {
            for(int row = 0; row < island_size; row++) {
                islandMat[col][row] = new Water();
            }
        }
        Log.d(TAG,"Initialized Island Matrix of " + islandMat.length + "x" + islandMat[0].length);
        return islandMat;
    }

    /// Trim islandMat to a cropping of the island
    private static IslandLandType[][] cropToIsland(IslandLandType[][] mat, Point upperLeft, Point bottomRight) {
        IslandLandType[][] croppedMat = new IslandLandType[bottomRight.col-upperLeft.col][bottomRight.row-upperLeft.row];
        for(int col = 0; col < croppedMat.length; col++) {
            for(int row = 0; row < croppedMat[0].length; row++) {
                croppedMat[col][row] = mat[upperLeft.col+col][upperLeft.row+row];
            }
        }
        return croppedMat;
    }

    /// Convert the matrix to a string and return
    private static String matrixToString(IslandLandType[][] mat) {
        StringBuilder builder = new StringBuilder();
        for(int row = 0; row < mat[0].length; row++) {
            for(int col = 0; col < mat.length; col++) {
                builder.append(mat[col][row].toString());
            }
            builder.append("\n");
        }
        Log.d(TAG,"Created Island with String length " + builder.length());
        return builder.toString();
    }

    private static final String TAG = "ISLAND_GENERATOR";

}
