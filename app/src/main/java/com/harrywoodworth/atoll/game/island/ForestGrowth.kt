package com.harrywoodworth.atoll.game.island

import com.harrywoodworth.atoll.game.utility.DesignManager

/// Evolution rate is how many times it grows per factor, factor is how many times it evolves
data class ForestGrowth (var forest_evolution_rate: Double = DesignManager.DEFAULT_FOREST_EVOLUTION_RATE,
                         var growth_factor: Int = DesignManager.DEFAULT_FOREST_GROWTH_FACTOR,
                         var seed_count : Int = DesignManager.DEFAUL_FOREST_SEED_COUNT)