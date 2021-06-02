package com.example.work2play.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.work2play.model.Habit;
import com.example.work2play.model.Project;
import com.example.work2play.model.Reward;
import com.example.work2play.model.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "name", null, 1);
    }

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "workPlay";

    private static final String TABLE_TASK = "tasks";
    private static final String TABLE_REWARD = "rewards";
    private static final String TABLE_PROJECT = "projects";
    private static final String TABLE_HABIT = "habits";

    private static final String KEY_ID = "id";
    private static final String KEY_COINS = "coins";
    private static final String KEY_TITLE = "title";
    private static final String KEY_REPEATABLE = "repeatable";

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_REPEAT_FREQUENCY = "repeatFrequency";
    private static final String KEY_PROJECT = "project";
    private static final String KEY_DEADLINE = "deadline";

    private static final String KEY_COINS_ONE = "coinsOne";
    private static final String KEY_COINS_ALL = "coinsAll";
    private static final String KEY_NUMBER_REP = "numberRep";
    private static final String KEY_NUMBER_REP_DONE = "numberRepDone";


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


    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE "
            + TABLE_REWARD + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITLE + " TEXT"
            + ")";

    private static final String CREATE_TABLE_HABIT = "CREATE TABLE "
            + TABLE_HABIT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITLE + " TEXT,"
            + KEY_COINS_ONE + " INTEGER,"
            + KEY_COINS_ALL + " INTEGER,"
            + KEY_NUMBER_REP + " INTEGER,"
            + KEY_NUMBER_REP_DONE + " INTEGER"
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_REWARD);
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_PROJECT);
        db.execSQL(CREATE_TABLE_HABIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REWARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HABIT);

        onCreate(db);
    }


    /*
    -------------------------------------

    REWARD TABLE

    -------------------------------------
     */

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







        /*
    -------------------------------------

    TASK TABLE

    -------------------------------------
     */

    public long createTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_COINS, task.getCoins());
        values.put(KEY_REPEATABLE, task.getRepeatable());
        values.put(KEY_REPEAT_FREQUENCY, task.getRepeatFrequency());
        values.put(KEY_PROJECT, task.getProjectId());
        values.put(KEY_DEADLINE, task.getDeadlineDate());


        return db.insert(TABLE_TASK, null, values);
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TASK;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Task ts = new Task();
                ts.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                ts.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                ts.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                ts.setCoins(c.getInt(c.getColumnIndex(KEY_COINS)));
                ts.setRepeatable(c.getInt(c.getColumnIndex(KEY_REPEATABLE)));
                ts.setRepeatFrequency(c.getString(c.getColumnIndex(KEY_REPEAT_FREQUENCY)));
                ts.setProjectId(c.getInt(c.getColumnIndex(KEY_PROJECT)));
                ts.setDeadlineDate(c.getString(c.getColumnIndex(KEY_DEADLINE)));
                tasks.add(ts);
            } while (c.moveToNext());
        }

        return tasks;
    }

    public void deleteTask(long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?", new String[] {String.valueOf(task_id)});
    }








        /*
    -------------------------------------

    PROJECT TABLE

    -------------------------------------
     */

    public long createProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, project.getTitle());


        return db.insert(TABLE_REWARD, null, values);
    }


    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PROJECT;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Project pr = new Project();
                pr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                pr.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                projects.add(pr);
            } while (c.moveToNext());
        }

        return projects;
    }

    public void deleteProject(long project_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECT, KEY_ID + " = ?", new String[] {String.valueOf(project_id)});
    }









        /*
    -------------------------------------

    HABIT TABLE

    -------------------------------------
     */


    public long createHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, habit.getTitle());
        values.put(KEY_COINS_ONE, habit.getCoinsOne());
        values.put(KEY_COINS_ALL, habit.getCoinsAll());
        values.put(KEY_NUMBER_REP, habit.getNumberRep());
        values.put(KEY_NUMBER_REP_DONE, habit.getNumberRepDone());


        return db.insert(TABLE_HABIT, null, values);
    }

    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_HABIT;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Habit hb = new Habit();
                hb.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                hb.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                hb.setCoinsOne(c.getInt(c.getColumnIndex(KEY_COINS_ONE)));
                hb.setCoinsAll(c.getInt(c.getColumnIndex(KEY_COINS_ALL)));
                hb.setNumberRep(c.getInt(c.getColumnIndex(KEY_NUMBER_REP)));
                hb.setNumberRepDone(c.getInt(c.getColumnIndex(KEY_NUMBER_REP_DONE)));
                habits.add(hb);
            } while (c.moveToNext());
        }

        return habits;
    }

    public void deleteHabit(long habit_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABIT, KEY_ID + " = ?", new String[] {String.valueOf(habit_id)});
    }



















    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

}
