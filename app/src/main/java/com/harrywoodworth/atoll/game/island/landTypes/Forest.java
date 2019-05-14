package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class Forest implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_FOREST;
    }

    @Override
    public boolean higherPrecedence(IslandLandType t) {
        return     t instanceof Rock
                || t instanceof Sand
                || t instanceof Brush;
    }

    @Override
    public String getName() {
        return "Forest";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
