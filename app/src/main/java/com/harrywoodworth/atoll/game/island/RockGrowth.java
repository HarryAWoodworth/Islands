package com.harrywoodworth.atoll.game.island;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class RockGrowth {

    public static double rock_evolution_rate = DesignManager.DEFAULT_ROCK_EVOLUTION_RATE;

    public RockGrowth(){}

    public RockGrowth(double _rock_evolution_rate) {
        rock_evolution_rate = _rock_evolution_rate;
    }

}
