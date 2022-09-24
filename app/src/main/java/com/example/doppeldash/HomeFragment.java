package com.example.doppeldash;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.IllegalFormatException;

public class HomeFragment extends Fragment {

    static int serve = 0;
    EditText serveAmount;
    ImageButton increaseServe, decreaseServe, addServe;
    DoppeldashDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DoppeldashDatabase();
        db.load(getActivity());

        FragmentManager fm = getParentFragmentManager();
        RestaurantFragment restFrag = (RestaurantFragment) fm.findFragmentByTag("fragment_restaurant");
        FoodFragment foodFrag = (FoodFragment) fm.findFragmentByTag("fragment_food_list");

        if (foodFrag == null){
            foodFrag = new FoodFragment();
            fm.beginTransaction().add(R.id.foodListFrame, foodFrag).commit();
        }

        if (restFrag == null){
            restFrag = new RestaurantFragment();
            fm.beginTransaction().add(R.id.restaurantListFrame, restFrag).commit();
        }


        increaseServe = (ImageButton) v.findViewById(R.id.increaseServe);
        decreaseServe = (ImageButton) v.findViewById(R.id.decreaseServe);
        serveAmount = (EditText) v.findViewById(R.id.servingSize);
        addServe = (ImageButton) v.findViewById(R.id.addServing);

        serveAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    serve = Integer.parseInt(serveAmount.getText().toString());
                    if(serve > 100){
                        serve = 100;
                        serveAmount.setText(String.valueOf(serve));
                    }
                    else if (serve < 0){
                        serve = 0;
                        serveAmount.setText(String.valueOf(serve));
                    }
                }
                catch (NumberFormatException e){
                    serve = 0;
                    serveAmount.setText(String.valueOf(serve));
                }
            }
        });

        increaseServe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serve = Integer.parseInt(serveAmount.getText().toString());
                if (serve != 100) {
                    serve += 1;
                    serveAmount.setText(String.valueOf(serve));
                }
            }
        });

        decreaseServe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serve = Integer.parseInt(serveAmount.getText().toString());
                if (serve > 0) {
                    serve -= 1;
                    serveAmount.setText(String.valueOf(serve));
                }
            }
        });


        return v;
    }
}