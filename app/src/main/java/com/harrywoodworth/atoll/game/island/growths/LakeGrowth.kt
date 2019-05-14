package com.harrywoodworth.atoll.game.island.growths

import com.harrywoodworth.atoll.game.utility.DesignManager

class LakeGrowth (
    var lake_evolution_rate: Double = DesignManager.DEFAULT_LAKE_EVOLUTION_RATE,
    var growth_factor: Int = DesignManager.DEFAULT_LAKE_GROWTH_FACTOR,
    var seed_count : Int = DesignManager.DEFAULT_LAKE_SEED_COUNT
) {
    fun lEvolRateRand() : Double { return DesignManager.RANDOM_LAKE_EVOLUTION_RATE() }
    fun lGrowthFactRand() : Int { return DesignManager.RANDOM_LAKE_GROWTH_FACTOR() }
    fun lSeedCountRand() : Int { return DesignManager.RANDOM_LAKE_SEED_COUNT() }
}