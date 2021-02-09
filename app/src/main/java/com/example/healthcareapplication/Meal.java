package com.example.healthcareapplication;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

public class Meal {
    private String mealName;
    private String calories;
    Date date=new Date();
    private String mealDate = DateFormat.getDateInstance().format(date);


    public static final Meal[] meals = {
            new Meal("Steak", "400Ca","10-02-2020"),
            new Meal("Chicken", "300Ca","11-01-2020"),
            new Meal("Banana", "50Ca","12-03-2020"),
            new Meal("Yogurt", "40Ca","09-02-2020")
    };

    public Meal(String mealName, String calories,String mealDate) {
        this.mealName = mealName;
        this.calories = calories;
        this.mealDate=mealDate;
    }

    public String getMealName() {
        return mealName;
    }
    public String getCalories() {
        return calories;
    }
    public String getMealDate(){
        return mealDate;
    }

    @NonNull
    @Override
    public String toString() {
        return this.mealName;
    }
}
