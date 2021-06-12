 package com.example.work2play;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.work2play.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

 public class FragmentHabits extends Fragment {

    private static DatabaseHelper db;
    View view;
    static ListView habitList;
    static ArrayList<HabitDataHelper> habits;
    static HabitListAdapter habitListAdapter;
    static Popup popup = new HabitPopup();
    static List<HabitDataHelper> allHabits;

    private static SharedPreferences lastMonday;


     public static void setLastMonday(){
         Calendar cal = Calendar.getInstance(Locale.GERMANY);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.clear(Calendar.MINUTE);
         cal.clear(Calendar.SECOND);
         cal.clear(Calendar.MILLISECOND);

         cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
         int thisDay = (int)cal.getTimeInMillis();
         lastMonday.edit().putInt("lastMonday",thisDay).apply();
     }

     public static int getNewLastMonday(){
         Calendar cal = Calendar.getInstance(Locale.GERMANY);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.clear(Calendar.MINUTE);
         cal.clear(Calendar.SECOND);
         cal.clear(Calendar.MILLISECOND);

         cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
         int thisDay = (int)cal.getTimeInMillis();
         return thisDay;
     }

     public static void resetHabits()
     {
         allHabits = db.getAllHabits();
         for( HabitDataHelper habitDataHelper : allHabits){
             habitDataHelper.setNumberRepDone(0);
             db.updateHabit(habitDataHelper);
         }

         habitListAdapter.notifyDataSetChanged();
         reloadHabitListView();

     }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_habits, container, false);


        habitList = view.findViewById(R.id.listHabits);
        habits = new ArrayList<>();

        db = new DatabaseHelper(getActivity().getApplicationContext());

        habits = db.getAllHabits();

        habitListAdapter = new HabitListAdapter(getActivity(), R.layout.adapter_view_habits, habits);

        lastMonday = getActivity().getSharedPreferences("com.example.work2play", Context.MODE_PRIVATE);
        if(lastMonday.getInt("lastMonday", 0) != getNewLastMonday()){
            resetHabits();
            setLastMonday();
            Log.i("NewMonday", "new Monday");
            Log.i("lastMonday", Long.toString(lastMonday.getInt("lastMonday", 0)));
        }
        else
        {
            Log.i("noNewMonday", "no new Monday");
        }

        //lastMonday.edit().putInt("lastMonday",0).apply();

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
                popup.showPopup(position, currActivity, db);


                return true;
            }
        });


        return view;
    }

    public static void addHabit(HabitDataHelper newHabit){
        db.createHabit(newHabit);
        reloadHabitListView();
    }

    public static void reloadHabitListView(){
        allHabits = db.getAllHabits();
        habits.clear();
        for( HabitDataHelper habitDataHelper : allHabits){
            habits.add(habitDataHelper);
        }
        habitList.setAdapter(habitListAdapter);

    }
}
