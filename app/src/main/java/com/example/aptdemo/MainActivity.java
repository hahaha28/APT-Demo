package com.example.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apt_annotation.Print;
import com.example.navigation.Navigator;


//@Routing(key="main")
public class MainActivity extends AppCompatActivity {

    @Print
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(v ->{
            Navigator.jump(MainActivity.this,"test");

        });

    }
}