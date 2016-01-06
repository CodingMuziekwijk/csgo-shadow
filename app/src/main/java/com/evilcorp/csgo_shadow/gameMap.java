package com.evilcorp.csgo_shadow;

/**
 * Created by Mark on 06/01/2016.
 */
public class gameMap {

    private String name;
    private int iconID;

    public gameMap(String name, int iconID) {
        this.name = name;
        this.iconID = iconID;

    }

    public int getIconID() {
        return iconID;
    }

    public String getName() {
        return name;
    }
}
