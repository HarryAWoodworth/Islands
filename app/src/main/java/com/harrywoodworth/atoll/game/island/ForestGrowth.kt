package com.harrywoodworth.atoll.game.island

import com.harrywoodworth.atoll.game.utility.DesignManager

data class ForestGrowth (var forest_evolution_rate: Double = DesignManager.DEFAULT_FOREST_EVOLUTION_RATE,
                         var growth_factor: Int = DesignManager.DEFAULT_FOREST_GROWTH_FACTOR)