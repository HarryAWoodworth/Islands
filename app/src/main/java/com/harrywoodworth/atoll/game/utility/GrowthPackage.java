package com.harrywoodworth.atoll.game.utility;

import com.harrywoodworth.atoll.game.island.ApexForestGrowth;
import com.harrywoodworth.atoll.game.island.ForestGrowth;
import com.harrywoodworth.atoll.game.island.RockGrowth;

/// Holds variables for determining how an Island changes in growth loops
/// Used in IslandGenerator
public class GrowthPackage {

    ForestGrowth forest_growth;
    ApexForestGrowth apex_forest_growth;
    RockGrowth rock_growth;

    public GrowthPackage() {
        forest_growth = new ForestGrowth();
        rock_growth = new RockGrowth();
        apex_forest_growth = new ApexForestGrowth();
    }

    public GrowthPackage(ForestGrowth _forest_growth, RockGrowth _rock_growth, ApexForestGrowth _apex_forest_growth) {
        forest_growth = _forest_growth;
        rock_growth = _rock_growth;
        apex_forest_growth = _apex_forest_growth;
    }


}
