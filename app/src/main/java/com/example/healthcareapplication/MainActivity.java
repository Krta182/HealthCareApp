package com.example.healthcareapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthcareapplication.database.HealthCareDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private HealthCareDatabase healthCareDatabase;
    private Cursor mealCursor;

    private EditText DatePicker;
    private Button DisplayBtn;

    private long mealId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.Meal_list);

        healthCareDatabase=HealthCareDatabase.getInstance(MainActivity.this);
        new MealListAsyncTask(this, false).execute();


    }
    @Override
    protected void onRestart() {
        super.onRestart();

        healthCareDatabase= HealthCareDatabase.getInstance(MainActivity.this);
        new MealListAsyncTask(this, true).execute();
    }



    public class MealListAsyncTask extends AsyncTask<Void, Void, Cursor> {
        Context context;
        boolean onRestart;

        public MealListAsyncTask(Context context, boolean onRestart) {
            this.context = context;
        }

        @Override
        protected Cursor doInBackground(Void... voids) {

            return healthCareDatabase.mealDao().getMealListCursor();
        }

        @Override
        protected void onPostExecute(Cursor mealCursor) {
            ListView listMeals = findViewById(R.id.Meal_list);

            if(!onRestart){
                listMeals.setAdapter(new SimpleCursorAdapter(
                        context,
                        android.R.layout.simple_list_item_1,
                        mealCursor,
                        new String[]{"mealName"},
                        new int[]{android.R.id.text1},
                        0
                ));
                listMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_MEAL_ID, (int) id);
                        startActivity(intent);
                    }
                });
                listMeals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        healthCareDatabase=HealthCareDatabase.getInstance(MainActivity.this);
                        healthCareDatabase.mealDao().deleteMeal(Meal.meals[(int)id]);
                        return false;
                    }
                });
            }else {
                SimpleCursorAdapter adapter = (SimpleCursorAdapter) listMeals.getAdapter();
                adapter.changeCursor(mealCursor);
            }
        }
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){

            switch (item.getItemId()) {
                case R.id.action_create_meal:
                    Intent intent = new Intent(this, CreateMealActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

}



