package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class Rock implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_ROCK;
    }

    @Override
    public boolean higherPrecedence(IslandLandType t) {
        return     t instanceof Water
                || t instanceof Sand
                || t instanceof Forest
                || t instanceof Brush;
    }

    @Override
    public String getName() {
        return "Rock";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
