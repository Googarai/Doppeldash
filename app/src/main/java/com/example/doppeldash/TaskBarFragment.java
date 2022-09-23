package com.example.doppeldash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.apache.commons.logging.Log;

public class TaskBarFragment extends Fragment {

    private DoppeldashDatabase db;
    private CartFragment cartFragment;
    private LoginFragment loginFragment;
    private HomeFragment homeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View taskView = inflater.inflate(R.layout.fragment_task_bar, container, false);

        ImageButton home = (ImageButton) taskView.findViewById(R.id.homeButton);
        ImageButton cart = (ImageButton) taskView.findViewById(R.id.cartButton);
        ImageButton account = (ImageButton) taskView.findViewById(R.id.accountButton);

        FragmentManager fm = getParentFragmentManager();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment = (HomeFragment) fm.findFragmentByTag("fragment_home");
                fm.beginTransaction().replace(R.id.mainScreenFragment, homeFragment).commit();
            }
        });

       cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartFragment = (CartFragment) fm.findFragmentByTag("fragment_cart");
                if (cartFragment == null){
                    cartFragment = new CartFragment();
                }
                fm.beginTransaction().replace(R.id.mainScreenFragment, cartFragment).commit();
            }
        });

       account.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               loginFragment = (LoginFragment) fm.findFragmentByTag("fragment_login");
               if (loginFragment == null)
               {
                   loginFragment = new LoginFragment();
               }
               loginFragment.setDatabase(db);
               fm.beginTransaction().replace(R.id.mainScreenFragment, loginFragment).commit();
           }
       });

        // Inflate the layout for this fragment
        return taskView;
    }

    public void setDatabase(DoppeldashDatabase inDB)
    {
        db = inDB;
    }
}