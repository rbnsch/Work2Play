package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    int coins = 0;

    SharedPreferences savedCoins;

    TextView coinsTextRewards;


    static ArrayList<String> rewards;
    static ListView rewardList;
    static ArrayAdapter<String> arrayAdapterRewards;
    static SQLiteDatabase rewardsDataBase;

    public void gotoTasks (View view) {
        finish();
    }

    public void gotoAddReward(View view) {
        Intent reward = new Intent(getApplicationContext(), AddRewards.class);
        startActivity(reward);
    }

    public static void addReward(String newReward, int coins) {
        rewardsDataBase.execSQL("INSERT INTO rewards (reward, coins) VALUES ('"+ newReward +"', '"+ coins +"')");
        rewards.add(Integer.toString(coins) + " - " + newReward);
        rewardList.setAdapter(arrayAdapterRewards);

    }





    public void notEnougthCoins () {
        Toast.makeText(this, "Nicht genügend Coins", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        savedCoins = this.getSharedPreferences("com.example.work2play", Context.MODE_PRIVATE);


        coinsTextRewards = (TextView) findViewById(R.id.shopCoins);


        coins = savedCoins.getInt("coins", 0);
        coinsTextRewards.setText(String.valueOf(coins) + " Coins");


        rewardList = findViewById(R.id.listRewards);


        rewards = new ArrayList<String>();

        coins = savedCoins.getInt("coins", 0);


        rewardsDataBase = this.openOrCreateDatabase("rewards", MODE_PRIVATE, null);
        rewardsDataBase.execSQL("CREATE TABLE IF NOT EXISTS rewards (reward VARCHAR, coins INT(2))");

        Cursor c = rewardsDataBase.rawQuery("SELECT * FROM rewards", null);

        int taskIndex = c.getColumnIndex("reward");
        int coinsIndex = c.getColumnIndex("coins");

        //rewardsDataBase.execSQL("INSERT INTO rewards (reward, coins) VALUES ('Test', 33)");

        if(c.moveToFirst()){
            do{
                rewards.add(c.getString(coinsIndex) + " - " + c.getString(taskIndex));
            }
            while (c.moveToNext());
        }



        arrayAdapterRewards = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rewards);
        rewardList.setAdapter(arrayAdapterRewards);



        rewardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String newCoinsString = rewards.get(i).substring(0, 2);

                int newCoins = Integer.parseInt(newCoinsString);


                if(coins < newCoins) {
                    notEnougthCoins();
                    //Toast.makeText(this, "Nicht genug Coins", Toast.LENGTH_LONG).show();

                } else {
                    coins -= newCoins;
                    savedCoins.edit().putInt("coins", coins).apply();
                    MainActivity.setCoins();
                    coinsTextRewards.setText(String.valueOf(savedCoins.getInt("coins", 0)) + " Coins");
                }
            }

        });


        rewardList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deleteEntry = rewards.get(position);
                String[] seperatedEntry = deleteEntry.split(" - ");
                Log.i("Delete", seperatedEntry[1] + " nene");

                rewardsDataBase.execSQL("DELETE FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')");
                rewards.remove(position);

                rewardList.setAdapter(arrayAdapterRewards);
                return true;
            }
        });



    }
}