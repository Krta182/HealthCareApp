package com.example.healthcareapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthcareapplication.database.HealthCareDatabase;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity  implements IListener {

    private HealthCareDatabase healthCareDatabase;
    private Cursor mealCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Handler handler = new Handler();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                healthCareDatabase = HealthCareDatabase.getInstance(MainActivity.this);
                mealCursor = healthCareDatabase.mealDao().getMealListCursor();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onGetCursor(mealCursor);
                    }
                });
            }


        });
    }

    public void onGetCursor(Cursor mealCursor) {
        ListView listMeals = findViewById(R.id.Meal_list_Fragment);
        listMeals.setAdapter(new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                mealCursor,
                new String[]{"name"},
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


        @Override
        public void itemClicked ( long id){
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MEAL_ID, (int) id);
            startActivity(intent);
        }

        @Override
        public void OnLongItemClick ( long id){

        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mealCursor.close();
        healthCareDatabase.close();
    }
}



