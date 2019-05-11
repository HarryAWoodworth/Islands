package com.harrywoodworth.atoll.game.island;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class ForestGrowth {

    // How efficient is the Forest at growing? Determines chance for forest spread
    public static double forest_evolution_rate = DesignManager.DEFAULT_FOREST_EVOLUTION_RATE;

    // How long has the forest been growing? Determines number of loops of forest growth
    public static int growth_factor = DesignManager.DEFAULT_FOREST_GROWTH_FACTOR;

    public ForestGrowth(){}

    public ForestGrowth(double _forest_evolution_rate, int _growth_factor) {
        if(_forest_evolution_rate != 0.0)
            forest_evolution_rate = _forest_evolution_rate;
        if(_growth_factor != 0)
            growth_factor = _growth_factor;
    }



}
