package com.example.work2play.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.work2play.model.Reward;

import java.util.ArrayList;
import java.util.List;

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
    private static final String KEY_TITLE = "title";
    private static final String KEY_REPEATABLE = "repeatable";

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_REPEAT_FREQUENCY = "repeatFrequency";
    private static final String KEY_PROJECT = "project";
    private static final String KEY_DEADLINE = "deadline";


    private static final String CREATE_TABLE_REWARD = "CREATE TABLE "
            + TABLE_REWARD + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITLE + " TEXT,"
            + KEY_COINS + " INTEGER,"
            + KEY_REPEATABLE + " TEXT" + ")";

    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITLE + " TEXT,"
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

    public long createReward(Reward reward) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, reward.getTitle());
        values.put(KEY_COINS, reward.getCoins());
        values.put(KEY_REPEATABLE, reward.getRepeatable());


        return db.insert(TABLE_REWARD, null, values);
    }

    public List<Reward> getAllRewards() {
        List<Reward> rewards = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REWARD;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Reward rw = new Reward();
                rw.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                rw.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                rw.setCoins(c.getInt(c.getColumnIndex(KEY_COINS)));
                rw.setRepeatable(c.getInt(c.getColumnIndex(KEY_REPEATABLE)));
                rewards.add(rw);
            } while (c.moveToNext());
        }

        return rewards;
    }

    public void deleteReward(long reward_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REWARD, KEY_ID + " = ?", new String[] {String.valueOf(reward_id)});
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

}
