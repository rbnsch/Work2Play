 package com.example.work2play;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class FragmentHabits extends Fragment {

    View view;
    static ListView habitList;
    static ArrayList<HabitDataHelper> habits;
    static HabitListAdapter habitListAdapter;
    static SQLiteDatabase habitsDataBase;
    static Popup popup = new HabitPopup();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_habits, container, false);


        habitList = view.findViewById(R.id.listHabits);
        habits = new ArrayList<>();


        habitListAdapter = new HabitListAdapter(getActivity(), R.layout.adapter_view_habits, habits);

        habitList.setAdapter(habitListAdapter);

        habitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

        habitList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentActivity currActivity = getActivity();
                popup.showPopup(position, currActivity);


                return true;
            }
        });


        return view;
    }

    public static void addHabit(HabitDataHelper newHabit){
        habits.add(newHabit);
        habitList.setAdapter(habitListAdapter);
    }
}
