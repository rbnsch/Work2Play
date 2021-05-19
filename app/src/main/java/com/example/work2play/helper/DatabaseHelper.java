package com.example.work2play.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "workPlay";

    private static final String TABLE_TASK = "tasks";
    private static final String TABLE_REWARD = "rewards";

    private static final String KEY_ID = "id";
    private static final String KEY_COINS = "coins";
    private static final String KEY_TITEL = "titel";
    private static final String KEY_REPEATABLE = "repeatable";

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_REPEAT_FREQUENCY = "repeatFrequency";
    private static final String KEY_PROJECT = "project";
    private static final String KEY_DEADLINE = "deadline";


    private static final String CREATE_TABLE_REWARD = "CREATE TABLE "
            + TABLE_REWARD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITEL
            + " TEXT," + KEY_COINS + " INTEGER," + KEY_REPEATABLE + " TEXT" + ")";

    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITEL + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_COINS + " INTEGER,"
            + KEY_REPEATABLE + " TEXT,"
            + KEY_REPEAT_FREQUENCY + " TEXT,"
            + KEY_PROJECT + " INTEGER,"
            + KEY_DEADLINE + " DATETIME"
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_REWARD);
        db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REWARD);

        onCreate(db);
    }
}
