package com.example.piotr.rankingszachowy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcin Omelan on 29.05.2017.
 */

class UserDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Users.db";
    private static final String TABLE_NAME = "users_table";
    private static final String ID = "ID";
    private static final String USERNAME = "USERNAME";
    private static final String RANK = "RANK";
    private static final String LAST_PLAYED = "LAST_PLAYED";
    private static final String PLAYING_SINCE = "PLAYING_SINCE";
    private static final String AGE = "AGE";


    UserDBHelper(Context context) {
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

    Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean insertData(String username, String rank, String lastPlayed, String playingSince,
                              String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(RANK, rank);
        contentValues.put(LAST_PLAYED, lastPlayed);
        contentValues.put(PLAYING_SINCE, playingSince);
        contentValues.put(AGE, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
