package com.example.sss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apt_annotation.Routing;

@Routing(key="sss")
public class SssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sss);
    }
}