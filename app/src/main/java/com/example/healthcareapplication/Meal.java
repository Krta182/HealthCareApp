package com.example.healthcareapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "meal")
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private Integer _id;
    private String mealName;
    private String calories;
    /*
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    String mealDate = dateFormat.format(date);
*/



    public static final Meal[] meals = {
            new Meal("Steak", "400Ca","2020-02-20"),
            new Meal("Chicken", "300Ca","2020-03-10"),
            new Meal("Banana", "50Ca","2020-04-15"),
            new Meal("Yogurt", "40Ca","2020-08-28")
    };

    public Meal(String mealName, String calories,String  mealDate) {
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
