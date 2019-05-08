package com.harrywoodworth.atoll.game.island;

public class Island {

    private String islandCode;
    public int width;
    public int height;

    public Island(String _islandCode, int _width, int _height) {
        islandCode = _islandCode;
        width = _width;
        height = _height;
    }

    /// Return islandCode char[] without newlines
    public char[] toCharArray() {
        String modified = islandCode.replaceAll("\n","");
        return modified.toCharArray();
    }

    @Override
    public String toString() {
        return islandCode;
    }


}
