package com.holdings.siloaman.dbzo_leveller;

/**
 * Created by Shanta on 2017-04-24.
 */

public class PowerScaling {

    public int PowerScaling (int totalKiDenominator, String playerRace, int boostModifier){

        int tier = 0;
        double increase = 0;
        float powerLevelPercentage;



        if (totalKiDenominator >= 100 && totalKiDenominator < 1000)
            tier = 2;
        if (totalKiDenominator >= 1000 && totalKiDenominator < 10000)
            tier = 3;
        if (totalKiDenominator >= 10000 && totalKiDenominator < 100000)
            tier = 4;
        if (totalKiDenominator >= 100000 && totalKiDenominator < 1000000)
            tier = 5;
        if (totalKiDenominator >= 1000000 && totalKiDenominator < 10000000)
            tier = 6;
        if (totalKiDenominator >= 10000000)
            tier = 7;

        if(playerRace == "Namekian"){
            if(tier == 2)
                increase = 20;
            if(tier == 3)
                increase = 100;
            if(tier == 4)
                increase = 500;
            if(tier == 5)
                increase = 1000;
            if(tier == 6)
                increase = 10000;
            if(tier == 7)
                increase = 10000;
        }
        if(playerRace == "Saiyan"){
            if(tier == 2)
                increase = 5;
            if(tier == 3)
                increase = 25;
            if(tier == 4)
                increase = 125;
            if(tier == 5)
                increase = 250;
            if(tier == 6)
                increase = 2500;
            if(tier == 7)
                increase = 2500;
        }
        if(playerRace == "Heran"){

            // POWER LEVEL FACTORING
            powerLevelPercentage = boostModifier - 95;              // 100 - 95 = 5
            powerLevelPercentage /= 200;                            // 5/200 = 0.025
            powerLevelPercentage *= 100;                            // 0.025 * 100 = 2.5

            if(tier == 2)
                increase = powerLevelPercentage;
            if(tier == 3)
                increase = powerLevelPercentage * 5;
            if(tier == 4)
                increase = powerLevelPercentage * 25;
            if(tier == 5)
                increase = powerLevelPercentage * 50;
            if(tier == 6)
                increase = powerLevelPercentage * 500;
            if(tier == 7)
                increase = powerLevelPercentage * 500;
        }

        //increase *= heran/namekianGreen;
        Math.round(increase);

        return (int)increase;
    }


}
/*
Tier 2 (100 - 999)
        [ 2% ]
        50 Training Sessions To Reach 1,000 Total Ki
        +20 ki / session


        Tier 3 (1,000 - 9,999)
        [ 1% ]
        100 Training Sessions to Reach 10,000 Total Ki
        +100 ki / session


        Tier 4 (10,000 - 99,999)
        [ 0.5% ]
        200 Training Sessions to Reach 100,000 Total Ki
        +500 ki / session


        Tier 5 (100,000 - 999,999)
        [ 0.1% ]
        1000 Training Sessions to Reach 1,000,000 Total Ki
        +1,000 ki / session


        Tier 6 (1,000,000 - 9,999,999)
        [ 0.1% ]
        1000 Training Sessions to Reach 10,000,000 Total Ki
        +10,000 ki / session
*/
