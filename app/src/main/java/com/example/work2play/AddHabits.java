package com.example.work2play;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddHabits extends AppCompatActivity {


    public void cancelAddHabits(View view) {
        finish();
    }

    public void saveAddHabits(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habits);
    }
}
