package com.harrywoodworth.atoll.game.utility;

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

    /// Forest
    public static final double DEFAULT_FOREST_EVOLUTION_RATE = 0.6;
    public static final int DEFAULT_FOREST_GROWTH_FACTOR = 10;
    public static final int DEFAULT_FOREST_SEED_COUNT = 3;


    /// Apex Forest
    public static final double DEFAULT_APEX_FOREST_F_FACTOR = 0.05;
    public static final double DEFAULT_APEX_FOREST_APEX_FACTOR = 0.015;
    public static final double DEFAULT_APEX_FOREST_SAND_FACTOR = -0.5;


    /// Rock
    public static final double DEFAULT_ROCK_EVOLUTION_RATE = 0.0;
    public static final int DEFAULT_ROCK_GROWTH_FACTOR = 0;




}
