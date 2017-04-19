package com.holdings.siloaman.dbzo_leveller;

import android.app.Activity;

import android.content.Context;
import android.graphics.Color;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;


public class MainActivity extends Activity {

    // BUTTON MODIFIERS
    boolean CKModifier = false;
    boolean DKModifier = false;
    boolean PLModifier = false;
    boolean BPModifier = false;

    int startStopModifier = 1;

    // TIMERS
    int yellowTimer = 0;
    float namekianGreenTimer = 0;
    float namekianYellowTimer = 0;
    int saiyanTimer = 0;
    float heranGreenTimer = 0;
    float heranYellowTimer = 0;

    static boolean init = false;
    Avatar avatar;

    // CHARACTER STATS
    String playerName = "PLAYER";
    String playerRace = "HUMAN";
    static float TKNRF = 0;
    static int TKDRI = 0;
    static float CKRF = 0;
    static float DKRF = 0;
    static int PLRI = 0;
    static int BPRI = 0;
    static float GEPRF = 0;
    static float YEPRF = 0;
    static int GETRI = 0;
    static int YETRI = 0;
    static int YTRI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BASE STAT TEXTVIEW ASSIGNMENT
        final TextView CKValue = (TextView)findViewById(R.id.CKValueTextView);
        final TextView DKValue = (TextView)findViewById(R.id.DKValueTextView);
        final TextView PLValue = (TextView)findViewById(R.id.PLValueTextView);
        final TextView BPValue = (TextView)findViewById(R.id.BPValueTextView);

        final TextView YEPValue = (TextView)findViewById(R.id.YEPValueTextView);
        final TextView GEPValue = (TextView)findViewById(R.id.GEPValueTextView);

        final TextView TKNValue = (TextView)findViewById(R.id.TKValueTextView);
        final TextView TKDValue = (TextView)findViewById(R.id.TKValueTextView2);

        // RACIAL POWER LEVEL TEXTVIEW ASSIGNMENT
        final TextView NPValue = (TextView)findViewById(R.id.namekianPowerTextView);
        final TextView SPValue = (TextView)findViewById(R.id.saiyanPowerTextView);
        final TextView HPValue = (TextView)findViewById(R.id.heranPowerTextView);

        // BUTTON ASSIGNMENT
        final Button CKButton = (Button)findViewById(R.id.currentKiButton);
        final Button DKButton = (Button)findViewById(R.id.defenseKiButton);
        final Button AttackButton = (Button)findViewById(R.id.attackButton);
        final Button PLButton = (Button)findViewById(R.id.powerLevelButton);
        final Button BPButton = (Button)findViewById(R.id.battlePowerButton);
        final Button UpButton = (Button)findViewById(R.id.upButton);
        final Button StartStopButton = (Button)findViewById(R.id.startStopButton);
        final Button DownButton = (Button)findViewById(R.id.downButton);
        //final Button LoadButton = (Button)findViewById(R.id.loadButton);
        //final Button SaveButton = (Button)findViewById(R.id.saveButton);


        /* Set Button Colors */

        CKButton.setBackgroundColor(Color.GRAY);
        CKButton.setTextColor(Color.BLACK);

        DKButton.setBackgroundColor(Color.GRAY);
        DKButton.setTextColor(Color.BLACK);

        PLButton.setBackgroundColor(Color.GRAY);
        PLButton.setTextColor(Color.BLACK);

        BPButton.setBackgroundColor(Color.GRAY);
        BPButton.setTextColor(Color.BLACK);

        UpButton.setBackgroundColor(Color.GREEN);
        UpButton.setTextColor(Color.YELLOW);

        DownButton.setBackgroundColor(Color.RED);
        DownButton.setTextColor(Color.YELLOW);

        // INITIALIZE SCREEN TEXTVIEW VALUES

        if(init == false) {
            avatar = new Avatar();

            playerName = avatar.getAvatarName();
            playerRace = avatar.getAvatarRace();
            TKNRF = avatar.getAvatarTotalKiNumerator();
            TKDRI = avatar.getAvatarTotalKiDenominator();
            CKRF = avatar.getAvatarCurrentKi();
            DKRF = avatar.getAvatarDefenseKi();
            PLRI = avatar.getAvatarPowerLevel();
            BPRI = avatar.getAvatarBattlePower();
            GEPRF = avatar.getAvatarGreenEnergyPercentage();
            YEPRF = avatar.getAvatarYellowEnergyPercentage();
            GETRI = avatar.getAvatarGreenEnergyTimer();
            YETRI = avatar.getAvatarYellowEnergyTimer();
            YTRI = avatar.getAvatarYellowTimer();
            init = true;   // an avatar object is already running
        }

        // INITIALIZE SCREEN VIEW VALUES

        int TKNNum = (int)TKNRF;
            TKNValue.setText(TKNNum + "");
        int TKDNum = TKDRI;
            TKDValue.setText(TKDNum + "");
        int CKNum = (int)CKRF;
            CKValue.setText(CKNum + "");
        int DKNum = (int)DKRF;
            DKValue.setText(DKNum + "");
        int PLNum = PLRI;
            PLValue.setText(PLNum + "");
        int BPNum = BPRI;
            BPValue.setText(BPNum + "");
        float GEPNum = GEPRF;
            GEPValue.setText(GEPNum + "");
        float YEPNum = YEPRF;
            YEPValue.setText(YEPNum + "");

        // Initialize Racial Power Levels


        // BUTTON LISTENERS

        CKButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(CKModifier == false){

                    CKModifier = true;
                    CKButton.setBackgroundColor(Color.BLACK);
                    CKButton.setTextColor(Color.GRAY);
                    DKModifier = false;
                    DKButton.setBackgroundColor(Color.GRAY);
                    DKButton.setTextColor(Color.BLACK);
                    PLModifier = false;
                    PLButton.setBackgroundColor(Color.GRAY);
                    PLButton.setTextColor(Color.BLACK);
                    BPModifier = false;
                    BPButton.setBackgroundColor(Color.GRAY);
                    BPButton.setTextColor(Color.BLACK);


                }
                else if(CKModifier == true) {
                    CKModifier = false;

                    CKButton.setBackgroundColor(Color.GRAY);
                    CKButton.setTextColor(Color.BLACK);
                }

            }
        });

        DKButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(DKModifier == false){

                    DKModifier = true;
                    DKButton.setBackgroundColor(Color.BLACK);
                    DKButton.setTextColor(Color.GRAY);
                    CKModifier = false;
                    CKButton.setBackgroundColor(Color.GRAY);
                    CKButton.setTextColor(Color.BLACK);
                    PLModifier = false;
                    PLButton.setBackgroundColor(Color.GRAY);
                    PLButton.setTextColor(Color.BLACK);
                    BPModifier = false;
                    BPButton.setBackgroundColor(Color.GRAY);
                    BPButton.setTextColor(Color.BLACK);


                }
                else if(DKModifier == true) {
                    DKModifier = false;

                    DKButton.setBackgroundColor(Color.GRAY);
                    DKButton.setTextColor(Color.BLACK);
                }

            }
        });

        AttackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // TOAST VALUES
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                switch(startStopModifier) {

                    case 1:
                        // While attacking...

                        threeDigConv(CKRF);
                        threeDigConv(GEPRF);
                        threeDigConv(YEPRF);

                        int SPNum = Integer.parseInt(SPValue.getText().toString());

                        // only attack if there's current ki available to use
                        if(CKRF > 0){

                            float powerPercent = (CKRF * 100) / TKDRI;      // 20*100 / 100 = 20%
                            threeDigConv(powerPercent);

                            if(GEPRF >= powerPercent) {
                                GEPRF -= powerPercent;
                                // Racial Specific Counters
                                namekianGreenTimer += powerPercent;
                                heranGreenTimer += powerPercent;
                                GETRI += (int)powerPercent;

                                saiyanTimer += 3;                   // Should count full training session for Saiyans

                            }

                            else if(GEPRF < powerPercent && YEPRF != 0 /*>= powerPercent*/){

                                powerPercent -= GEPRF;

                                namekianGreenTimer += GEPRF;
                                heranGreenTimer += GEPRF;
                                GETRI += (int)GEPRF;

                                GEPRF -= GEPRF;
                                //String text = "YEPNum before: " + YEPRF + " and powerPercent before: " + powerPercent;
                                //Toast toast = Toast.makeText(context, text, duration);
                                //toast.show();
                                YEPRF -= powerPercent;
                                //text = "YEPNum after: " + YEPRF + " and powerPercent after: " + powerPercent;
                                //toast = Toast.makeText(context, text, duration);
                                //toast.show();

                                namekianYellowTimer += powerPercent;
                                heranYellowTimer += powerPercent;
                                YETRI += (int)powerPercent;
                                saiyanTimer += 3;

                                powerPercent -= powerPercent;

                            }

                            if(GEPRF <= 0)
                                GEPRF = 0;
                            if(YEPRF <= 0)
                                YEPRF = 0;
                            if((GEPRF + YEPRF + 7.5) <= 100) {
                                YEPRF += 7.5;
                                threeDigConv(YEPRF);

                                if(Math.round(YEPRF) == 8)         // prevent 7.500005
                                    YEPRF = 7.5f;

                                float TKDTemp = (float)TKDRI;

                                TKDTemp *= 0.075;				// 437 * 7.5% = 32.775
                                threeDigConv(TKDTemp);
                                TKNRF += TKDTemp;				// 349.6 + 32.775 = 382.375
                                if (TKNRF >= (float)TKDRI)
                                    TKNRF = (float)TKDRI;

                                int TKNNum = Math.round(TKNRF);		// 382
                                TKNValue.setText(TKNNum + "");
                            }
                            else if((GEPRF + YEPRF + 7.5) > 100) {

                                YEPRF = 100 - GEPRF;
                                TKNRF = (float)TKDRI;

                                int TKNNum = Math.round(TKNRF);
                                TKNValue.setText(TKNNum + "");
                            }


                        }

                            GEPValue.setText(GEPRF + "");     // try to mirror ck and tkn values
                            YEPValue.setText(YEPRF + "");

                        /*
                            BEGIN TO CLEAN UP VALUES FOR DISPLAY
                        */

                        CKRF = 0.0f;

                        if(CKRF <= 180)            // represents 3 mins
                            BPRI = 1;
                        else
                            BPRI = (int)CKRF/180;

                        int BPNum = BPRI;
                        BPValue.setText(BPNum + "");


                        int CKNum = Math.round(CKRF);
                        CKValue.setText(CKNum + "");
                        YTRI = 0;

                        if(saiyanTimer == 15){

                            SPNum += 5;
                            SPValue.setText(SPNum + "");
                            saiyanTimer = 0;
                            if(playerRace == "Saiyan"){
                                TKDRI = SPNum;
                                PLRI = TKDRI;                       // THIS WILL CAUSE GEP AND YEP PROBLEMS!!!!
                                int TKDNum = TKDRI;
                                TKDValue.setText(TKDNum + "");
                            }
                        }

                        break;

                    case 0:

                        // While resting...
                        YTRI += 3;

                        int NPNum = Integer.parseInt(NPValue.getText().toString());
                        int HPNum = Integer.parseInt(HPValue.getText().toString());

                        float TKDTemp = (float)TKDRI;
                        TKDTemp *= 0.075;
                        threeDigConv(TKDTemp);

                        // simply resting up, no stored ki, and ki regen not yet close to full
                        if(CKRF == 0 && DKRF == 0 && TKNRF + TKDTemp <= (float)TKDRI) {
                            TKNRF += TKDTemp;
                            threeDigConv(TKNRF);
                            if(GEPRF + YEPRF + 7.5 <= 100) {
                                YEPRF += 7.5;
                                threeDigConv(GEPRF);
                                threeDigConv(YEPRF);
                            }
                        } // simply resting up, ki regen is close to complete
                        else if(CKRF == 0 && DKRF == 0 && TKNRF + TKDTemp >= (float)TKDRI){
                            TKNRF = (float)TKDRI;
                            float YEPRemainder = 100 - GEPRF - YEPRF;
                            YEPRF += YEPRemainder;

                        }   // in case of some already stored up ki
                        else if(CKRF > 0 || DKRF > 0){

                            if((float)TKDRI - CKRF - DKRF - TKNRF >= TKDTemp){      // 100 - 10 - 5 - 30 = 55 >= 7.5
                                TKNRF += TKDTemp;
                                threeDigConv(TKNRF);
                                if(GEPRF + YEPRF + 7.5 <= 100) {
                                    YEPRF += 7.5;
                                    threeDigConv(GEPRF);
                                    threeDigConv(YEPRF);
                                }
                            }
                            else if((float)TKDRI - CKRF - DKRF - TKNRF < TKDTemp && (float)TKDRI - CKRF - DKRF - TKNRF > 0){
                                TKNRF = (float)TKDRI - CKRF - DKRF;           // 7.5 - (100-90-5) = 2.5
                                if(GEPRF + YEPRF + 7.5 > 100) {
                                    YEPRF = 100 - GEPRF;
                                    threeDigConv(GEPRF);
                                    threeDigConv(YEPRF);
                                }
                            }

                        }


                        int TKNNum = Math.round(TKNRF);
                        TKNValue.setText(TKNNum + "");

                        if(YTRI >= 120 && GEPRF != 100) {
                            YEPRF -= 20;
                            GEPRF += 20;
                            YTRI = 0;

                            if(GEPRF >= 100)
                                GEPRF = 100;

                        }

                        // Namekian and Heran Regrowth
                        if(YTRI == 117 && GEPRF >= 80) {      // A resting period to full Green Energy has occurred

                            float namekGreen = namekianGreenTimer / 100;
                            float namekYellow = namekianYellowTimer / 100;
                            float heranGreen = heranGreenTimer / 100;
                            float heranYellow = heranYellowTimer / 100;

                            namekGreen *= 20;
                            namekYellow *= 5;

                            NPNum += Math.round(namekGreen) + Math.round(namekYellow);
                            NPValue.setText(NPNum + "");

                            heranGreen *= 2.5;
                            heranYellow *= 8.125;

                            HPNum += Math.round(heranGreen) + Math.round(heranYellow);
                            HPValue.setText(HPNum + "");

                            namekianGreenTimer = namekianYellowTimer = heranGreenTimer = heranYellowTimer = 0;


                            if(playerRace == "Namekian") {
                                TKDRI = NPNum;
                                PLRI = TKDRI;
                                if(DKRF > 0) {
                                    DKRF = 0;
                                    DKValue.setText((int)DKRF + "");
                                }
                                TKNRF = (float)TKDRI;
                                TKDValue.setText(TKDRI + "");
                            }
                            if(playerRace == "Heran") {
                                TKDRI = HPNum;
                                PLRI = TKDRI;
                                if(DKRF > 0) {
                                    DKRF = 0;
                                    DKValue.setText((int)DKRF + "");
                                }
                                TKNRF = (float)TKDRI;
                                TKDValue.setText(TKDRI + "");
                            }
                        }

                        if(GEPRF + YEPRF > 100)
                            YEPRF = 100 - GEPRF;

                            YEPValue.setText(YEPRF + "");
                            GEPValue.setText(GEPRF + "");


                        break;

                    default:
                        break;
                }

            }
        });

        PLButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(PLModifier == false){

                    PLModifier = true;
                    PLButton.setBackgroundColor(Color.BLACK);
                    PLButton.setTextColor(Color.GRAY);
                    DKModifier = false;
                    DKButton.setBackgroundColor(Color.GRAY);
                    DKButton.setTextColor(Color.BLACK);
                    CKModifier = false;
                    CKButton.setBackgroundColor(Color.GRAY);
                    CKButton.setTextColor(Color.BLACK);
                    BPModifier = false;
                    BPButton.setBackgroundColor(Color.GRAY);
                    BPButton.setTextColor(Color.BLACK);


                }
                else if(PLModifier == true) {
                    PLModifier = false;

                    PLButton.setBackgroundColor(Color.GRAY);
                    PLButton.setTextColor(Color.BLACK);
                }

            }
        });

        BPButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(BPModifier == false){

                    BPModifier = true;
                    BPButton.setBackgroundColor(Color.BLACK);
                    BPButton.setTextColor(Color.GRAY);
                    DKModifier = false;
                    DKButton.setBackgroundColor(Color.GRAY);
                    DKButton.setTextColor(Color.BLACK);
                    PLModifier = false;
                    PLButton.setBackgroundColor(Color.GRAY);
                    PLButton.setTextColor(Color.BLACK);
                    CKModifier = false;
                    CKButton.setBackgroundColor(Color.GRAY);
                    CKButton.setTextColor(Color.BLACK);


                }
                else if(BPModifier == true) {
                    BPModifier = false;

                    BPButton.setBackgroundColor(Color.GRAY);
                    BPButton.setTextColor(Color.BLACK);
                }

            }
        });

        UpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(CKModifier) {

                    boolean fillUp;

                    // determine if there is 20% or more available ki to fill up current ki (should be @string...)
                    if(TKNRF >= (float)TKDRI * 0.2)					// if (437 >= 437 * 0.2 				[87.4]
                        fillUp = true;
                    else
                        fillUp = false;

                    if(CKRF == 0) {

                        if(fillUp){

                            CKRF += (float)TKDRI * 0.2;				// 0 + 87.4 					[87.4]
                            TKNRF -= (float)TKDRI * 0.2;				// 437.0 - 87.4					[349.6]
                        }
                        else if(!fillUp){

                            CKRF += TKNRF;
                            TKNRF -= TKNRF;
                        }
                    }
                    else if(CKRF != 0){
                        // determine if at least 20% room still available for full current ki fill up
                        if(CKRF <= (float)TKDRI * 0.8){				// if CKRF was like 23 <= 349.6

                            if(fillUp){

                                CKRF += (float)TKDRI * 0.2;			// 23 + 87.4  					[110]
                                TKNRF -= (float)TKDRI * 0.2;			//
                            }
                            else if(!fillUp){

                                CKRF += TKNRF;
                                TKNRF -= TKNRF;
                            }

                        }   // current ki is more than 80% at the moment
                        else if(CKRF > (float)TKDRI * 0.8){			// 360 > 349.6

                            CKRF += TKNRF;
                            TKNRF -= TKNRF;

                        }

                    }

                    int CKNum = Math.round(CKRF);

                    if(CKRF <= 180)            // represents 3 mins
                        BPRI = 1;
                    else
                        BPRI = (int)CKRF / 180;

                    BPValue.setText(BPRI + "");

                    TKNValue.setText((int)TKNRF + "");
                    CKValue.setText((int)CKRF + "");

                }

                if(DKModifier) { // because DK is priority, if TKN empty, should be able to draw from CK in extreme cases.

                    boolean fillUp;

                    if(TKNRF >= (float)TKDRI * 0.050)
                        fillUp = true;
                    else
                        fillUp = false;

                    if(fillUp) {

                        if (DKRF <= (float)TKDRI * 0.95) {

                            DKRF += (float)TKDRI * 0.050;
                            TKNRF -= (float)TKDRI * 0.050;
                        }
                        else if(DKRF > (float)TKDRI * 0.95){

                            TKNRF -= (float)TKDRI - DKRF;
                            DKRF += (float)TKDRI - DKRF;
                        }
                    }
                    else if(!fillUp){
                        // if there's hardly any ki left to spare but defense ki isn't close to full
                        if (DKRF <= (float)TKDRI * 0.95) {
                            DKRF += TKNRF;
                            TKNRF -= TKNRF;
                        }   // if defense ki was, say 97% but available ki is less that 5%, we need max 3% of whatever we got
                        else if(DKRF > (float)TKDRI * 0.95){

                            DKRF += TKNRF;
                            TKNRF -= TKNRF;

                        }
                    }

                    TKNValue.setText((int)TKNRF + "");
                    DKValue.setText((int)DKRF + "");

                    /*
                    Because DK is so important, it can also be used directly during battle. Rather than using CK for blocking,
                    if DK is used, 1 point of DK blocks 1.5 points of CK from enemy.
                     */
                }

                if(PLModifier) {

                    /*
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int PLNum = Integer.parseInt(PLValue.getText().toString());

                    PLNum += (TKDNum * 0.1);

                    if(PLNum >= TKDNum)
                        PLNum = TKDNum;

                    PLValue.setText(PLNum + "");
                    */
                }

                if(BPModifier) {

                    /* Temporarily Disable Changing Battle Power

                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int BPNum = Integer.parseInt(BPValue.getText().toString());
                    int CKNum = Integer.parseInt(CKValue.getText().toString());

                    if(CKNum <= 180)            // represents 3 mins
                        BPNum = 1;
                    else if(CKNum > 180) {
                        BPNum += (int) (TKDNum * 0.01);
                        if (BPNum >= CKNum/180)
                            BPNum = CKNum/180;
                        BPValue.setText(BPNum + "");
                    }
                    */
                }

            }
        });

        StartStopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Button ChangeButton = (Button)findViewById(R.id.startStopButton);
                Button ChangeButton2 = (Button)findViewById(R.id.attackButton);

                switch(startStopModifier){

                    case 1:
                        startStopModifier = 0;
                        ChangeButton.setText("Resting");
                        ChangeButton2.setText("Rest(3)");
                        break;
                    case 0:
                        startStopModifier = 1;
                        ChangeButton.setText("Training");
                        ChangeButton2.setText("Attack(3)");
                        break;
                    default:
                        break;
                }

            }
        });

        DownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(CKModifier) {

                    // if current ki is 20% or more, can we put it all back into available ki (keeping DK in mind)
                    if(TKNRF <= (float)TKDRI * 0.8){

                        if(CKRF >= (float)TKDRI * 0.2){

                            CKRF -= (float)TKDRI * 0.2;
                            TKNRF += (float)TKDRI * 0.2;
                        }
                        else if(CKRF < (float)TKDRI * 0.2){

                            TKNRF += CKRF;
                            CKRF -= CKRF;
                        }


                    }   // if available ki space is less than 20%
                    else if(TKNRF > (float)TKDRI * 0.8){
                        // if current ki is a lot
                        if(CKRF >= (float)TKDRI - TKNRF){

                            CKRF -= (float)TKDRI - TKNRF;
                            TKNRF += (float)TKDRI - TKNRF;
                        }
                        else if(CKRF < (float)TKDRI - TKNRF){

                            TKNRF += CKRF;
                            CKRF -= CKRF;
                        }

                    }

                    if(CKRF <= 180)            // represents 3 mins
                        BPRI = 1;
                    else
                        BPRI = (int)CKRF/180;

                    if(BPRI <= 1)
                        BPRI = 1;						// In case player tries to lower it manually
                    BPValue.setText(BPRI + "");

                    TKNValue.setText((int)TKNRF + "");
                    CKValue.setText((int)CKRF + "");

                }


                if(DKModifier) {

                    // if available ki can handle receiving a full %5 of defense ki
                    if(TKNRF <= (float)TKDRI * 0.95){

                        if(DKRF >= (float)TKDRI * 0.050){

                            DKRF -= (float)TKDRI * 0.050;
                            TKNRF += (float)TKDRI * 0.050;
                        }
                        else if(DKRF < (float)TKDRI * 0.050){

                            TKNRF += DKRF;
                            DKRF -= DKRF;
                        }


                    }   // if available space is very limited
                    else if(TKNRF > (float)TKDRI * 0.95){

                        TKNRF += DKRF;
                        DKRF -= DKRF;

                    }

                    TKNValue.setText((int)TKNRF + "");
                    DKValue.setText((int)DKRF + "");

                }

                if(PLModifier) {

                    /*
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int PLNum = Integer.parseInt(PLValue.getText().toString());

                    PLNum -= (TKDNum * 0.1);

                    if(PLNum <= 0)
                        PLNum = 0;

                    PLValue.setText(PLNum + "");
                    */
                }

                if(BPModifier) {

                    /* Temporarily Disable Changing Battle Power

                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int BPNum = Integer.parseInt(BPValue.getText().toString());


                    BPNum -= (int)(TKDNum * 0.01);
                    if(BPNum <= 1)
                        BPNum = 1;
                    BPValue.setText(BPNum + "");

                    */
                }

            }
        });







    }

    public void loadMyAvatar(View view) {

        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Avatar avatar = dbHandler.loadAvatar(playerName);

        if(avatar != null){

            TKNRF  = avatar.getAvatarTotalKiNumerator();
            TKDRI  = avatar.getAvatarTotalKiDenominator();
            CKRF   = avatar.getAvatarCurrentKi();
            DKRF   = avatar.getAvatarDefenseKi();
            PLRI   = avatar.getAvatarPowerLevel();
            BPRI   = avatar.getAvatarBattlePower();
            GEPRF  = avatar.getAvatarGreenEnergyPercentage();
            YEPRF  = avatar.getAvatarYellowEnergyPercentage();
            GETRI  = avatar.getAvatarGreenEnergyTimer();
            YETRI  = avatar.getAvatarYellowEnergyTimer();
            YTRI   = avatar.getAvatarYellowTimer();
        }
        else{
            Avatar newAvatar = new Avatar();

            playerName = newAvatar.getAvatarName();
            playerRace = newAvatar.getAvatarRace();
            TKNRF  = newAvatar.getAvatarTotalKiNumerator();
            TKDRI  = newAvatar.getAvatarTotalKiDenominator();
            CKRF   = newAvatar.getAvatarCurrentKi();
            DKRF   = newAvatar.getAvatarDefenseKi();
            PLRI   = newAvatar.getAvatarPowerLevel();
            BPRI   = newAvatar.getAvatarBattlePower();
            GEPRF  = newAvatar.getAvatarGreenEnergyPercentage();
            YEPRF  = newAvatar.getAvatarYellowEnergyPercentage();
            GETRI  = newAvatar.getAvatarGreenEnergyTimer();
            YETRI  = newAvatar.getAvatarYellowEnergyTimer();
            YTRI   = newAvatar.getAvatarYellowTimer();

        }
    }

    public void saveMyAvatar(View view){

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        dbHandler.deleteAvatar(playerName);
        Avatar saveNewAvatar = new Avatar();

        saveNewAvatar.setAvatarName(playerName);
        saveNewAvatar.setAvatarRace(playerRace);
        saveNewAvatar.setAvatarTotalKiNumerator(TKNRF);
        saveNewAvatar.setAvatarTotalKiDenominator(TKDRI);
        saveNewAvatar.setAvatarCurrentKi(CKRF);
        saveNewAvatar.setAvatarDefenseKi(DKRF);
        saveNewAvatar.setAvatarPowerLevel(PLRI);
        saveNewAvatar.setAvatarBattlePower(BPRI);
        saveNewAvatar.setAvatarGreenEnergyPercentage(GEPRF);
        saveNewAvatar.setAvatarYellowEnergyPercentage(YEPRF);
        saveNewAvatar.setAvatarGreenEnergyTimer(GETRI);
        saveNewAvatar.setAvatarYellowEnergyTimer(YETRI);
        saveNewAvatar.setAvatarYellowTimer(YTRI);

        dbHandler.saveAvatar(saveNewAvatar);
    }

    public float threeDigConv(float number){

        // Receive number, say: 7.500005        OR          32.775
        number *= 1000;                          // = 7500.05        = 32775
        number = (int)number;                    // = 7500           = 32775
        //number = Math.round(number);           // = 7500            = 32775
        number /= 1000;                          // = 7.5            = 32.775

        return number;
    }

}

/*

*/
