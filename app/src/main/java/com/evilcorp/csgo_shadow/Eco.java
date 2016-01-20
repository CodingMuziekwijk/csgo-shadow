package com.evilcorp.csgo_shadow;

public class Eco {

    private int count_money;
    private int terror_money;
    private boolean terror_faction;
    private int round_number = 1;

    public Eco(boolean terror_faction) {
        this.terror_faction = terror_faction;
    }

    public int getCount_money() {
        return count_money;
    }

    public int getRound_number() {
        return round_number;
    }

    public int getTerror_money() {
        return terror_money;
    }

    public String getFactionName() {
        String faction;
        if(terror_faction){
            faction = "Terrorists";
        }else {
            faction = "Counter-Terrorists";
        }

        return faction;
    }

    public boolean isTerror_faction() {
        return terror_faction;
    }
}
