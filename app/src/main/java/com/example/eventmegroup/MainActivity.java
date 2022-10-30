package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        System.out.println("here1");


    }

    public void goProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
//        System.out.println("here1");
    }

    public void goMap(View view){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
//        System.out.println("here3");
    }

    public void goSearch(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
//        System.out.println("here5");
    }






}