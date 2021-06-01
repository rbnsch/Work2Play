package com.example.work2play;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.work2play.helper.DatabaseHelper;



class RewardPopup extends Fragment {
    DatabaseHelper db;

    public void showPopup(final int position, FragmentActivity currActivity, DatabaseHelper db){
        this.db = db;
        View popupView = LayoutInflater.from(currActivity).inflate(R.layout.reward_popup_window, null);
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
        db.deleteReward(FragmentRewards.allRewards.get(position).getId());
        /*
        String deleteEntry = FragmentRewards.rewards.get(position);


        String[] seperatedEntry = deleteEntry.split(" - ");
        Log.i("Delete", seperatedEntry[1] + " nene");

        FragmentRewards.rewardsDataBase.execSQL("DELETE FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')");

         */
        FragmentRewards.rewards.remove(position);

        FragmentRewards.rewardList.setAdapter(FragmentRewards.arrayAdapterRewards);

    }

    public void getCoins(int position){
        String newCoinsString = FragmentRewards.rewards.get(position).substring(0, 2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        if(coins < newCoins) {

            Toast.makeText(getActivity(), "Nicht genug Coins", Toast.LENGTH_LONG).show();

        } else {
            coins -= newCoins;
            MainActivity.setCoins(coins);
            String deleteEntry = FragmentRewards.rewards.get(position);
            String[] seperatedEntry = deleteEntry.split(" - ");
            Cursor c = FragmentRewards.rewardsDataBase.rawQuery("SELECT multiple FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')", null);
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


}
