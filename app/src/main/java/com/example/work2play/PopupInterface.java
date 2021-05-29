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



interface Popup{

    public void showPopup(final int position, FragmentActivity currActivity);


}

class TaskPopup extends Fragment implements Popup{

    public void showPopup(final int position, FragmentActivity currActivity) {

        View popupView = LayoutInflater.from(currActivity).inflate(R.layout.task_popup_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        popupWindow.showAsDropDown(popupView, 0, 0);

        Button dismissButton = (Button) popupView.findViewById(R.id.dismissButton);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button finishButton = (Button) popupView.findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTask(position);
                popupWindow.dismiss();
            }
        });

        Button deleteButton = (Button) popupView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                popupWindow.dismiss();
            }
        });


    }

    public void deleteItem(int position){
        String deleteEntry = FragmentTasks.tasks.get(position);
        String[] seperatedEntry = deleteEntry.split(" - ");

        FragmentTasks.tasksDataBase.execSQL("DELETE FROM tasks WHERE task = ('" + seperatedEntry[1] + "')");
        FragmentTasks.tasks.remove(position);

        FragmentTasks.taskList.setAdapter(FragmentTasks.arrayAdapterTasks);
    }

    public void finishTask(int position){
        String newCoinsString = FragmentTasks.tasks.get(position).substring(0,2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        MainActivity.setCoins(coins + newCoins);
    }
}

class HabitPopup extends Fragment implements Popup{

    public void showPopup(final int position, final FragmentActivity currActivity) {

        View popupView = LayoutInflater.from(currActivity).inflate(R.layout.habit_popup_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        popupWindow.showAsDropDown(popupView, 0, 0);

        Button dismissButton = (Button) popupView.findViewById(R.id.dismissButton);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button finishButton = (Button) popupView.findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishHabit(position, currActivity);
                popupWindow.dismiss();
            }
        });

        Button deleteButton = (Button) popupView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                popupWindow.dismiss();
            }
        });



    }

    public void deleteItem(int position){

        FragmentHabits.habits.set(position, null);
        FragmentHabits.habits.remove(position);

        FragmentHabits.habitList.setAdapter(FragmentHabits.habitListAdapter);

    }

    public void finishHabit(int position, FragmentActivity currActivity){
        HabitDataHelper currHabit = FragmentHabits.habits.get(position);
        int newCoins = 0;

        if (currHabit.getNumberRepDone() < (currHabit.getNumberRep() - 1)){
            newCoins = currHabit.getCoinsOne();
            currHabit.setNumberRepDone(currHabit.getNumberRepDone() + 1);
        }
        else if(currHabit.getNumberRepDone() == (currHabit.getNumberRep() - 1) ){
            newCoins = currHabit.getCoinsOne() + currHabit.getCoinsAll();
            currHabit.setNumberRepDone(currHabit.getNumberRepDone() + 1);
        }
        else {
            Toast.makeText(currActivity, "Already Finished", Toast.LENGTH_LONG).show();
        }

        FragmentHabits.habitListAdapter.notifyDataSetChanged();

        int coins = MainActivity.getCoins();
        MainActivity.setCoins(coins + newCoins);
    }
}

class RewardPopup extends Fragment implements Popup {
    public void showPopup(final int position, final FragmentActivity currActivity){
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
                getCoins(position, currActivity);
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
        String deleteEntry = FragmentRewards.rewards.get(position);
        String[] seperatedEntry = deleteEntry.split(" - ");
        Log.i("Delete", seperatedEntry[1] + " nene");

        FragmentRewards.rewardsDataBase.execSQL("DELETE FROM rewards WHERE reward = ('" + seperatedEntry[1] + "')");
        FragmentRewards.rewards.remove(position);

        FragmentRewards.rewardList.setAdapter(FragmentRewards.arrayAdapterRewards);

    }

    public void     getCoins(int position, FragmentActivity currActivity){
        String newCoinsString = FragmentRewards.rewards.get(position).substring(0, 2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        if(coins < newCoins) {

            Toast.makeText(currActivity, "Nicht genug Coins", Toast.LENGTH_LONG).show();

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
