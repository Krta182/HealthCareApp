package com.example.healthcareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthcareapplication.database.HealthCareDatabase;

import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MEAL_ID="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final int mealId=getIntent().getExtras().getInt(EXTRA_MEAL_ID);

        final Handler handler = new Handler();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HealthCareDatabase healthCareDatabase = HealthCareDatabase.getInstance(DetailActivity.this);
                final Meal meal = healthCareDatabase.mealDao().findById(mealId);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onGetMeal(meal);
                    }
                });

            }
        });
    }

    public void onGetMeal(Meal meal) {
        TextView title = findViewById(R.id.textBoxTitle);
        title.setText(meal.getMealName());

        TextView calories = findViewById(R.id.textBoxCalories);
        calories.setText(meal.getCalories());

        TextView date = findViewById(R.id.textBoxDate);
        date.setText(meal.getMealDate());

    }
}