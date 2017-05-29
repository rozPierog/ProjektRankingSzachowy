package com.example.piotr.rankingszachowy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcin Omelan on 29.05.2017.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "users_table";
    public static final String ID = "ID";
    public static final String USERNAME = "USERNAME";
    public static final String RANK = "RANK";
    public static final String LAST_PLAYED = "LAST_PLAYED";
    public static final String PLAYING_SINCE = "PLAYING_SINCE";
    public static final String AGE = "AGE";


    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " +
                RANK + " TEXT, " + LAST_PLAYED +" TEXT, "+ PLAYING_SINCE +" TEXT, "+
                AGE +" INTEGER);";

        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }

    public boolean insertData(String username, String rank, String lastPlayed, String playingSince,
                              String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(RANK, rank);
        contentValues.put(LAST_PLAYED, lastPlayed);
        contentValues.put(PLAYING_SINCE, playingSince);
        contentValues.put(AGE, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
