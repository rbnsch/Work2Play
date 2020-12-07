package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static int coins = 0;

    static SharedPreferences savedCoins;


    static TextView coinsTextTasks;

    static ArrayList<String> tasks;
    static ListView taskList;
    static ArrayAdapter<String> arrayAdapterTasks;



    public void gotoShop(View view) {
        Intent shop = new Intent(getApplicationContext(), ShopActivity.class);
        startActivity(shop);
    }


    public void gotoAddTask(View view) {
        Intent add = new Intent(getApplicationContext(), AddTasks.class);
        startActivity(add);
    }

    public static void addTask(String newTask, int coins) {
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



        savedCoins = this.getSharedPreferences("com.example.work2play", Context.MODE_PRIVATE);

        coinsTextTasks = (TextView) findViewById(R.id.tasksCoins);
        tasks = new ArrayList<String>();
        taskList = findViewById(R.id.listTasks);



        coins = savedCoins.getInt("coins", 0);
        coinsTextTasks.setText(String.valueOf(coins) + " Coins");




        tasks.add("10 - Lernen");
        tasks.add("05 - Kochen");
        tasks.add("20 - Hausaufgaben");



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
                tasks.remove(position);

                taskList.setAdapter(arrayAdapterTasks);
                return true;
            }
        });




    }
}