package com.evilcorp.csgo_shadow;

/**
 * Created by Mark on 06/01/2016.
 */
public class gameMap {

    private String name;
    private int iconID;
    private int overViewID;
    private int shadowOverViewID;

    public gameMap(String name, int iconID, int overViewID, int shadowOverViewID) {
        this.name = name;
        this.iconID = iconID;
        this.overViewID = overViewID;
        this.shadowOverViewID = shadowOverViewID;

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

    public int getShadowOverViewID(){
        return shadowOverViewID;
    }
}
