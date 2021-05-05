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

import java.util.ArrayList;

public class FragmentRewards extends Fragment {

    View view;

    static ArrayList<String> rewards;
    static ListView rewardList;
    static ArrayAdapter<String> arrayAdapterRewards;
    static SQLiteDatabase rewardsDataBase;

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
              showPopup(position);
              return true;
            }
        });
        return view;
    }

    public void showPopup(final int position){
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.reward_popup_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        popupWindow.showAsDropDown(popupView, 0, 0);

        Button dismissButton=(Button)popupView.findViewById(R.id.dismissButton);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button buyButton=(Button)popupView.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCoins(position);
                popupWindow.dismiss();
            }
        });

        Button deleteButton=(Button)popupView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                popupWindow.dismiss();
            }
        });

    }

    public void deleteItem(int position){
        String deleteEntry = rewards.get(position);
        String[] seperatedEntry = deleteEntry.split(" - ");
        Log.i("Delete", seperatedEntry[1] + " nene");

        rewardsDataBase.execSQL("DELETE FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')");
        rewards.remove(position);

        rewardList.setAdapter(arrayAdapterRewards);

    }

    public void getCoins(int position){
        String newCoinsString = rewards.get(position).substring(0, 2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        if(coins < newCoins) {

            Toast.makeText(getActivity(), "Nicht genug Coins", Toast.LENGTH_LONG).show();

        } else {
            coins -= newCoins;
            MainActivity.setCoins(coins);
            String deleteEntry = rewards.get(position);
            String[] seperatedEntry = deleteEntry.split(" - ");
            Cursor c = rewardsDataBase.rawQuery("SELECT multiple FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')", null);
            c.moveToFirst();
            String mul = "0";
            int multipleIndex = c.getColumnIndex("multiple");

            if(c.moveToFirst()){
                do{
                    mul = c.getString(multipleIndex);
                }
                while (c.moveToNext());
            }

            int temp = Integer.parseInt(mul);
            boolean multiple = (temp == 1);

            Log.i("multiple", String.valueOf(multiple));
        }

    }

    public static void addReward(String newReward, int coins, boolean multiple) {
        Log.i("Multiple:", Boolean.toString(multiple));
        int mu = (multiple) ? 1 : 0;
        rewardsDataBase.execSQL("INSERT INTO rewards (reward, coins, multiple) VALUES ('"+ newReward +"', '"+ coins + "', '" + mu +"')");
        rewards.add(Integer.toString(coins) + " - " + newReward);
        rewardList.setAdapter(arrayAdapterRewards);
    }

}
