package com.harrywoodworth.atoll.game.utility;

/// Holds variables for determining how an Island changes in growth loops
/// Used in IslandGenerator
public class GrowthPackage {

    ForestGrowth forest_growth;
    RockGrowth rock_growth;

    public GrowthPackage() {
        forest_growth = new ForestGrowth();
        rock_growth = new RockGrowth();
    }

    public GrowthPackage(ForestGrowth _forest_growth, RockGrowth _rock_growth) {
        forest_growth = _forest_growth;
        rock_growth = _rock_growth;
    }


}
