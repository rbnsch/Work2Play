package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.work2play.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static int coins = 0;

    private static SharedPreferences savedCoins;
    private static TextView coinsText;


    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int newCoins) {
        coins = newCoins;
        coinsText.setText(String.valueOf(coins) + " Coins");
        savedCoins.edit().putInt("coins", coins).apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        savedCoins = this.getSharedPreferences("com.example.work2play", Context.MODE_PRIVATE);
        coinsText = (TextView) findViewById(R.id.coins);

        coins = savedCoins.getInt("coins", 0);
        coinsText.setText(String.valueOf(coins) + " Coins");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Log.i("Delete", shownTab + " nene");
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        Intent addHabit = new Intent(getApplicationContext(), AddHabits.class);
                        startActivity(addHabit);
                        break;
                    case 1:
                        Intent addTask = new Intent(getApplicationContext(), AddTasks.class);
                        startActivity(addTask);
                        break;
                    case 2:
                        Intent addReward = new Intent(getApplicationContext(), AddRewards.class);
                        startActivity(addReward);
                        break;
                    default:
                        break;
                }



            }
        });

    }


}