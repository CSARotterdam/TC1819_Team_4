package com.example.techlabapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_STUDNUM = "studNum";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_RANK = "rank";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table "+TABLE_NAME+" ("+COLUMN_STUDNUM+" integer primary key not null auto_increment , " +
        COLUMN_EMAIL+" text not null , "+COLUMN_PASS+" text not null , "+COLUMN_RANK+" text not null)";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
