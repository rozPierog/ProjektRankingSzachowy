package com.example.piotr.rankingszachowy.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONObject;

/**
 * Created by Marcin Omelan on 29.05.2017.
 */

public class GameDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Chessss.db";
    private static final String TABLE_NAME = "gamess_table";
    private static final String ID = "ID";
    private static final String WHITE_PLAYER = "WHITE_PLAYER";
    private static final String BLACK_PLAYER = "BLACK_PLAYER";
    private static final String WHITE_POINTS = "WHITE_POINTS";
    private static final String BLACK_POINTS = "BLACK_POINTS";
    private static final String DATE = "DATE";
    private static final String MOVE_LIST = "MOVE_LIST";
    private static final String TOURNAMENT_ID = "TOURNAMENT_ID";


    public GameDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + WHITE_PLAYER + " TEXT, " +
                BLACK_PLAYER + " TEXT, " + WHITE_POINTS + " TEXT, " +
                BLACK_POINTS + " TEXT, " + DATE + " TEXT, " + MOVE_LIST
                + " TEXT, " + TOURNAMENT_ID + " TEXT);";

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
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +" JOIN " + " WHERE " + WHITE_PLAYER +" LIKE '"+ s+"%'", null);
    }

    public boolean insertData(String white_player, String white_points, String black_player,
                              String black_points, String date, String move_list,
                              String tournament_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WHITE_PLAYER, white_player);
        contentValues.put(BLACK_PLAYER, black_player);
        contentValues.put(WHITE_POINTS, white_points);
        contentValues.put(BLACK_POINTS, black_points);
        contentValues.put(DATE, date);
        contentValues.put(MOVE_LIST, move_list);
        contentValues.put(TOURNAMENT_ID, tournament_id);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
