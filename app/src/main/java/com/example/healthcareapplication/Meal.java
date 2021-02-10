package com.example.healthcareapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.text.DateFormat;
import java.util.Date;

@Entity(tableName = "meal")
public class Meal {
    private Integer _id;
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
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    public String getCalories() {
        return calories;
    }
    public void setCalories(String calories) {
        this.calories = calories;
    }
    public String getMealDate(){
        return mealDate;
    }
    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }
    public Integer get_id() {
        return _id;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.mealName;
    }
}
