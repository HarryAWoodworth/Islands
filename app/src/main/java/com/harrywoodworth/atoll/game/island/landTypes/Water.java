package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class Water implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_WATER;
    }

    @Override
    public boolean higherPrecedence(IslandLandType i) {
        return i instanceof Sand;
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
