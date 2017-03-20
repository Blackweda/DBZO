package com.holdings.siloaman.dbzo_leveller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


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
                        int BPNum = Integer.parseInt(BPValue.getText().toString());
                        float YEPNum = Float.parseFloat(YEPValue.getText().toString());
                        float GEPNum = Float.parseFloat(GEPValue.getText().toString());



                            if(CKNum != 0) {

                                GEPNum -= 20;
                                if (GEPNum <= 0)
                                    GEPNum = 0;
                                if (GEPNum <= 80 && (YEPNum + 7.5) <= 100)
                                    YEPNum += 7.5;      // regen 7.5% yellow
                                if (GEPNum <= 80 && (YEPNum + 7.5) >= 100)
                                    YEPNum = 100 - GEPNum;
                                if (GEPNum == 0 && YEPNum >= 20)
                                    YEPNum -= 20;

                                // every 3 mins use up 20% Green
                                GEPValue.setText(GEPNum + "");
                                YEPValue.setText(YEPNum + "");
                            }

                            CKNum = 0;
                            CKValue.setText(CKNum + "");


                            int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                            int TKDTemp = Integer.parseInt(TKDValue.getText().toString());
                            int TKNNum = Integer.parseInt(TKNValue.getText().toString());


                        // THIS CODE EXECUTES REGARDLESS OF IF CK IS ZERO AND NO ATTACK HAPPENED.
                            TKDTemp *= 0.075;
                            TKNNum += TKDTemp;
                            if (TKNNum >= TKDNum)
                                TKNNum = TKDNum;

                            TKNValue.setText(TKNNum + "");

                            yellowTimer = 0;



                        break;

                    case 0:
                        // While resting...
                        yellowTimer += 3;

                        YEPNum = Float.parseFloat(YEPValue.getText().toString());
                        GEPNum = Float.parseFloat(GEPValue.getText().toString());

                        YEPNum += 7.5;

                        if(GEPNum == 0 && YEPNum >= 100)
                            YEPNum = 100;

                        YEPValue.setText(YEPNum + "");

                        TKDNum = Integer.parseInt(TKDValue.getText().toString());
                        TKDTemp = Integer.parseInt(TKDValue.getText().toString());
                        TKNNum = Integer.parseInt(TKNValue.getText().toString());
                        TKDTemp *= 0.075;
                        TKNNum += TKDTemp;
                        if(TKNNum  >= TKDNum)
                            TKNNum = TKDNum;

                        TKNValue.setText(TKNNum + "");

                        if(yellowTimer >= 120 && GEPNum != 100) {
                            YEPNum -= 20;
                            GEPNum += 20;
                            yellowTimer = 0;

                            if(GEPNum >= 100)
                                GEPNum = 100;

                        }

                        if(GEPNum + YEPNum > 100)
                            YEPNum = 100 - GEPNum;

                        YEPValue.setText(YEPNum + "");
                        GEPValue.setText(GEPNum + "");

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

                        int addMissing = (int)(TKDNum * 0.2) - CKNum;
                        // could also make float and modulus % 0.5 = answer + 0.5 for perfect int

                        if(fillUp){

                            CKNum += addMissing;
                            TKNNum -= addMissing;
                        }
                        else if(!fillUp){

                            if(TKNNum >= addMissing){
                                CKNum += addMissing;
                                TKNNum -= addMissing;
                            }
                            else if(TKNNum <= addMissing){
                                CKNum += TKNNum;
                                TKNNum -= TKNNum;
                            }
                        }
                    }

                    TKNValue.setText(TKNNum + "");
                    CKValue.setText(CKNum + "");

                }

                if(DKModifier) {
                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int DKNum = Integer.parseInt(DKValue.getText().toString());
                    boolean fillUp;

                    if(TKNNum >= TKDNum * 0.050)
                        fillUp = true;
                    else
                        fillUp = false;

                    if(DKNum == 0) {


                        if (fillUp) {

                            DKNum += (TKDNum * 0.050);
                            TKNNum -= (TKDNum * 0.050);
                        }
                        else if(!fillUp){

                            DKNum += TKNNum;
                            TKNNum -= TKNNum;
                        }
                    }
                    else if(DKNum != 0){

                        int addMissing = (int)(TKDNum * 0.050) - DKNum;

                        if(fillUp){

                            DKNum += addMissing;
                            TKNNum -= addMissing;
                        }
                        else if(!fillUp){

                            if(TKNNum >= addMissing){
                                DKNum += addMissing;
                                TKNNum -= addMissing;
                            }
                            else if(TKNNum <= addMissing){
                                DKNum += TKNNum;
                                TKNNum -= TKNNum;
                            }
                        }
                    }

                    TKNValue.setText(TKNNum + "");
                    DKValue.setText(DKNum + "");

                }

                if(PLModifier) {
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int PLNum = Integer.parseInt(PLValue.getText().toString());

                    PLNum += (TKDNum * 0.1);

                    if(PLNum >= TKDNum)
                        PLNum = TKDNum;

                    PLValue.setText(PLNum + "");
                }

                if(BPModifier) {
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int BPNum = Integer.parseInt(BPValue.getText().toString());
                    int CKNum = Integer.parseInt(CKValue.getText().toString());

                    BPNum += (int)(TKDNum * 0.01);
                    if(BPNum >= CKNum)
                        BPNum = CKNum;
                    BPValue.setText(BPNum + "");
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
                    int addMissing;

                    addMissing = TKDNum - TKNNum;

                    if(addMissing >= CKNum){
                        TKNNum += CKNum;
                        CKNum -= CKNum;
                    }
                    else if(addMissing <= CKNum){
                        TKNNum += addMissing;
                        CKNum -= addMissing;
                    }

                    TKNValue.setText(TKNNum + "");
                    CKValue.setText(CKNum + "");

                }


                if(DKModifier) {
                    int TKNNum = Integer.parseInt(TKNValue.getText().toString());
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int DKNum = Integer.parseInt(DKValue.getText().toString());
                    int addMissing;

                    addMissing = TKDNum - TKNNum;

                    if(addMissing >= DKNum){
                        TKNNum += DKNum;
                        DKNum -= DKNum;
                    }
                    else if(addMissing <= DKNum){
                        TKNNum += addMissing;
                        DKNum -= addMissing;
                    }

                    TKNValue.setText(TKNNum + "");
                    DKValue.setText(DKNum + "");

                }

                if(PLModifier) {
                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int PLNum = Integer.parseInt(PLValue.getText().toString());

                    PLNum -= (TKDNum * 0.1);

                    if(PLNum <= 0)
                        PLNum = 0;

                    PLValue.setText(PLNum + "");
                }

                if(BPModifier) {

                    int TKDNum = Integer.parseInt(TKDValue.getText().toString());
                    int BPNum = Integer.parseInt(BPValue.getText().toString());


                    BPNum -= (int)(TKDNum * 0.01);
                    if(BPNum <= 0)
                        BPNum = 0;
                    BPValue.setText(BPNum + "");
                }

            }
        });

    }

}
