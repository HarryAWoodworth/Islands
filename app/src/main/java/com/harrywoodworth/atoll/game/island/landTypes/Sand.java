package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class Sand implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_SAND;
    }

    @Override
    public boolean higherPrecedence(IslandLandType t) {
        return t instanceof Water;
    }

    @Override
    public String getName() {
        return "Sand";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
