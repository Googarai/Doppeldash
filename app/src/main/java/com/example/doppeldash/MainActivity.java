package com.example.doppeldash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        DoppeldashDatabase db = new DoppeldashDatabase();
        db.load(this);

        HomeFragment homeFrag = (HomeFragment) fm.findFragmentByTag("fragment_home");
        TaskBarFragment taskFrag = (TaskBarFragment) fm.findFragmentByTag("fragment_task_bar");

        if (homeFrag == null){
            homeFrag = new HomeFragment();
            fm.beginTransaction().add(R.id.mainScreenFragment, homeFrag).commit();
        }

        if (taskFrag == null){
            taskFrag = new TaskBarFragment();
            fm.beginTransaction().add(R.id.controlFrame, taskFrag).commit();
        }
    }
}