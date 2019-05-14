package com.harrywoodworth.atoll.game.island.growths

import com.harrywoodworth.atoll.game.utility.DesignManager

class RockGrowth (
    var growth_factor: Int = DesignManager.DEFAULT_ROCK_GROWTH_FACTOR,
    var seed_count : Int = DesignManager.DEFAULT_ROCK_SEED_COUNT
) {
    fun rGrowthFactRand() : Int { return DesignManager.RANDOM_ROCK_GROWTH_FACTOR() }
    fun rSeedCountRand() : Int { return DesignManager.RANDOM_ROCK_SEED_COUNT() }
}