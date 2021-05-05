package com.example.work2play;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentTasks extends Fragment {

    View view;

    static ArrayList<String> tasks;
    static ListView taskList;
    static ArrayAdapter<String> arrayAdapterTasks;
    static SQLiteDatabase tasksDataBase;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_tasks, container, false);

        taskList = view.findViewById(R.id.listTasks);
        tasks = new ArrayList<String>();

        tasksDataBase = getActivity().openOrCreateDatabase("tasks", Context.MODE_PRIVATE, null);
        tasksDataBase.execSQL("CREATE TABLE IF NOT EXISTS tasks (task VARCHAR, coins INT(2))");

        Cursor c = tasksDataBase.rawQuery("SELECT * FROM tasks", null);

        int taskIndex = c.getColumnIndex("task");
        int coinsIndex = c.getColumnIndex("coins");

        if(c.moveToFirst()){
            do{
                tasks.add(c.getString(coinsIndex) + " - " + c.getString(taskIndex));
            } while (c.moveToNext());
        }

        arrayAdapterTasks = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(arrayAdapterTasks);



        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                finishTask(position);
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
        String deleteEntry = tasks.get(position);
        String[] seperatedEntry = deleteEntry.split(" - ");

        tasksDataBase.execSQL("DELETE FROM tasks WHERE task = ('" + seperatedEntry[1] + "')");
        tasks.remove(position);

        taskList.setAdapter(arrayAdapterTasks);
    }

    public void finishTask(int position){
        String newCoinsString = tasks.get(position).substring(0,2);

        int newCoins = Integer.parseInt(newCoinsString);

        int coins = MainActivity.getCoins();
        MainActivity.setCoins(coins + newCoins);
    }

    public static void addTask(String newTask, int coins) {
        tasksDataBase.execSQL("INSERT INTO tasks (task, coins) VALUES ('"+ newTask +"', '"+ coins +"')");
        tasks.add(Integer.toString(coins) + " - " + newTask);
        taskList.setAdapter(arrayAdapterTasks);

    }

}
