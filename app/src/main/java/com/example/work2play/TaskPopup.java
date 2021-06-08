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


class TaskPopup extends Fragment{
    DatabaseHelper db;

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