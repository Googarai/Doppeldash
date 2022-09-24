package com.example.doppeldash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

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
    private AccountFragment accountFragment;
    private UserViewModel uvm;
    private User currUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View taskView = inflater.inflate(R.layout.fragment_task_bar, container, false);

        db = new DoppeldashDatabase();
        db.load(getActivity());

        ImageButton home = (ImageButton) taskView.findViewById(R.id.homeButton);
        ImageButton cart = (ImageButton) taskView.findViewById(R.id.cartButton);
        ImageButton account = (ImageButton) taskView.findViewById(R.id.accountButton);

        FragmentManager fm = getParentFragmentManager();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment = (HomeFragment) fm.findFragmentByTag("fragment_home");
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                }
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
                uvm = new ViewModelProvider(getActivity()).get(UserViewModel.class);
                currUser = uvm.getUser();

                if (currUser == null) {
                    loginFragment = (LoginFragment) fm.findFragmentByTag("fragment_login");
                    if (loginFragment == null) {
                        loginFragment = new LoginFragment();
                    }
                    fm.beginTransaction().replace(R.id.mainScreenFragment, loginFragment).commit();
                } else {
                    accountFragment = (AccountFragment) fm.findFragmentByTag("fragment_account");
                    if (accountFragment == null) {
                        accountFragment = new AccountFragment();
                    }
                    fm.beginTransaction().replace(R.id.mainScreenFragment, accountFragment).commit();
                }
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