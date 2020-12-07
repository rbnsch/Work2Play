package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


    public void gotoTasks (View view) {
        finish();
    }

    public void gotoAddReward(View view) {
        Intent reward = new Intent(getApplicationContext(), AddRewards.class);
        startActivity(reward);
    }

    public static void addReward(String newTask, int coins) {
        rewards.add(Integer.toString(coins) + " - " + newTask);
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


        rewards.add("10 - TEst");
        rewards.add("30 - Jo");
        rewards.add("05 - ha");




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
                rewards.remove(position);

                rewardList.setAdapter(arrayAdapterRewards);
                return true;
            }
        });



    }
}