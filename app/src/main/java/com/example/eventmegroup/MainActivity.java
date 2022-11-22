package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
    }

    public void goEvent(View view){
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }

    public void goExplore(View view){
        Intent intent = new Intent(this, Explore.class);
        startActivity(intent);
    }

}