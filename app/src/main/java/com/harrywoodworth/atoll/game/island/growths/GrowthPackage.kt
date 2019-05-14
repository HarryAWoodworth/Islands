package com.harrywoodworth.atoll.game.island.growths

/// Holds variables for determining how an Island changes in growth loops
/// Used in IslandGenerator
data class GrowthPackage(
    var brushGrowth: BrushGrowth = BrushGrowth(),
    var lakeGrowth: LakeGrowth = LakeGrowth(),
    var forest_growth: ForestGrowth = ForestGrowth(),
    var apex_forest_growth: ApexForestGrowth = ApexForestGrowth()
)
