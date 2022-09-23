package com.example.doppeldash;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

    private DoppeldashDatabase db;
    private CreateAccFragment createAccFragment;
    EditText email, password;
    AppCompatButton login, createAcc;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        email = (EditText) v.findViewById(R.id.email);
        password = (EditText) v.findViewById(R.id.password);
        login = (AppCompatButton) v.findViewById(R.id.login_button);
        createAcc = (AppCompatButton) v.findViewById(R.id.account_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "TEXT HERE", BaseTransientBottomBar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.white));
                snackbar.setTextColor(getResources().getColor(R.color.purple_700));
                if (email.getText().length() == 0)
                {
                    snackbar.setText("Please enter your email.");
                    snackbar.show();
                }
                else if (password.getText().length() == 0)
                {
                    snackbar.setText("Please enter your password.");
                    snackbar.show();
                }
                else
                {
                    String emailText = email.getText().toString();
                    String passText = password.getText().toString();
                    boolean userFound = false;
                    int i = 0;
                    while (i < db.getNumUsers() && !userFound)
                    {
                        User currUser = db.getUser(i);
                        if (currUser.getEmail().equals(emailText) && currUser.getPassword().equals(passText))
                        {
                            userFound = true;
                        }
                        i++;
                    }
                    if (!userFound)
                    {
                        snackbar.setText("Invalid email or password.");
                        snackbar.show();
                    }
                    else
                    {

                    }
                }
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                createAccFragment = (CreateAccFragment) fm.findFragmentByTag("fragment_create_acc");
                if (createAccFragment == null)
                {
                    createAccFragment = new CreateAccFragment();
                }
                createAccFragment.setDatabase(db);
                fm.beginTransaction().replace(R.id.mainScreenFragment, createAccFragment).commit();
            }
        });

        return v;
    }

    public void setDatabase(DoppeldashDatabase inDB)
    {
        db = inDB;
    }
}
