package com.example.doppeldash;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.doppeldash.DoppeldashDatabase;
import com.example.doppeldash.LoginFragment;
import com.example.doppeldash.R;
import com.example.doppeldash.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AccountFragment extends Fragment {

    private UserViewModel uvm;
    private DoppeldashDatabase db;
    private User currUser;
    TextView welcome;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        FragmentManager fm = getParentFragmentManager();

        db = new DoppeldashDatabase();
        db.load(getActivity());
        welcome = (TextView) v.findViewById(R.id.welcomeText);

        uvm = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        currUser = uvm.getUser();

        welcome.setText("Welcome back, " + currUser.getName());

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
