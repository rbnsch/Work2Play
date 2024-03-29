package com.example.work2play;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Calendar;

public class AddTasks extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

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

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        month = month + 1;
        return makeDateString(day, month, year);
    }

    private void initDatepicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day ) {
                month = month +1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        String[] strMonth = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};


        if (month > 0 && month < 13) {
            return strMonth[month -1];
        }
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        final SeekBar coinsControl = findViewById(R.id.seekBarTasks);
        final TextView coinsView = findViewById(R.id.textViewCoinsTasks);
        final Spinner spinnerWdh = findViewById(R.id.spinnerWdh);

        ArrayAdapter<CharSequence> taskWdhAdapter = ArrayAdapter.createFromResource(this, R.array.task_wdh, android.R.layout.simple_spinner_item);
        taskWdhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWdh.setAdapter(taskWdhAdapter);
        spinnerWdh.setOnItemSelectedListener(this);

        initDatepicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());


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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}