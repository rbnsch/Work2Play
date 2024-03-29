package com.example.work2play;


import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.work2play.helper.DatabaseHelper;


interface Popup{

    public void showPopup(final int position, FragmentActivity currActivity, DatabaseHelper db);


}

class TaskPopup extends Fragment implements Popup{

    private DatabaseHelper db;

    public void showPopup(final int position, FragmentActivity currActivity, DatabaseHelper db) {
        this.db = db;

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
        db.deleteTask(FragmentTasks.allTasks.get(position).getId());

        FragmentTasks.reloadTaskListView();

    }


    public void finishTask(int position){
        String newCoinsString = FragmentTasks.tasks.get(position).substring(0,2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        MainActivity.setCoins(coins + newCoins);
    }
}

class HabitPopup extends Fragment implements Popup{

    private DatabaseHelper db;

    public void showPopup(final int position, final FragmentActivity currActivity, DatabaseHelper db) {

        this.db = db;
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
        db.deleteHabit(FragmentHabits.habits.get(position).getId());
        FragmentHabits.reloadHabitListView();


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
        db.updateHabit(currHabit);
        FragmentHabits.habitListAdapter.notifyDataSetChanged();

        int coins = MainActivity.getCoins();
        MainActivity.setCoins(coins + newCoins);
    }
}

class RewardPopup extends Fragment implements Popup {

    private DatabaseHelper db;

    public void showPopup(final int position, final FragmentActivity currActivity, DatabaseHelper db){
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
        db.deleteReward(FragmentRewards.allRewards.get(position).getId());

        FragmentRewards.reloadRewardListView();


    }

    public void getCoins(int position, FragmentActivity currActivity){
        String newCoinsString = FragmentRewards.rewards.get(position).substring(0, 2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        if(coins < newCoins) {

            Toast.makeText(currActivity, "Nicht genug Coins", Toast.LENGTH_LONG).show();

        } else {
            coins -= newCoins;
            MainActivity.setCoins(coins);

            if (FragmentRewards.allRewards.get(position).getRepeatable() != 1) {
                deleteItem(position);
            }

        }

    }


}
