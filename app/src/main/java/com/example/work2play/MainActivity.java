package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static int coins = 0;

    static SharedPreferences savedCoins;


    static TextView coinsTextTasks;

    static ArrayList<String> tasks;
    static ListView taskList;
    static ArrayAdapter<String> arrayAdapterTasks;
    static SQLiteDatabase tasksDataBase;



    public void gotoShop(View view) {
        Intent shop = new Intent(getApplicationContext(), ShopActivity.class);
        startActivity(shop);
    }


    public void gotoAddTask(View view) {
        Intent add = new Intent(getApplicationContext(), AddTasks.class);
        startActivity(add);
    }

    public static void addTask(String newTask, int coins) {
        tasksDataBase.execSQL("INSERT INTO tasks (task, coins) VALUES ('"+ newTask +"', '"+ coins +"')");
        tasks.add(Integer.toString(coins) + " - " + newTask);
        taskList.setAdapter(arrayAdapterTasks);

    }

    public static void setCoins() {
        coins = savedCoins.getInt("coins", 0);
        coinsTextTasks.setText(String.valueOf(coins) + " Coins");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ---------------------------------------------
                        Tab Layout
        ---------------------------------------------
         */

        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabHabits = findViewById(R.id.tabHabits);
        TabItem tabTasks = findViewById(R.id.tabTasks);
        TabItem tabRewards = findViewById(R.id.tabRewards);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*
        ---------------------------------------------
                     Tab Layout Ende
        ---------------------------------------------
         */
        /*
        savedCoins = this.getSharedPreferences("com.example.work2play", Context.MODE_PRIVATE);

        coinsTextTasks = (TextView) findViewById(R.id.tasksCoins);
        tasks = new ArrayList<String>();
        taskList = findViewById(R.id.listTasks);



        coins = savedCoins.getInt("coins", 0);
        coinsTextTasks.setText(String.valueOf(coins) + " Coins");

        tasksDataBase = this.openOrCreateDatabase("tasks", MODE_PRIVATE, null);
        //erstellt Datenbank, wenn nicht vorhanden
        tasksDataBase.execSQL("CREATE TABLE IF NOT EXISTS tasks (task VARCHAR, coins INT(2))");

        Cursor c = tasksDataBase.rawQuery("SELECT * FROM tasks", null);

        int taskIndex = c.getColumnIndex("task");
        int coinsIndex = c.getColumnIndex("coins");

        //tasksDataBase.execSQL("INSERT INTO tasks (task, coins) VALUES ('Test', 33)");

        if(c.moveToFirst()){
            do{
                tasks.add(c.getString(coinsIndex) + " - " + c.getString(taskIndex));
            }
            while (c.moveToNext());
        }


        arrayAdapterTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(arrayAdapterTasks);


        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String newCoinsString = tasks.get(i).substring(0, 2);

                int newCoins = Integer.parseInt(newCoinsString);

                coins += newCoins;

                savedCoins.edit().putInt("coins", coins).apply();


                //savedCoins.getInt("coins", 0);

                //coinsTextTasks.setText(String.valueOf(coins) + " Coins");

                coinsTextTasks.setText(String.valueOf(savedCoins.getInt("coins", 0)) + " Coins");

            }

        });


        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deleteEntry = tasks.get(position);
                String[] seperatedEntry = deleteEntry.split(" - ");
                Log.i("Delete", seperatedEntry[1] + " nene");

                tasksDataBase.execSQL("DELETE FROM tasks WHERE task = ('" + seperatedEntry[1] + "')");
                tasks.remove(position);

                taskList.setAdapter(arrayAdapterTasks);
                return true;
            }
        });

*/


    }
}