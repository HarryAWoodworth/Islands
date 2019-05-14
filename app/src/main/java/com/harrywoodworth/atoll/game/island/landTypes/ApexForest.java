package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class ApexForest implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_APEX_FOREST;
    }

    @Override
    public boolean higherPrecedence(IslandLandType t) {
        return      t instanceof Forest
                ||  t instanceof ApexForest
                ||  t instanceof Sand;
    }

    @Override
    public String getName() {
        return "Apex Forest";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
