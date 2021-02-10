package com.example.healthcareapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.healthcareapplication.Meal;
import com.example.healthcareapplication.dao.MealDao;

import java.util.concurrent.Executors;

@Database(entities = {Meal.class}, exportSchema = false, version = 1)
public abstract class HealthCareDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "db_healthCare";
    private static HealthCareDatabase healthCareDatabase;

    public static synchronized HealthCareDatabase getInstance(final Context context) {
        if(healthCareDatabase == null) {
            healthCareDatabase = Room.databaseBuilder(context.getApplicationContext(), HealthCareDatabase.class, DATABASE_NAME)
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    String firstRow = String.format("INSERT INTO meal(\"mealName\", \"calories\", \"mealDate\") VALUES(\"%s\", \"%s\", %d)",
                                            Meal.meals[0].getMealName(), Meal.meals[0].getCalories(), Meal.meals[0].getMealDate());
                                    String secondRow = String.format("INSERT INTO meal(\"mealName\", \"calories\", \"mealDate\") VALUES(\"%s\", \"%s\", %d)",
                                            Meal.meals[1].getMealName(), Meal.meals[1].getCalories(), Meal.meals[0].getMealDate());
                                    String thirdRow = String.format("INSERT INTO meal(\"mealName\", \"calories\", \"mealDate\") VALUES(\"%s\", \"%s\", %d)",
                                            Meal.meals[2].getMealName(), Meal.meals[2].getCalories(), Meal.meals[0].getMealDate());
                                    String fourthRow = String.format("INSERT INTO meal(\"mealName\", \"calories\", \"mealDate\") VALUES(\"%s\", \"%s\", %d)",
                                            Meal.meals[3].getMealName(), Meal.meals[3].getCalories(), Meal.meals[0].getMealDate());
                                    db.execSQL(firstRow);
                                    db.execSQL(secondRow);
                                    db.execSQL(thirdRow);
                                    db.execSQL(fourthRow);
                                }
                            });
                        }
                    })
                    .build();
        }
        return healthCareDatabase;
    }

    public abstract MealDao mealDao();
}
