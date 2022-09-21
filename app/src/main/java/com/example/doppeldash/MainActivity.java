package com.example.doppeldash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static HomeFragment homeFrag;
    public static CartFragment cartFragment;
    public static AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        homeFrag = (HomeFragment) fm.findFragmentByTag("fragment_home");
        cartFragment = (CartFragment) fm.findFragmentByTag("fragment_cart");
        accountFragment = (AccountFragment) fm.findFragmentByTag("fragment_account");


        if (homeFrag==null){
            homeFrag = new HomeFragment();
            fm.beginTransaction().add(R.id.mainScreenFragment, homeFrag).commit();
        }


        TaskBarFragment taskFrag = (TaskBarFragment) fm.findFragmentByTag("fragment_task_bar");

        if (taskFrag == null){
            taskFrag = new TaskBarFragment();
            fm.beginTransaction().add(R.id.controlFrame, taskFrag).commit();
        }




    }


}