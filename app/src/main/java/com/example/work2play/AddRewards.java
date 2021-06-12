package com.example.work2play;


import android.view.View;

import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class AddRewards extends AppCompatActivity {





    public void cancelAddRewards (View view) {
        finish();
    }


    public void saveAddTask (View view) {
        EditText name = findViewById(R.id.editTextBelohnung);
        TextView coinsView = findViewById(R.id.textViewCoinsRewards);
        Switch multiple = findViewById(R.id.switchMultipleReward);
        FragmentRewards.addReward(name.getText().toString(), Integer.parseInt(String.valueOf(coinsView.getText().toString())), multiple.isChecked());
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rewards);

        final SeekBar coinsControl = findViewById(R.id.seekBarRewards);
        final TextView coinsView = findViewById(R.id.textViewCoinsRewards);

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