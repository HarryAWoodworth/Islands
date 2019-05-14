package com.harrywoodworth.atoll.game.utility;

import java.util.Random;

public final class DesignManager {

    // Island
    static final int ISLAND_SIZE_MIN = 100;
    static final int ISLAND_SIZE_MAX = 1500;
    static final double ISLAND_CREATION_SAND_RANGE_LOW = 0.1;
    static final double ISLAND_CREATION_SAND_RANGE_HIGH = 0.4;


    /// Char representation
    public static final char CHAR_WATER = ' ';
    public static final char CHAR_SAND = ':';
    public static final char CHAR_BRUSH = '.';
    public static final char CHAR_ROCK = '#';
    public static final char CHAR_FOREST = 'o';
    public static final char CHAR_APEX_FOREST = '0';


    /// Lakes
    public static final double DEFAULT_LAKE_EVOLUTION_RATE = 0.6;
    public static final int DEFAULT_LAKE_GROWTH_FACTOR = 10;
    public static final int DEFAULT_LAKE_SEED_COUNT = 3;
    public static final double RANDOM_LAKE_EVOLUTION_RATE(){ return getRandomDouble(0.4,0.8); }
    public static final int RANDOM_LAKE_GROWTH_FACTOR(){ return getRandomInt(5,13); }
    public static final int RANDOM_LAKE_SEED_COUNT(){ return getRandomInt(0,4); }

    /// Forest

    public static final double DEFAULT_FOREST_EVOLUTION_RATE = 0.6;
    public static final int DEFAULT_FOREST_GROWTH_FACTOR = 10;
    public static final int DEFAULT_FOREST_SEED_COUNT = 3;
    public static double RANDOM_FOREST_EVOLUTION_RATE(){ return getRandomDouble(0.5,0.8); }
    public static int RANDOM_FOREST_GROWTH_FACTOR() { return getRandomInt(8,13); }
    public static int RANDOM_FOREST_SEED_COUNT() { return getRandomInt(0,8); }


    /// Apex Forest
    public static final double DEFAULT_APEX_FOREST_F_FACTOR = 0.05;
    public static final double DEFAULT_APEX_FOREST_APEX_FACTOR = 0.015;
    public static final double DEFAULT_APEX_FOREST_SAND_FACTOR = -0.5;


    /// Utility Methods
    private static int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
    private static double getRandomDouble(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }


}
