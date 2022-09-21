package com.example.doppeldash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    private DoppeldashDatabase db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }

    public void setDatabase(DoppeldashDatabase inDB)
    {
        db = inDB;
    }
}
