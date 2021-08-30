package com.example.aptdemo;

import android.app.Application;

import com.example.navigation.Navigator;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Navigator.init();

    }
}
