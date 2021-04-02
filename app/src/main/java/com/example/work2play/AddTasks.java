package com.example.work2play;

import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddTasks extends AppCompatActivity {

    public void cancelAddTask (View view) {
        finish();
    }


    public void saveAddTask (View view) {
        EditText name = findViewById(R.id.editTextAufgabe);
        TextView coinsView = findViewById(R.id.textViewCoinsTasks);


        FragmentTasks.addTask(name.getText().toString(), Integer.parseInt(String.valueOf(coinsView.getText().toString())));
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        final SeekBar coinsControl = findViewById(R.id.seekBarTasks);
        final TextView coinsView = findViewById(R.id.textViewCoinsTasks);

        coinsControl.setMax(98);

        coinsControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                coinsView.setText(String.valueOf(progress + 1));
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