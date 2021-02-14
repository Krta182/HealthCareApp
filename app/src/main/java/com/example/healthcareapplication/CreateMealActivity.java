package com.example.healthcareapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcareapplication.database.HealthCareDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
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


        String mealname=mealName.getText().toString();
        String mealcalories=calories.getText().toString();
        String mealdate=date.getText().toString();
        DateHelper.stringToDate(mealdate);


        saveMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mealname.isEmpty()) {
                    Toast.makeText(CreateMealActivity.this, "This is required field.Please fill in the meal name,", Toast.LENGTH_SHORT).show();
                }
                else if(mealcalories.isEmpty()) {
                    Toast.makeText(CreateMealActivity.this, "This is required field.Please fill in the meal calories,", Toast.LENGTH_SHORT).show();
                }

                else if(mealdate.isEmpty()) {
                    Toast.makeText(CreateMealActivity.this, "This is required field.Please fill in the meal date,", Toast.LENGTH_SHORT).show();
                }


                else {
                    new InsertMealAsyncTask().execute(mealname,mealcalories,mealdate);
                    Toast.makeText(CreateMealActivity.this, "You added meal!", Toast.LENGTH_SHORT).show();

                }
            }
        });

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

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

    }

    public void onClickCancelMeal(View view) {
       finish();
    }

    public class InsertMealAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... string) {
            String mealName = string [0];
            String mealCalories = string [1];
            String mealDate= string [2];

            healthCareDatabase = HealthCareDatabase.getInstance(CreateMealActivity.this);

            Meal meal = new Meal();
            meal.setMealName(mealName);
            meal.setCalories(mealCalories);
            meal.setMealDate(DateHelper.stringToDate(mealDate));

            healthCareDatabase.mealDao().insertMeal(meal);

            return null;
        }
    }
}