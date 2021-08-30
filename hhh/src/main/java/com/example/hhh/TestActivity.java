package com.example.hhh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apt_annotation.Routing;
import com.example.navigation.Navigator;

@Routing(key = "test")
public class TestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener( v ->{
            Navigator.jump(this,"sss");
        });
    }
}