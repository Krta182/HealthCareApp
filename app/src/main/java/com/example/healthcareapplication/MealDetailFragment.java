package com.example.healthcareapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MealDetailFragment extends Fragment {
    private long mealId;


    public MealDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null) {
            Meal meal = Meal.meals[(int) mealId];

            TextView title = view.findViewById(R.id.textBoxTitle);
            title.setText(meal.getMealName());

            TextView description = view.findViewById(R.id.textBoxCalories);
            description.setText(meal.getCalories());

            TextView date = view.findViewById(R.id.textBoxDate);
            description.setText(meal.getMealDate());
        }
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }
}
