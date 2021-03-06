package com.harrywoodworth.atoll.game.island.growths

import com.harrywoodworth.atoll.game.utility.DesignManager

/// Evolution rate is how many times it grows per factor, factor is how many times it evolves
class ForestGrowth (
    var forest_evolution_rate: Double = DesignManager.DEFAULT_FOREST_EVOLUTION_RATE,
    var growth_factor: Int = DesignManager.DEFAULT_FOREST_GROWTH_FACTOR,
    var seed_count : Int = DesignManager.DEFAULT_FOREST_SEED_COUNT
) {
    fun fEvolRateRand() : Double { return DesignManager.RANDOM_FOREST_EVOLUTION_RATE() }
    fun fGrowthFactRand() : Int { return DesignManager.RANDOM_FOREST_GROWTH_FACTOR() }
    fun fSeedCountRand() : Int { return DesignManager.RANDOM_FOREST_SEED_COUNT() }
}