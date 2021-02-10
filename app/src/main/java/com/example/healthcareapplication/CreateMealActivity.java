package com.example.healthcareapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMealActivity extends AppCompatActivity{

    private long mealId;
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
        EditText mealName = findViewById(R.id.MealAddBox);
        String mealBoxContent = mealName.getText().toString();
        EditText calories= findViewById(R.id.CaloriesAddBox);
        String caloriesBoxContent=calories.getText().toString();

        CalendarView date=findViewById(R.id.DatePicker);
        String mealDate = DateFormat.getDateInstance().format(date);

        Meal meal = new Meal(mealBoxContent,caloriesBoxContent,mealDate);

        ArrayAdapter<Meal> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.add(meal);


        finish();
    }

    public void onClickCancelMeal(View view) {
       finish();
    }
}