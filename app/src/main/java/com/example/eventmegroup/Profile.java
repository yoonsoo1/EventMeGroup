package com.example.eventmegroup;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null) {
            startActivity(new Intent(Profile.this, SignInActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(Profile.this, SetUpActivity.class));
            finish();
//            TextView tv = findViewById(R.id.status);
//            tv.setText(currentUser.getEmail());
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    firebaseAuth.signOut();
//                    finish();
//                    startActivity(getIntent());
//                }
//            });
        }
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
