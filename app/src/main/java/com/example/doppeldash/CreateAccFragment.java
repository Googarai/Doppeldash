package com.example.doppeldash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateAccFragment extends Fragment {

    private DoppeldashDatabase db;
    LoginFragment loginFragment;
    EditText email, name, password, retypePassword;
    AppCompatButton signUp, back;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.fragment_create_acc, container, false);
        FragmentManager fm = getParentFragmentManager();

        email = (EditText) v.findViewById(R.id.email);
        name = (EditText) v.findViewById(R.id.name);
        password = (EditText) v.findViewById(R.id.password);
        retypePassword = (EditText) v.findViewById(R.id.retype_password);
        signUp = (AppCompatButton) v.findViewById(R.id.signup_button);
        back = (AppCompatButton) v.findViewById(R.id.back_button);

        signUp.setOnClickListener(new View.OnClickListener() {
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
                else if (name.getText().length() == 0)
                {
                    snackbar.setText("Please enter a name.");
                    snackbar.show();
                }
                else if (password.getText().length() == 0)
                {
                    snackbar.setText("Please enter a password.");
                    snackbar.show();
                }
                else if (!retypePassword.getText().toString().equals(password.getText().toString()))
                {
                    snackbar.setText("Passwords do not match.");
                    snackbar.show();
                }
                else
                {
                    String emailText = email.getText().toString();
                    boolean userFound = false;
                    int i = 0;
                    while (i < db.getNumUsers() && !userFound)
                    {
                        User currUser = db.getUser(i);
                        if (currUser.getEmail().equals(emailText))
                        {
                            userFound = true;
                        }
                        i++;
                    }
                    if (userFound)
                    {
                        snackbar.setText("User with email already exists. Please log in.");
                        snackbar.show();
                    }
                    else
                    {
                        String nameText = name.getText().toString();
                        String passText = password.getText().toString();
                        db.addUser(new User(emailText, passText, nameText));

                        AccountFragment accountFragment = (AccountFragment) fm.findFragmentByTag("fragment_login");
                        if (loginFragment == null)
                        {
                            loginFragment = new LoginFragment();
                        }
                        fm.beginTransaction().replace(R.id.mainScreenFragment, loginFragment).commit();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFragment = (LoginFragment) fm.findFragmentByTag("fragment_login");
                if (loginFragment == null)
                {
                    loginFragment = new LoginFragment();
                }
                fm.beginTransaction().replace(R.id.mainScreenFragment, loginFragment).commit();
            }
        });

        return v;
    }

    public void setDatabase(DoppeldashDatabase inDB)
    {
        db = inDB;
    }
}
