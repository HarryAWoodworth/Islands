package com.harrywoodworth.atoll.game.utility;

public final class DesignManager {

    public static final int ISLAND_SIZE_MIN = 100;
    public static final int ISLAND_SIZE_MAX = 1500;


    public static final char CHAR_ROCK = '#';
    public static final char CHAR_FOREST = 'f';
    public static final char CHAR_SAND = 's';
    public static final char CHAR_WATER = ' ';

    public static final double DEFAULT_FOREST_EVOLUTION_RATE = 0.6; // Between 0.0 and 1.0
    public static final int DEFAULT_FOREST_GROWTH_FACTOR = 10;
    public static final int DEFAULT_FOREST_SEED_COUNT = 3;

    public static final double DEFAULT_ROCK_EVOLUTION_RATE = 0.0;
    public static final int DEFAULT_ROCK_GROWTH_FACTOR = 0;

    public static final double ISLAND_CREATION_SAND_RANGE_LOW = 0.1;
    public static final double ISLAND_CREATION_SAND_RANGE_HIGH = 0.4;


}
