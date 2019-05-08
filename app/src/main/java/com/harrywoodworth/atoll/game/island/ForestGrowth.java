package com.harrywoodworth.atoll.game.island;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class ForestGrowth {

    public static double forest_evolution_rate = DesignManager.DEFAULT_FOREST_EVOLUTION_RATE;

    public ForestGrowth(){}

    public ForestGrowth(double _forest_evolution_rate) {
        forest_evolution_rate = _forest_evolution_rate;
    }

}
