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
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class FragmentTasks extends Fragment {

    View view;

    static ArrayList<String> tasks;
    static ListView taskList;
    static ArrayAdapter<String> arrayAdapterTasks;
    static SQLiteDatabase tasksDataBase;
    static Popup popup = new TaskPopup();



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

                FragmentActivity currActivity = getActivity();
                popup.showPopup(position, currActivity);


                return true;
            }
        });
        return view;
    }


    public static void addTask(String newTask, int coins) {
        tasksDataBase.execSQL("INSERT INTO tasks (task, coins) VALUES ('"+ newTask +"', '"+ coins +"')");
        tasks.add(Integer.toString(coins) + " - " + newTask);
        taskList.setAdapter(arrayAdapterTasks);

    }

}
