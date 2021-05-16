package com.example.work2play;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class FragmentRewards extends Fragment {

    View view;

    static ArrayList<String> rewards;
    static ListView rewardList;
    static ArrayAdapter<String> arrayAdapterRewards;
    static SQLiteDatabase rewardsDataBase;
    static Popup popup = new RewardPopup();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_rewards, container, false);

        rewardList = view.findViewById(R.id.listRewards);
        rewards = new ArrayList<String>();

        rewardsDataBase = getActivity().openOrCreateDatabase("rewards", Context.MODE_PRIVATE, null);
        rewardsDataBase.execSQL("CREATE TABLE IF NOT EXISTS rewards (reward VARCHAR, coins INT(2), multiple INT(1))");

        Cursor c = rewardsDataBase.rawQuery("SELECT * FROM rewards", null);

        int taskIndex = c.getColumnIndex("reward");
        int coinsIndex = c.getColumnIndex("coins");



        if(c.moveToFirst()){
            do{
                rewards.add(c.getString(coinsIndex) + " - " + c.getString(taskIndex));
            }
            while (c.moveToNext());
        }

        arrayAdapterRewards = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, rewards);
        rewardList.setAdapter(arrayAdapterRewards);




        rewardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }

        });

        rewardList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentActivity currActivity = getActivity();
                popup.showPopup(position, currActivity);
              return true;
            }
        });
        return view;
    }


    public static void addReward(String newReward, int coins, boolean multiple) {
        Log.i("Multiple:", Boolean.toString(multiple));
        int mu = (multiple) ? 1 : 0;
        rewardsDataBase.execSQL("INSERT INTO rewards (reward, coins, multiple) VALUES ('"+ newReward +"', '"+ coins + "', '" + mu +"')");
        rewards.add(Integer.toString(coins) + " - " + newReward);
        rewardList.setAdapter(arrayAdapterRewards);
    }

}
