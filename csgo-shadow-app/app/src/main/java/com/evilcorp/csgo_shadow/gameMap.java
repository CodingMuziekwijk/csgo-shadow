package com.evilcorp.csgo_shadow;

public class gameMap {

    //Global variables
    private String name;
    private int iconID;
    private int overViewID;
    private int shadowOverViewID;

    //Initialize conditions
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
