package com.example.eventmegroup;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ImageView circleImageView;
    private TextView mProfileName;
    private TextView bday;
    private Button editBtn;
    private Button logOutBtn;
    private FirebaseAuth auth;
    private Uri imageUri;
    private String uid;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore db;
    private Uri downloadUri = null;
    private String ogImgUri;
    private LinearLayout scrollLin;

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
            circleImageView = findViewById(R.id.circleImageView);
            mProfileName = findViewById(R.id.profile_name);
            bday = findViewById(R.id.bday);
            editBtn = findViewById(R.id.edit_btn);
            logOutBtn = findViewById(R.id.log_out_btn);

            scrollLin = findViewById(R.id.scroll_linear);

            auth = FirebaseAuth.getInstance();
            uid = auth.getCurrentUser().getUid();
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            db = FirebaseFirestore.getInstance();

            db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        if(task.getResult().exists()) {
                            DocumentSnapshot ds = task.getResult();
                            String name = task.getResult().getString("name");
                            String birthdate = task.getResult().getString("bday");
                            ogImgUri = task.getResult().getString("image");
                            List<String> events = (List<String>) ds.get("events");
                            mProfileName.setText(name);
                            bday.setText(birthdate);
                            Glide.with(Profile.this).load(ogImgUri).into(circleImageView);

                            if(events != null) {
                                for(String eventId : events) {
                                    db.collection("Events").document(eventId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            String eName = task.getResult().getString("name");
                                            String eDate = task.getResult().getString("date");
                                            String eTime = task.getResult().getString("time");
                                            String result = "☆ " + eName + " " + eDate + " " + eTime;

                                            TextView tv = new TextView(Profile.this);
                                            tv.setText(result);
                                            tv.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent details = new Intent(Profile.this, Detail.class);
                                                    details.putExtra("eventId", eventId);
                                                    startActivity(details);
                                                }
                                            });

                                            scrollLin.addView(tv);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(new Intent(Profile.this, SetUpActivity.class));
                }
            });
            logOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                    finish();
                    startActivity(new Intent(Profile.this, MainActivity.class));
                }
            });
        }
    }

    public Profile(){
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
