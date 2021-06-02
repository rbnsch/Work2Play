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
import com.example.work2play.helper.DatabaseHelper;

import com.example.work2play.model.Reward;

import java.util.ArrayList;
import java.util.List;

public class FragmentRewards extends Fragment {

    private static DatabaseHelper db;
    View view;

    static ArrayList<String> rewards;
    static ListView rewardList;
    static ArrayAdapter<String> arrayAdapterRewards;
    static SQLiteDatabase rewardsDataBase;
    static RewardPopup popup = new RewardPopup();
    static List<Reward> allRewards;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rewards, container, false);

        rewardList = view.findViewById(R.id.listRewards);
        rewards = new ArrayList<String>();

        db = new DatabaseHelper(getActivity().getApplicationContext());



        allRewards = db.getAllRewards();
        for (Reward reward : allRewards) {
            rewards.add(reward.getCoins() + " - " + reward.getTitle());
            Log.i("Index", Long.toString(reward.getId()));
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
                popup.showPopup(position, currActivity, db);
              return true;
            }
        });
        return view;
    }


    public static void addReward(String newReward, int coins, boolean multiple) {
        Log.i("Multiple:", Boolean.toString(multiple));
        int mu = (multiple) ? 1 : 0;
        Reward reward = new Reward();
        reward.setTitle(newReward);
        reward.setCoins(coins);
        reward.setRepeatable(mu);
        db.createReward(reward);
        reloadRewardListView();

    }

    public static void reloadRewardListView () {
        allRewards = db.getAllRewards();
        rewards.clear();
        for (Reward reward : allRewards) {
            rewards.add(reward.getCoins() + " - " + reward.getTitle());
            Log.i("Index", Long.toString(reward.getId()));
        }

        rewardList.setAdapter(arrayAdapterRewards);
    }




}
