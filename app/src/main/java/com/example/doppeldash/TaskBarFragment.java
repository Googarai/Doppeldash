package com.example.doppeldash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class TaskBarFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View taskView = inflater.inflate(R.layout.fragment_task_bar, container, false);

        ImageButton home = (ImageButton) taskView.findViewById(R.id.homeButton);
        ImageButton cart = (ImageButton) taskView.findViewById(R.id.cartButton);
        ImageButton account = (ImageButton) taskView.findViewById(R.id.accountButton);

        FragmentManager fm = getFragmentManager();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.mainScreenFragment, MainActivity.homeFrag).commit();
            }
        });

       cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.cartFragment == null){
                    MainActivity.cartFragment = new CartFragment();
                }
                fm.beginTransaction().replace(R.id.mainScreenFragment, MainActivity.cartFragment).commit();
            }
        });
        // Inflate the layout for this fragment
        return taskView;
    }
}