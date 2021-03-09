package com.example.work2play;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
                String newCoinsString = tasks.get(position).substring(0,2);

                int newCoins = Integer.parseInt(newCoinsString);

                int coins = MainActivity.getCoins();
                MainActivity.setCoins(coins + newCoins);

            }
        });

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deleteEntry = tasks.get(position);
                String[] seperatedEntry = deleteEntry.split(" - ");

                tasksDataBase.execSQL("DELETE FROM tasks WHERE task = ('" + seperatedEntry[1] + "')");
                tasks.remove(position);

                taskList.setAdapter(arrayAdapterTasks);
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
