package com.harrywoodworth.atoll.game.utility;

import com.harrywoodworth.atoll.game.island.CreationPoint;
import com.harrywoodworth.atoll.game.island.landTypes.Water;

/// Null point represents an error or no point for Point return functions
public class NullPoint extends CreationPoint {

    public NullPoint() {
        super(-1,-1, new Water());
    }

}
