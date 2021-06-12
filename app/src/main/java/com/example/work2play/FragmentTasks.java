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
import com.example.work2play.helper.DatabaseHelper;
import com.example.work2play.model.Reward;
import com.example.work2play.model.Task;


import java.util.ArrayList;
import java.util.List;

public class FragmentTasks extends Fragment {

    private static DatabaseHelper db;
    View view;

    static ArrayList<String> tasks;
    static ListView taskList;
    static ArrayAdapter<String> arrayAdapterTasks;
    static SQLiteDatabase tasksDataBase;
    static TaskPopup popup = new TaskPopup();
    static List<Task> allTasks;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_tasks, container, false);


        taskList = view.findViewById(R.id.listTasks);
        tasks = new ArrayList<String>();

        db = new DatabaseHelper(getActivity().getApplicationContext());


        allTasks = db.getAllTasks();
        for (Task task : allTasks) {
            tasks.add(task.getCoins() + " - " + task.getTitle());
            Log.i("Index", Long.toString(task.getId()));
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
                popup.showPopup(position, currActivity, db);


                return true;
            }
        });
        return view;
    }


    public static void addTask(String newTask, int coins) {
        Task task = new Task();
        task.setTitle(newTask);
        task.setCoins(coins);
        db.createTask(task);
        reloadTaskListView();

    }


    public static void reloadTaskListView () {
        allTasks = db.getAllTasks();
        tasks.clear();
        for (Task task : allTasks) {
            tasks.add(task.getCoins() + " - " + task.getTitle());
            Log.i("Index", Long.toString(task.getId()));
        }

        taskList.setAdapter(arrayAdapterTasks);
    }



}