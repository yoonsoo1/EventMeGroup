package com.example.eventmegroup;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class NavBar extends AppCompatActivity {
    public NavBar(){}

    public void goProfile(View view){
        Intent intent = new Intent(NavBar.this, Profile.class);
        startActivity(intent);
    }

    public void goMap(View view){
        Intent intent = new Intent(NavBar.this, Map.class);
        startActivity(intent);
    }

    public void goSearch(View view){
        Intent intent = new Intent(NavBar.this, Explore.class);
        startActivity(intent);
    }
}
