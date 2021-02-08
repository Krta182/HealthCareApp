package com.example.healthcareapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;

public class CreateMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSaveMeal(View view) {
        EditText meal = findViewById(R.id.MealAddBox);
        String mealBoxContent = meal.getText().toString();
        EditText calories= findViewById(R.id.CaloriesAddBox);
        String caloriesBoxContent=calories.getText().toString();
        CalendarView date=findViewById(R.id.DatePicker);
        long calendarViewDate=date.getDate();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCancelMeal(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}