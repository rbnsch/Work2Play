package com.example.work2play;

import android.view.View;
import android.widget.EditText;
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
        EditText coins = (EditText) findViewById(R.id.editTextAufgabeCoins);

        FragmentTasks.addTask(name.getText().toString(), Integer.parseInt(String.valueOf(coins.getText().toString())));
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
    }
}