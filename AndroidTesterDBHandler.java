package com.holdings.siloaman.dbzo_leveller;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;


/**
 * Created by Wayne on 2017-04-14.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DBZODatabase.db";


    public static class MyDataEntry implements BaseColumns {

        // Naming the Columns For Displaying
        // note that BaseColumns gives a hidden '_ID' which Android Database needs
        public static final String AVATAR_TABLE_NAME = "MyAvatar";
        public static final String AVATAR_ID_COLUMN = "Player_ID";
        public static final String AVATAR_NAME_COLUMN = "Player_Name";
        public static final String AVATAR_RACE_COLUMN = "Player_Race";
        public static final String TOTAL_KI_NUMERATOR_COLUMN = "Total_Ki_Numerator";
        public static final String TOTAL_KI_DENOMINATOR_COLUMN = "Total_Ki_Denominator";
        public static final String CURRENT_KI_COLUMN = "Current_Ki";
        public static final String DEFENSE_KI_COLUMN = "Defense_Ki";
        public static final String POWER_LEVEL_COLUMN = "Power_Level";
        public static final String BATTLE_POWER_COLUMN = "Battle_Power";
        public static final String STAMINA_COLUMN = "Stamina";
        public static final String GREEN_ENERGY_PERCENT_COLUMN = "Green_Energy_Percentage";
        public static final String YELLOW_ENERGY_PERCENT_COLUMN = "Yellow_Energy_Percentage";
        public static final String GREEN_ENERGY_TIMER_COLUMN = "Green_Energy_Timer";
        public static final String YELLOW_ENERGY_TIMER_COLUMN = "Yellow_Energy_Timer";
        public static final String YELLOW_TIMER_COLUMN = "Yellow_Timer";
    }

    String SQL_CREATE_TABLE = "CREATE TABLE " + MyDataEntry.AVATAR_TABLE_NAME
            + " (" + MyDataEntry._ID + " INTEGER PRIMARY KEY,"
            + MyDataEntry.AVATAR_ID_COLUMN + " INTEGER,"
            + MyDataEntry.AVATAR_NAME_COLUMN + " TEXT,"
            + MyDataEntry.AVATAR_RACE_COLUMN + " TEXT,"
            + MyDataEntry.TOTAL_KI_NUMERATOR_COLUMN + " FLOAT,"
            + MyDataEntry.TOTAL_KI_DENOMINATOR_COLUMN + " INTEGER,"
            + MyDataEntry.CURRENT_KI_COLUMN + " FLOAT,"
            + MyDataEntry.DEFENSE_KI_COLUMN + " FLOAT,"
            + MyDataEntry.POWER_LEVEL_COLUMN + " INTEGER,"
            + MyDataEntry.BATTLE_POWER_COLUMN + " INTEGER,"
            + MyDataEntry.STAMINA_COLUMN + " INTEGER,"
            + MyDataEntry.GREEN_ENERGY_PERCENT_COLUMN + " FLOAT,"
            + MyDataEntry.YELLOW_ENERGY_PERCENT_COLUMN + " FLOAT,"
            + MyDataEntry.GREEN_ENERGY_TIMER_COLUMN + " INTEGER,"
            + MyDataEntry.YELLOW_ENERGY_TIMER_COLUMN + " INTEGER,"
            + MyDataEntry.YELLOW_TIMER_COLUMN + " INTEGER" + ")";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyDataEntry.AVATAR_TABLE_NAME);
        onCreate(db);
    }

    public boolean saveAvatar(int ID, String aName, String aRB, float aTKN, int aTKD, float aCK, float aDK,
            int aPL, int aBP, int aST, float aGEP, float aYEP, int aGET, int aYET, int aYT) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyDataEntry.AVATAR_ID_COLUMN, ID);
        values.put(MyDataEntry.AVATAR_NAME_COLUMN, aName);
        values.put(MyDataEntry.AVATAR_RACE_COLUMN, aRB);
        values.put(MyDataEntry.TOTAL_KI_NUMERATOR_COLUMN, aTKN);
        values.put(MyDataEntry.TOTAL_KI_DENOMINATOR_COLUMN, aTKD);
        values.put(MyDataEntry.CURRENT_KI_COLUMN, aCK);
        values.put(MyDataEntry.DEFENSE_KI_COLUMN, aDK);
        values.put(MyDataEntry.POWER_LEVEL_COLUMN, aPL);
        values.put(MyDataEntry.BATTLE_POWER_COLUMN, aBP);
        values.put(MyDataEntry.STAMINA_COLUMN, aST);
        values.put(MyDataEntry.GREEN_ENERGY_PERCENT_COLUMN, aGEP);
        values.put(MyDataEntry.YELLOW_ENERGY_PERCENT_COLUMN, aYEP);
        values.put(MyDataEntry.GREEN_ENERGY_TIMER_COLUMN, aGET);
        values.put(MyDataEntry.YELLOW_ENERGY_TIMER_COLUMN, aYET);
        values.put(MyDataEntry.YELLOW_TIMER_COLUMN, aYT);


        db.insert(MyDataEntry.AVATAR_TABLE_NAME, null, values);
        return true;

    }

    public Cursor loadAvatar(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] query_columns = {
                MyDataEntry._ID,
                MyDataEntry.AVATAR_ID_COLUMN,
                MyDataEntry.AVATAR_NAME_COLUMN,
                MyDataEntry.AVATAR_RACE_COLUMN,
                MyDataEntry.TOTAL_KI_NUMERATOR_COLUMN,
                MyDataEntry.TOTAL_KI_DENOMINATOR_COLUMN,
                MyDataEntry.CURRENT_KI_COLUMN,
                MyDataEntry.DEFENSE_KI_COLUMN,
                MyDataEntry.POWER_LEVEL_COLUMN,
                MyDataEntry.BATTLE_POWER_COLUMN,
                MyDataEntry.STAMINA_COLUMN,
                MyDataEntry.GREEN_ENERGY_PERCENT_COLUMN,
                MyDataEntry.YELLOW_ENERGY_PERCENT_COLUMN,
                MyDataEntry.GREEN_ENERGY_TIMER_COLUMN,
                MyDataEntry.YELLOW_ENERGY_TIMER_COLUMN,
                MyDataEntry.YELLOW_TIMER_COLUMN
        };




        Cursor cursor = db.query(

                MyDataEntry.AVATAR_TABLE_NAME,
                query_columns,
                MyDataEntry.AVATAR_ID_COLUMN + " = " + id,                           //https://youtu.be/MDdkdsG-Yww
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public int deleteAvatar(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MyDataEntry.AVATAR_TABLE_NAME, "_ID = ?", new String[] { Integer.toString(id) });
    }

}
