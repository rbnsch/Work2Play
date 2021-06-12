package com.example.work2play;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class AddHabits extends AppCompatActivity {


    public void cancelAddHabits(View view) {
        finish();
    }

    public void saveAddHabits(View view) {
        EditText description = findViewById(R.id.editTextHabit);
        TextView coinsAllView = findViewById(R.id.textViewCoinsHabitsAll);
        TextView coinsOneView = findViewById(R.id.textViewCoinsHabitsOne);
        EditText numberRep = findViewById((R.id.editTextRep));

        String descripionStr = description.getText().toString();
        String numberRepStr = numberRep.getText().toString();
        String coinsAllStr = coinsAllView.getText().toString();

        int coinsAllInt = Integer.parseInt(coinsAllStr);
        int coinsOneInt = Integer.parseInt(coinsAllStr);

        int numberRepInt = 0;

        if (!"".equals(numberRepStr)){
            numberRepInt = Integer.parseInt(numberRepStr);
        }

        HabitDataHelper newHabit = new HabitDataHelper(descripionStr, numberRepInt, 0, coinsOneInt, coinsAllInt);
        FragmentHabits.addHabit(newHabit);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habits);

        final SeekBar coinsControlAll = findViewById(R.id.seekBarHabitsAll);
        final SeekBar coinsControlOne = findViewById(R.id.seekBarHabitsOne);
        final TextView coinsViewAll = findViewById(R.id.textViewCoinsHabitsAll);
        final TextView coinsViewOne = findViewById(R.id.textViewCoinsHabitsOne);

        coinsControlAll.setMax(98);
        coinsControlOne.setMax(98);

        coinsControlAll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                coinsViewAll.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        coinsControlOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                coinsViewOne.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
