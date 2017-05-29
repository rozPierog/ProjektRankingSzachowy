package com.example.piotr.rankingszachowy.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcin Omelan on 29.05.2017.
 */

public class TournamentDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Tournament.db";
    private static final String TABLE_NAME = "tournaments_table";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STARTS_AT = "STARTS_AT";


    public TournamentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " +
                DESCRIPTION + " TEXT, " + STARTS_AT + " TEXT);";

        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getSpecificData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME +" LIKE '"+ s+"%'", null);
    }

    public boolean insertData(String name, String description, String starts_at) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(STARTS_AT, starts_at);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
