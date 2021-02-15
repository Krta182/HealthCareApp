package com.example.healthcareapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "meal")
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private Integer _id;
    private String mealName;
    private String calories;
    private Date mealDate;

    public static final Meal[] meals = {
            new Meal("Steak", "400Ca",DateHelper.stringToDate("2020-02-14")),
            new Meal("Chicken", "300Ca",DateHelper.stringToDate("2020-01-13")),
            new Meal("Banana", "50Ca",DateHelper.stringToDate("2020-07-12")),
            new Meal("Yogurt", "40Ca",DateHelper.stringToDate("2020-06-09"))
    };

    public Meal(String mealName, String calories,Date mealDate) {
        this.mealName = mealName;
        this.calories = calories;
        this.mealDate=mealDate;
    }

    public Meal()
    {

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
    public Date getMealDate(){
        return mealDate;
    }
    public void setMealDate(Date mealDate) {
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
