package com.evilcorp.csgo_shadow;

/**
 * Created by Mark on 06/01/2016.
 */
public class gameMap {

    private String name;
    private int iconID;
    private int overViewID;

    public gameMap(String name, int iconID, int overViewID) {
        this.name = name;
        this.iconID = iconID;
        this.overViewID = overViewID;

    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return iconID;
    }

    public int getOverViewID() {
        return overViewID;
    }
}
