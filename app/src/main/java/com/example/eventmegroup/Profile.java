package com.example.eventmegroup;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

    }

    public Profile(){
//        System.out.println("here2");
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
