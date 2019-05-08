package com.example.techlabapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInfo extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HRData.db";
    public static final String TABLE_NAME = "User_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NICKNAME";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "SURNAME";
    public static final String COL_5 = "CLASS";
    public static final String COL_6 = "BORN";
    public static final String COL_7 = "EMAIL";
    public static final String COL_8 = "PHONE";
    public static final String COL_9 = "BORROWING";
    public static final String COL_10 = "RESERVED";


    public UserInfo(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NICKNAME TEXT, NAME TEXT, SURNAME TEXT, CLASS TEXT, BORN TEXT, EMAIL TEXT, PHONE INTEGER, BORROWING TEXT, RESERVED TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
