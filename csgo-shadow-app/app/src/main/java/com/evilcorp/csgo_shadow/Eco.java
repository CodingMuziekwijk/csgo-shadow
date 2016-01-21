package com.evilcorp.csgo_shadow;

import android.util.Log;

public class Eco {

    private int counterMoney = 800;
    private int terrorMoney = 800;
    private boolean terrorFaction;
    private int roundNumber = 1;
    private int winStreak = 0;
    private int loseStreak = 0;
    private String ecoMessage;
    public Eco(boolean terror_faction) {
        this.terrorFaction = terror_faction;
    }

    public int getCount_money() {
        return counterMoney;
    }

    public int getRound_number() {
        return roundNumber;
    }

    public int getTerror_money() {
        return terrorMoney;
    }

    public String getEcoMessage(){
        return ecoMessage;
    }

    public String getFactionName() {
        String faction;
        if(terrorFaction){
            faction = "Terrorists";
        }else {
            faction = "Counter-Terrorists";
        }

        return faction;
    }

    public boolean isTerror_faction() {
        return terrorFaction;
    }

    public void endOfRound(Boolean won, Boolean eco){
        roundNumber += 1;

        updateStreak(won);

        if(won && terrorFaction){
            terrorMoney += 3500 + 300;
            counterMoney += 900 + (500 * winStreak);
        }else if(!won && terrorFaction){
            terrorMoney += 900 + (500 * loseStreak);
            counterMoney += 3500 + 300;
        }else if(!won && !terrorFaction){
            terrorMoney += 3500 + 300;
            counterMoney += 900 + (500 * loseStreak);
        }
        else if(won && !terrorFaction){
            terrorMoney += 900 + (500 * winStreak);
            counterMoney += 3500 + 300;
        }
        Log.i("countermoney", "" + counterMoney);
        Log.i("terrormoney", "" + terrorMoney);

        ecoMessage = checkForEco();


        if(eco){
            if(terrorFaction){
                counterMoney += 4000;
            }else{
                terrorMoney += 4000;
            }
        }

        counterMoney -= 4000;
        terrorMoney -= 4000;

        if(counterMoney > 16000){
            counterMoney = 16000;
        }
        if(terrorMoney > 16000){
            terrorMoney = 16000;
        }

        isHalfTime();
        isEndOfGame();

        return;
    }

    private void resetEco(){
        counterMoney = 800;
        terrorMoney = 800;
        roundNumber = 1;
        winStreak = 0;
        loseStreak = 0;
    }

    private void isEndOfGame(){
        if(roundNumber == 31){
            resetEco();
        }
    }

    private void isHalfTime(){
        if(roundNumber == 16){
            counterMoney = 800;
            terrorMoney = 800;

            if(terrorFaction){
                terrorFaction = false;
            }else{
                terrorFaction = true;
            }
        }
    }

    private void updateStreak(boolean won){
        if(won){
            winStreak += 1;
            loseStreak = 0;
        }else{
            loseStreak += 1;
            winStreak = 0;
        }

        if(loseStreak > 5){
            loseStreak = 5;
        }
        if(winStreak > 5){
            winStreak = 5;
        }
    }

    private String checkForEco(){
        String ecoMessage;
        if((counterMoney <= 4000) && (terrorMoney <= 4000)){
            Log.i("terrormoney", "Counter & Terror Eco!");
            ecoMessage = "Both will Eco";
        }else if(counterMoney <= 4000){
            Log.i("terrormoney", "Counter Eco!");
            ecoMessage = "Counter Eco!";
        }else if(terrorMoney <= 4000){
            Log.i("terrormoney", "Terror Eco!");
            ecoMessage = "Terror Eco!";
        }else{
            ecoMessage = "";
        }

        return ecoMessage;
    }
}
