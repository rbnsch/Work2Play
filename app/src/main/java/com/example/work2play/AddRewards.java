package com.example.work2play;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddRewards extends AppCompatActivity {





    public void cancelAddRewards (View view) {
        finish();
    }


    public void saveAddTask (View view) {
        EditText name = findViewById(R.id.editTextBelohnung);
        EditText coins = (EditText) findViewById(R.id.editTextBelohnungCoins);

        FragmentRewards.addReward(name.getText().toString(), Integer.parseInt(String.valueOf(coins.getText().toString())));
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rewards);
    }
}