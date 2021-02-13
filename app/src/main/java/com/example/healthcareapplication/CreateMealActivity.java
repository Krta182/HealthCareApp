package com.example.healthcareapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.healthcareapplication.database.HealthCareDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateMealActivity extends AppCompatActivity{

    private long mealId;
    private HealthCareDatabase healthCareDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        EditText mealName = findViewById(R.id.MealAddBox);
        EditText calories= findViewById(R.id.CaloriesAddBox);
        EditText date=findViewById(R.id.DatePicker);
        Button saveMeal=findViewById(R.id.SaveMealButton);

        mealName.setInputType(InputType.TYPE_NULL);
        calories.setInputType(InputType.TYPE_NULL);
        date.setInputType(InputType.TYPE_NULL);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date);
            }
        });

    }

    public void onClickSaveMeal(View view) {
        EditText mealName = findViewById(R.id.MealAddBox);
        EditText calories= findViewById(R.id.CaloriesAddBox);
        EditText date=findViewById(R.id.DatePicker);
        Button saveMeal=findViewById(R.id.SaveMealButton);

        mealName.setInputType(InputType.TYPE_NULL);
        calories.setInputType(InputType.TYPE_NULL);
        date.setInputType(InputType.TYPE_NULL);






        finish();
    }

    private void showDateDialog(EditText date) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

    }

    public void onClickCancelMeal(View view) {
       finish();
    }
}