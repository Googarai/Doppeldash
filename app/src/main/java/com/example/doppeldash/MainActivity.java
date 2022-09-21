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
    public static LoginFragment loginFragment;
    private DoppeldashDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        db = new DoppeldashDatabase();
        db.load(this);


        homeFrag = (HomeFragment) fm.findFragmentByTag("fragment_home");
        cartFragment = (CartFragment) fm.findFragmentByTag("fragment_cart");
        loginFragment = (LoginFragment) fm.findFragmentByTag("fragment_login");
        accountFragment = (AccountFragment) fm.findFragmentByTag("fragment_account");


        if (homeFrag==null){
            homeFrag = new HomeFragment();
            homeFrag.setDatabase(db);
            fm.beginTransaction().add(R.id.mainScreenFragment, homeFrag).commit();
        }


        TaskBarFragment taskFrag = (TaskBarFragment) fm.findFragmentByTag("fragment_task_bar");

        if (taskFrag == null){
            taskFrag = new TaskBarFragment();
            fm.beginTransaction().add(R.id.controlFrame, taskFrag).commit();
        }
    }
}