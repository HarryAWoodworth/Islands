package com.harrywoodworth.atoll.game.island.landTypes;

public interface IslandLandType {

    char getChar();
    boolean higherPrecedence(IslandLandType i);
    String toString();
    String getName();

}
