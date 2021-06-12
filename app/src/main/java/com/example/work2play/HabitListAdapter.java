package com.example.work2play;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HabitListAdapter extends ArrayAdapter<HabitDataHelper> {

    //private static final String TAG = "HabitListAdapter";


    private Context mContext;
    private int mResource;

    public HabitListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HabitDataHelper> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String title = getItem(position).getTitle();
        int numberRep = getItem(position).getNumberRep();
        int numberRepDone = getItem(position).getNumberRepDone();




        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent, false);

        ProgressBar pbHabits = (ProgressBar) convertView.findViewById(R.id.pbHabits);

        TextView tvDescription = (TextView) convertView.findViewById(R.id.textViewAdapterHabitTitle);
        TextView tvRepDone = (TextView) convertView.findViewById(R.id.textViewRepDone);


        pbHabits.setMax(numberRep);
        pbHabits.setProgress(numberRepDone);
        tvDescription.setText(title);
        tvRepDone.setText(numberRepDone + "/" + numberRep);

        return convertView;

    }
}
