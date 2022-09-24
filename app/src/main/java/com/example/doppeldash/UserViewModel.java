package com.example.doppeldash;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    public MutableLiveData<User> user;
    public UserViewModel()
    {
        user = new MutableLiveData<User>();
        user.setValue(null);
    }

    public User getUser()
    {
        return user.getValue();
    }

    public void setUser(User user)
    {
        this.user.setValue(user);
    }
}
