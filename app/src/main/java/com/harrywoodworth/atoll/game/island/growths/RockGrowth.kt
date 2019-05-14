package com.harrywoodworth.atoll.game.island.growths

import com.harrywoodworth.atoll.game.utility.DesignManager

data class RockGrowth (var forest_evolution_rate: Double = DesignManager.DEFAULT_ROCK_EVOLUTION_RATE,
                         var growth_factor: Int = DesignManager.DEFAULT_ROCK_GROWTH_FACTOR)