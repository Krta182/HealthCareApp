package com.example.healthcareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MEAL_ID="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MealDetailFragment fragment = (MealDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        int mealId = (int) getIntent().getExtras().getInt(EXTRA_MEAL_ID);
        fragment.setMealId(mealId);
    }
}