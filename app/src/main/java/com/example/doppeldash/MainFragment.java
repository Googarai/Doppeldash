package com.example.doppeldash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment
{
    private DoppeldashDatabase db;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        db = new DoppeldashDatabase();
        db.load(getActivity());
    }
}