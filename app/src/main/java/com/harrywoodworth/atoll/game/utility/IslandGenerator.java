package com.harrywoodworth.atoll.game.utility;

import android.util.Log;
import com.harrywoodworth.atoll.game.island.ApexForestGrowth;
import com.harrywoodworth.atoll.game.island.CreationPoint;
import com.harrywoodworth.atoll.game.island.ForestGrowth;
import com.harrywoodworth.atoll.game.island.Island;
import com.harrywoodworth.atoll.game.island.landTypes.*;

import java.lang.reflect.Array;
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

        /* GENERATE APEX FOREST */
        islandMat = genApexForest(islandMat, growth_package.apex_forest_growth);

        // Return a new Island from islandMat
        if(islandMat == null) {
            Log.e(TAG, "islandMat is null");
            return null;
        } else {
            Log.d(TAG, "Creating island of size " + islandMat.length + "x" + islandMat[0].length);
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
        CreationPoint cPoint, coverPoint;
        for(int i = 0; i < sandCoverage; i++) {

            // Throw error is sandTracker is empty
            if(sandTracker.isEmpty()) {
                Log.e(TAG, "Sand Tracker is empty on iteration " + i + " when generating sand");
                return null;
            }

            // Select random creation point from sandTracker with open points next to it
            cPoint = sandTracker.get(new Random().nextInt(sandTracker.size()));
            if(!(cPoint.type instanceof Sand)) {
                Log.e(TAG,"ERROR: Type " + cPoint.type.getName() + " has been added to Sand Tracker");
                return null;
            }
            Log.d(TAG,"Random point selected: " + cPoint.col + "," + cPoint.row);
            while(cPoint.emptyAdjacency(islandMat)) {
                sandTracker.remove(cPoint);
                cPoint = sandTracker.get(new Random().nextInt(sandTracker.size()));
                if(sandTracker.isEmpty()) {
                    Log.e(TAG, "Sand Tracker is empty while getting a new CPoint");
                    return null;
                }
            }

            // Get random point to cover in sand from cPoint
            coverPoint = cPoint.getRandomPoint(islandMat);
            Log.d(TAG, cPoint.col + "," + cPoint.row + " random Adjacent Point Selected: " + coverPoint.col + "," + coverPoint.row);
            // Error Check for NullPoint
            if(coverPoint instanceof NullPoint) {
                Log.e(TAG,"getRandomPoint() returned NullPoint in Sand Gen");
                return null;
            }
            // Fill in random slot
            islandMat[coverPoint.col][coverPoint.row] = new Sand();
            coverPoint.type = new Sand();
            Log.d(TAG,"Added sand at point [" + coverPoint.col + "][" + coverPoint.row + "]");
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
            if(!coverPoint.emptyAdjacency(islandMat)) {
                sandTracker.add(coverPoint);
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

        Forest f = new Forest();

        ArrayList<CreationPoint> forestTracker = new ArrayList<>();

        for(Point p : findSeedLocations(islandMat, f, forestGrowth.getSeed_count())) {
            islandMat[p.col][p.row] = new Forest();
            Log.d(TAG,"FOREST GEN: Added new Forest seed at " + p.col + "," + p.row);
            forestTracker.add(new CreationPoint(p.col, p.row, f));
        }

        // For every growth factor, evolve forests using evolution rates
        for(int i = 0; i < forestGrowth.getGrowth_factor(); i++) {

            // Break if no more grow-able forests
            if(forestTracker.isEmpty()) {
                Log.d(TAG, "FOREST GEN: Exited due to empty forest tracker on iteration " + i);
                break;
            }

            ArrayList<CreationPoint> temp = new ArrayList<>(forestTracker);
            for(int j = 0; j < temp.size(); j++) {

                CreationPoint p = temp.get(j);

                // Remove if empty adjacency list
                if(p.emptyAdjacency(islandMat)) {
                    forestTracker.remove(p);
                    Log.d(TAG,"FOREST GEN: Removed point " + p.col + "," + p.row + " due to empty adjacency list");
                }

                // Else get the point, create forest in islandMat, and add to list
                else if(new Random().nextDouble() < forestGrowth.getForest_evolution_rate()){

                    CreationPoint cP = p.getRandomPoint(islandMat);
                    if(cP instanceof NullPoint) {
                        Log.e(TAG,"FOREST GEN: getRandomPoint() returned NullPoint");
                        return null;
                    }

                    islandMat[cP.col][cP.row] = f;
                    Log.d(TAG, "FOREST GEN: On iteration " + i + " Forest created at " + cP.col + "," + cP.row);

                    cP = new CreationPoint(cP.col,cP.row,f);
                    if(!cP.emptyAdjacency(islandMat)) {
                        Log.d(TAG,"FOREST GEN: forest at " + cP.col + "," + cP.row + " added to forest tracker");
                        forestTracker.add(cP);
                    } else {
                        Log.d(TAG,"FOREST GEN: forest at " + cP.col + "," + cP.row + " NOT added to forest tracker");
                    }
                }

            }
        }

        return islandMat;
    }

    /// Generate Apex Forest
    private static IslandLandType[][] genApexForest(IslandLandType[][] islandMat, ApexForestGrowth apexForestGrowth) {

        // Get every forest
        ArrayList<CreationPoint> forests = new ArrayList<>();
        Forest f = new Forest();
        for(int col = 0; col < islandMat.length; col++) {
            for(int row = 0; row < islandMat[0].length; row++) {
                if(islandMat[col][row] instanceof Forest)
                    forests.add(new CreationPoint(col,row,f));
            }
        }

        /// Get factors from apexForestGrowth
        double forest_factor = apexForestGrowth.getApex_forest_f_factor();
        double apex_factor = apexForestGrowth.getApex_forest_apex_factor();
        double sand_factor = apexForestGrowth.getApex_forest_sand_factor();

        /// Loop through each forest and, using its neighbors, calculate if it evolves into an Apex Forest
        for(CreationPoint cp : forests) {

            double chance_to_evolve_to_apex = 0.0;
            cp.type = new ApexForest();
            ArrayList<CreationPoint> adjacent = cp.getAdjacencyList(islandMat);

            for(CreationPoint p : adjacent) {
                if(p.type instanceof Forest) {
                    chance_to_evolve_to_apex += forest_factor;
                } else if(p.type instanceof ApexForest) {
                    chance_to_evolve_to_apex += apex_factor;
                } else if(p.type instanceof Sand){
                    chance_to_evolve_to_apex += sand_factor;
                } else {
                    Log.e(TAG,"APEX_FOREST GEN: Non forest/sand in adjacency list of Apex Forest: " + p.type.getName());
                }
            }

            if(chance_to_evolve_to_apex >= new Random().nextDouble()) {
                islandMat[cp.col][cp.row] = new ApexForest();
                Log.d(TAG,"APEX_FOREST GENERATION: Created new Apex Forest at " + cp.col + "," + cp.row);
            }
        }

        return islandMat;

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
        Log.d(TAG,"Cropping island between [" + upperLeft.col + "][" + upperLeft.row + "] and [" + bottomRight.col + "][" + bottomRight.row + "]");
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

    /// Find an array of points of valid precedence from the island matrix
    private static ArrayList<Point> findSeedLocations(IslandLandType[][] islandMat, IslandLandType type, int seed_count) {
        ArrayList<Point> ret = new ArrayList<>();
        int col, row;
        for(int i = 0; i < seed_count; i++) {
            do {
                col = new Random().nextInt(islandMat.length);
                row = new Random().nextInt(islandMat[0].length);
            } while(!type.higherPrecedence(islandMat[col][row]));

            Point p = new Point(col,row);
            if(ret.contains(p))
                i--;
            else
                ret.add(p);
        }
        return ret;
    }

    private static final String TAG = "ISLAND_GENERATOR";

}
