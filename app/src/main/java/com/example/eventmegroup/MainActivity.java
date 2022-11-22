package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(MainActivity.this, Map.class));
            finish();
        }


//        System.out.println("here1");
// test

    }

    public void goProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void goMap(View view){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void goSearch(View view){
        Intent intent = new Intent(this, Explore.class);
        startActivity(intent);
    }

}