package com.holdings.siloaman.dbzo_leveller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;


public class MainActivity extends Activity {

    public static class MyDataEntry implements BaseColumns {

        public static final String TABLE_NAME = "DBZO_TABLE";
        public static final String TOTAL_KI_NUMERATOR_COLUMN = "TKN_C";
        public static final String TOTAL_KI_DENOMINATOR_COLUMN = "TKD_C";
        public static final String CURRENT_KI_COLUMN = "CK_C";
        public static final String DEFENSE_KI_COLUMN = "DK_C";
        public static final String POWER_LEVEL_COLUMN = "PL_C";
        public static final String BATTLE_POWER_COLUMN = "BP_C";
        public static final String GREEN_ENERGY_PERCENT_COLUMN = "GEP_C";
        public static final String YELLOW_ENERGY_PERCENT_COLUMN = "YEP_C";
    }

    /*
    ANOTHER HELPER CLASS - is for managing database creating and version management
     */
    public class MyDBHelper extends SQLiteOpenHelper{
        //some static variables to remember information about our database
        //one is the database name
        public static final String DB_NAME = "DBZO_DataBase.db";
        //note: every time you change the database schema, you must increment this database version
        //the other is the database version
        public static final int DB_VERSION = 1;

        private static final String SQL_CREATE_TABLE = "CREATE TABLE " + MyDataEntry.TABLE_NAME
                + " (" + MyDataEntry._ID    // _ID inherited from BaseColumns
                + " INTEGER PRIMARY KEY,"
                + MyDataEntry.TOTAL_KI_NUMERATOR_COLUMN + " TEXT,"
                + MyDataEntry.TOTAL_KI_DENOMINATOR_COLUMN + " TEXT )"
                + MyDataEntry.CURRENT_KI_COLUMN + " TEXT )"
                + MyDataEntry.DEFENSE_KI_COLUMN + " TEXT )"
                + MyDataEntry.POWER_LEVEL_COLUMN + " TEXT )"
                + MyDataEntry.BATTLE_POWER_COLUMN + " TEXT )"
                + MyDataEntry.GREEN_ENERGY_PERCENT_COLUMN + " TEXT )"
                + MyDataEntry.YELLOW_ENERGY_PERCENT_COLUMN + " TEXT )";


        private static final String SQL_DELETE_QUERY = "DROP TABLE IF EXISTS" + MyDataEntry.TABLE_NAME;

        public MyDBHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            System.out.println("Executing Query: SQL_CREATE_TABLE " + SQL_CREATE_TABLE);     //DEBUGGING TIP
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //Shortcut: let's discard the table and start over.
            db.execSQL(SQL_DELETE_QUERY);
            onCreate(db);       // will re-create the database for us
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    //declare the preferences file name as a constant string for easy use elsewhere in code
    //choose a cool file name
    public static final String PREF_FILE_NAME = "MySenecaPrefs";
	
	

    boolean CKModifier = false;
    boolean DKModifier = false;
    boolean PLModifier = false;
    boolean BPModifier = false;

    int startStopModifier = 1;
    int yellowTimer = 0;

    float namekianGreenTimer = 0;
    float namekianYellowTimer = 0;

    int saiyanTimer = 0;

    float heranGreenTimer = 0;
    float heranYellowTimer = 0;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView CKValue = (TextView)findViewById(R.id.CKValueTextView);
        final TextView DKValue = (TextView)findViewById(R.id.DKValueTextView);
        final TextView PLValue = (TextView)findViewById(R.id.PLValueTextView);
        final TextView BPValue = (TextView)findViewById(R.id.BPValueTextView);

        final TextView YEPValue = (TextView)findViewById(R.id.YEPValueTextView);
        final TextView GEPValue = (TextView)findViewById(R.id.GEPValueTextView);

        final TextView TKNValue = (TextView)findViewById(R.id.TKValueTextView);
        final TextView TKDValue = (TextView)findViewById(R.id.TKValueTextView2);

        final TextView NPValue = (TextView)findViewById(R.id.namekianPowerTextView);
        final TextView SPValue = (TextView)findViewById(R.id.saiyanPowerTextView);
        final TextView HPValue = (TextView)findViewById(R.id.heranPowerTextView);

        final Button CKButton = (Button)findViewById(R.id.currentKiButton);
        final Button DKButton = (Button)findViewById(R.id.defenseKiButton);
        final Button AttackButton = (Button)findViewById(R.id.attackButton);
        final Button PLButton = (Button)findViewById(R.id.powerLevelButton);
        final Button BPButton = (Button)findViewById(R.id.battlePowerButton);
        final Button UpButton = (Button)findViewById(R.id.upButton);
        final Button StartStopButton = (Button)findViewById(R.id.startStopButton);
        final Button DownButton = (Button)findViewById(R.id.downButton);
        final Button LoadButton = (Button)findViewById(R.id.loadButton);
        final Button SaveButton = (Button)findViewById(R.id.saveButton);

        // Set Button Colors

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

        int PLNum = Integer.parseInt(PLValue.getText().toString());
        int TKDNum = Integer.parseInt(TKDValue.getText().toString());

        PLNum = TKDNum;
        PLValue.setText(PLNum + "");

        // Initialize Racial Power Levels


		

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



                switch(startStopModifier) {

                    case 1:
                        // While attacking...
                        
                        int CKNum = Integer.parseInt(CKValue.getText().toString());
                        float YEPNum = Float.parseFloat(YEPValue.getText().toString());
                        float GEPNum = Float.parseFloat(GEPValue.getText().toString());

                        int SPNum = Integer.parseInt(SPValue.getText().toString());

                        if(CKNum > 0){

                            int CKAmount = CKNum;
                            int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                            int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                            float powerPercent = ((float)CKAmount * 100) / TKDNum;
                            // if powerPercent is 0.4 or something, it equates to 0 and messes up function!!

                            if(GEPNum >= powerPercent) {
                                GEPNum -= powerPercent;             // Racial Specific Counters
                                                                    namekianGreenTimer += powerPercent;
                                                                    heranGreenTimer += powerPercent;
                                                                    saiyanTimer += 3;                   // Should count full training session for Saiyans

                            }

                            else if(GEPNum < powerPercent && YEPNum >= powerPercent){

                                powerPercent -= GEPNum;

                                                                    namekianGreenTimer += GEPNum;
                                                                    heranGreenTimer += GEPNum;
                                GEPNum -= GEPNum;
                                YEPNum -= powerPercent;
                                                                    namekianYellowTimer += powerPercent;
                                                                    heranYellowTimer += powerPercent;
                                                                    saiyanTimer += 3;
                                powerPercent -= powerPercent;

                            }

                            if(GEPNum <= 0)
                                GEPNum = 0;
                            if((GEPNum + YEPNum + 7.5) <= 100) {
                                YEPNum += 7.5;

                                TKDNum = Integer.parseInt(TKDValue.getText().toString());
                                int TKDTemp = Integer.parseInt(TKDValue.getText().toString());
                                TKNNum = Integer.parseInt(TKNValue.getText().toString());

                                TKDTemp *= 0.075;
                                TKNNum += TKDTemp;
                                if (TKNNum >= TKDNum)
                                    TKNNum = TKDNum;

                                TKNValue.setText(TKNNum + "");
                            }
                            else if((GEPNum + YEPNum + 7.5) > 100) {

                                TKNNum = Integer.parseInt(TKNValue.getText().toString());

                                YEPNum = 100 - GEPNum;
                                TKNNum = TKDNum;
                                if (TKNNum >= TKDNum)
                                    TKNNum = TKDNum;

                                TKNValue.setText(TKNNum + "");
                            }


                        }

                        int TKDNum = Integer.parseInt(TKDValue.getText().toString());

                        // every 3 mins use up 20% Green
                        if(TKDNum <= 100) {
                            int GEP = (int)GEPNum;
                            int YEP = (int)YEPNum;

                            GEPValue.setText(GEP + "");     // try to mirror ck and tkn values
                            YEPValue.setText(YEP + "");
                        }
                        else if(TKDNum > 100){                      // allow for decimal values to show
                            float GEP = GEPNum;
                            float YEP = YEPNum;

                            GEPValue.setText(GEP + "");
                            YEPValue.setText(YEP + "");

                        }

                        CKNum = 0;

                        int BPNum = Integer.parseInt(BPValue.getText().toString());

                        if(CKNum <= 180)            // represents 3 mins
                            BPNum = 1;
                        else
                            BPNum = CKNum/180;

                        if(BPNum <= 1)
                            BPNum = 1;
                        BPValue.setText(BPNum + "");

                        CKValue.setText(CKNum + "");
                        yellowTimer = 0;

                        if(saiyanTimer == 15){

                            SPNum += 5;
                            SPValue.setText(SPNum + "");
                            saiyanTimer = 0;
                        }

                        break;

                    case 0:
                        // While resting...
                        yellowTimer += 3;

                        YEPNum = Float.parseFloat(YEPValue.getText().toString());
                        GEPNum = Float.parseFloat(GEPValue.getText().toString());
                        int TKNNum = Integer.parseInt(TKNValue.getText().toString());

                        int NPNum = Integer.parseInt(NPValue.getText().toString());
                        int HPNum = Integer.parseInt(HPValue.getText().toString());


                        YEPNum += 7.5;

                        if(GEPNum == 0 && YEPNum >= 100)
                            YEPNum = 100;

                        if(TKNNum <= 100)
                            YEPValue.setText((int)YEPNum + "");
                        else if(TKNNum > 100)
                            YEPValue.setText(YEPNum + "");

                        TKDNum = Integer.parseInt(TKDValue.getText().toString());
                        int TKDTemp = Integer.parseInt(TKDValue.getText().toString());
                        TKNNum = Integer.parseInt(TKNValue.getText().toString());
                        CKNum = Integer.parseInt(CKValue.getText().toString());
                        int DKNum = Integer.parseInt(DKValue.getText().toString());

                        TKDTemp *= 0.075;

                        if(CKNum == 0 && DKNum == 0) {

                            TKNNum += TKDTemp;
                            if (TKNNum >= TKDNum)
                                TKNNum = TKDNum;
                        }
                        else if(CKNum > 0 || DKNum > 0){

                            if(TKDNum - CKNum - DKNum == TKNNum)
                                TKNNum = TKNNum;
                            if(TKDNum - CKNum - DKNum > TKNNum)
                                TKNNum += TKDTemp;
                            if(TKNNum >= TKDNum - CKNum - DKNum)
                                TKNNum = TKDNum - CKNum - DKNum;
                        }

                        TKNValue.setText(TKNNum + "");

                        if(yellowTimer >= 120 && GEPNum != 100) {
                            YEPNum -= 20;
                            GEPNum += 20;
                            yellowTimer = 0;

                            if(GEPNum >= 100)
                                GEPNum = 100;

                        }

                                        // Namekian and Heran Regrowth
                                        if(yellowTimer == 117 && GEPNum >= 80) {      // A resting period to full Green Energy has occurred

                                            float namekGreen = namekianGreenTimer / 100;
                                            float namekYellow = namekianYellowTimer / 100;
                                            float heranGreen = heranGreenTimer / 100;
                                            float heranYellow = heranYellowTimer / 100;

                                            namekGreen *= 20;
                                            namekYellow *= 5;

                                            NPNum += namekGreen + namekYellow;
                                            NPValue.setText(NPNum + "");

                                            heranGreen *= 2.5;
                                            heranYellow *= 8.125;

                                            HPNum += heranGreen + heranYellow;
                                            HPValue.setText(HPNum + "");

                                            namekianGreenTimer = namekianYellowTimer = heranGreenTimer = heranYellowTimer = 0;
                                        }

                        if(GEPNum + YEPNum > 100)
                            YEPNum = 100 - GEPNum;

                        if(TKDNum <= 100){
                            YEPValue.setText((int) YEPNum + "");
                            GEPValue.setText((int) GEPNum + "");
                        }
                        else if(TKDNum > 100){
                            YEPValue.setText((float)YEPNum + "");
                            GEPValue.setText((float)GEPNum + "");
                        }

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
                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int CKNum = Integer.parseInt(CKValue.getText().toString());
                    boolean fillUp;

                    if(TKNNum >= TKDNum * 0.2)
                        fillUp = true;
                    else
                        fillUp = false;

                    if(CKNum == 0) {


                        if(fillUp){

                            CKNum += (TKDNum * 0.2);
                            TKNNum -= (TKDNum * 0.2);
                        }
                        else if(!fillUp){

                            CKNum += TKNNum;
                            TKNNum -= TKNNum;
                        }
                    }
                    else if(CKNum != 0){

                        if(CKNum <= TKDNum * 0.8){

                            if(fillUp){

                                CKNum += (TKDNum * 0.2);
                                TKNNum -= (TKDNum * 0.2);
                            }
                            else if(!fillUp){

                                CKNum += TKNNum;
                                TKNNum -= TKNNum;
                            }

                        }
                        else if(CKNum > TKDNum * 0.8){

                            if(fillUp){

                                TKNNum -= TKDNum - CKNum;
                                CKNum += TKDNum - CKNum;
                            }
                            else if(!fillUp){

                                if(TKNNum >= TKDNum - CKNum){

                                    TKNNum -= TKDNum - CKNum;
                                    CKNum += TKDNum - CKNum;
                                }
                                else if(TKNNum < TKDNum - CKNum){

                                    CKNum += TKNNum;
                                    TKNNum -= TKNNum;
                                }
                            }

                        }

                    }

                    int BPNum = Integer.parseInt(BPValue.getText().toString());

                    if(CKNum <= 180)            // represents 3 mins
                        BPNum = 1;
                    else
                        BPNum = CKNum/180;

                    if(BPNum <= 1)
                        BPNum = 1;
                    BPValue.setText(BPNum + "");

                    TKNValue.setText(TKNNum + "");
                    CKValue.setText(CKNum + "");

                }

                if(DKModifier) { // because DK is priority, if TKN empty, should be able to draw from CK in extreme cases.
                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int DKNum = Integer.parseInt(DKValue.getText().toString());
                    boolean fillUp;

                    if(TKNNum >= TKDNum * 0.050)
                        fillUp = true;
                    else
                        fillUp = false;

                    if(fillUp) {

                        if (DKNum <= (TKDNum * 0.95)) {

                            DKNum += TKDNum * 0.050;
                            TKNNum -= TKDNum * 0.050;
                        }
                        else if(DKNum >= TKDNum * 0.95){

                            TKNNum -= TKDNum - DKNum;
                            DKNum += TKDNum - DKNum;
                        }
                    }
                    else if(!fillUp){

                        DKNum += TKNNum;
                        TKNNum -= TKNNum;
                    }


                    TKNValue.setText(TKNNum + "");
                    DKValue.setText(DKNum + "");

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

                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int CKNum = Integer.parseInt(CKValue.getText().toString());

                    if(TKNNum <= TKDNum * 0.8){

                        if(CKNum >= TKDNum * 0.2){

                            CKNum -= TKDNum * 0.2;
                            TKNNum += TKDNum * 0.2;
                        }
                        else if(CKNum < TKDNum * 0.2){

                            TKNNum += CKNum;
                            CKNum -= CKNum;
                        }


                    }
                    else if(TKNNum > TKDNum * 0.8){

                        if(CKNum >= TKDNum - TKNNum){

                            CKNum -= TKDNum - TKNNum;
                            TKNNum += TKDNum - TKNNum;
                        }
                        else if(CKNum < TKDNum - TKNNum){

                            TKNNum += CKNum;
                            CKNum -= CKNum;
                        }

                    }

                    int BPNum = Integer.parseInt(BPValue.getText().toString());

                    if(CKNum <= 180)            // represents 3 mins
                        BPNum = 1;
                    else
                        BPNum = CKNum/180;

                    if(BPNum <= 1)
                        BPNum = 1;
                    BPValue.setText(BPNum + "");

                    TKNValue.setText(TKNNum + "");
                    CKValue.setText(CKNum + "");

                }


                if(DKModifier) {
                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int DKNum = Integer.parseInt(DKValue.getText().toString());

                    if(TKNNum <= TKDNum * 0.95){

                        if(DKNum >= TKDNum * 0.050){

                            DKNum -= TKDNum * 0.050;
                            TKNNum += TKDNum * 0.050;
                        }
                        else if(DKNum < TKDNum * 0.050){

                            TKNNum += DKNum;
                            DKNum -= DKNum;
                        }


                    }
                    else if(TKNNum > TKDNum * 0.95){

                        if(DKNum >= TKDNum - TKNNum){

                            DKNum -= TKDNum - TKNNum;
                            TKNNum += TKDNum - TKNNum;
                        }
                        else if(DKNum < TKDNum - TKNNum){

                            TKNNum += DKNum;
                            DKNum -= DKNum;
                        }

                    }

                    TKNValue.setText(TKNNum + "");
                    DKValue.setText(DKNum + "");

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

}

/*
    
*/
