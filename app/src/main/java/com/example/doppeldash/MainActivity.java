package com.example.doppeldash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFrag;
    private TaskBarFragment taskFrag;
    private AccountFragment accountFragment;
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
        accountFragment = (AccountFragment) fm.findFragmentByTag("fragment_account");
        taskFrag = (TaskBarFragment) fm.findFragmentByTag("fragment_task_bar");

        if (homeFrag==null){
            homeFrag = new HomeFragment();
            homeFrag.setDatabase(db);
            fm.beginTransaction().add(R.id.mainScreenFragment, homeFrag).commit();
        }

        if (taskFrag == null){
            taskFrag = new TaskBarFragment();
            taskFrag.setDatabase(db);
            fm.beginTransaction().add(R.id.controlFrame, taskFrag).commit();
        }
    }
}