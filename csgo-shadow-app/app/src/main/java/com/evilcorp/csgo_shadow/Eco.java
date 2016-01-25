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

    public int getRound_number() {
        return roundNumber;
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

        updateRoundMoney(won);

        Log.i("countermoney", "ECO counter check = " + counterMoney);
        Log.i("terrormoney", "ECO terror check = " + terrorMoney);

        if(roundNumber == 1 || roundNumber == 16){
            ecoMessage = "Pistol Round";
        }else{
            ecoMessage = calculateEcoPercentage() + "% Chance on Eco";
        }

        if(eco){
            if(terrorFaction){
                Log.i("terrormoney", "ECO & terror");
                terrorMoney -= 4000;
            }else if (!isTerror_faction()){
                Log.i("terrormoney", "ECO & counter");
                counterMoney -= 4000;
            }
        }else if (roundNumber != 2 && roundNumber != 17){
            Log.i("terrormoney", "normalround");
            counterMoney -= 4000;
            terrorMoney -= 4000;
        }else{
            Log.i("terrormoney", "pistolround");
            counterMoney -= 500;
            terrorMoney -= 500;
        }

        checkMoneyCap();

        isHalfTime();
        isEndOfGame();

        Log.i("countermoney", "" + counterMoney);
        Log.i("terrormoney", "" + terrorMoney);

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
            winStreak = 0;
            loseStreak = 0;
            terrorFaction = !terrorFaction;
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

    private void updateRoundMoney(boolean won){
        if(won && terrorFaction){
            terrorMoney += 3500 + 300;
            counterMoney += 900 + (500 * winStreak);
        }else if(!won && terrorFaction){
            terrorMoney += 900 + (500 * loseStreak);
            counterMoney += 3500 + 300;
        }else if(!won && !isTerror_faction()){
            terrorMoney += 3500 + 300;
            counterMoney += 900 + (500 * loseStreak);
        }
        else if(won && !isTerror_faction()){
            terrorMoney += 900 + (500 * winStreak);
            counterMoney += 3500 + 300;
        }
    }

    public void checkMoneyCap(){
        if(counterMoney > 16000){
            counterMoney = 16000;
        }
        if(terrorMoney > 16000){
            terrorMoney = 16000;
        }
    }

    public int calculateEcoPercentage(){
        double factor = 100.00/2500.00;

        if(isTerror_faction()){
            if(counterMoney <= 2500){
                return 100;
            }else if(counterMoney >= 5000){
                return 0;
            }else{
                return  (int) Math.ceil(100 - (factor * (counterMoney - 2500)));
            }
        }else{
            if(terrorMoney <= 2500){
                return 100;
            }else if(terrorMoney >= 5000){
                return 0;
            }else{
                return  (int) Math.ceil(100 - (factor * (terrorMoney - 2500)));
            }
        }
    }
}
