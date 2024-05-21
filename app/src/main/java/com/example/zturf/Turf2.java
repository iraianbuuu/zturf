package com.example.zturf;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Turf2 extends AppCompatActivity {
    Button but;
    TextView textView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turf2);

        but = findViewById(R.id.button2);
        but.setBackgroundColor(Color.GREEN);
        textView = findViewById(R.id.textView52);

        // Initialize calendar instance
        calendar = Calendar.getInstance();

        // Set OnClickListener for the button
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Datepicker dialog
                showDatePicker();
            }
        });
    }

    // Method to show Datepicker dialog
    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(Turf2.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Set the selected date to the calendar instance
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Show Timepicker dialog after selecting the date
                showTimePicker();
            }
        }, year, month, dayOfMonth);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    // Method to show Timepicker dialog
    // Method to show Timepicker dialog
    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog instance
        TimePickerDialog timePickerDialog = new TimePickerDialog(Turf2.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Set the selected time to the calendar instance
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Display a progress bar indicating booking is in progress
                ProgressDialog progressDialog = new ProgressDialog(Turf2.this);
                progressDialog.setMessage("Booking in progress...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // Simulate a delay to mimic booking process
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss the progress dialog after booking is complete
                        progressDialog.dismiss();

                        // Display the selected date and time as a toast message
                        String selectedDateTime = calendar.getTime().toString();
                        textView.setText(selectedDateTime);
                        Toast.makeText(Turf2.this, "Booking Sucessfull!!!!", Toast.LENGTH_LONG).show();

                    }

                }, 3000);
            }
        }, hour, minute, false);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
}
