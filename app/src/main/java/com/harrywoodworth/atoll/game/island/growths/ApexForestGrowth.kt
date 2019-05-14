package com.harrywoodworth.atoll.game.island.growths

import com.harrywoodworth.atoll.game.utility.DesignManager

data class ApexForestGrowth (
    var apex_forest_f_factor: Double = DesignManager.DEFAULT_APEX_FOREST_F_FACTOR,
    var apex_forest_apex_factor: Double = DesignManager.DEFAULT_APEX_FOREST_APEX_FACTOR,
    var apex_forest_sand_factor: Double = DesignManager.DEFAULT_APEX_FOREST_SAND_FACTOR
)