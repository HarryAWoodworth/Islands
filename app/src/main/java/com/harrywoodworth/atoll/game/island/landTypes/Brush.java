package com.harrywoodworth.atoll.game.island.landTypes;

import com.harrywoodworth.atoll.game.utility.DesignManager;

public class Brush implements IslandLandType {

    @Override
    public char getChar() {
        return DesignManager.CHAR_BRUSH;
    }

    @Override
    public boolean higherPrecedence(IslandLandType t) {
        return  t instanceof Sand;
    }

    @Override
    public String getName() {
        return "Brush";
    }

    @Override
    public String toString() {
        return "" + getChar();
    }

}
