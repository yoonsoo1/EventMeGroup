package com.example.eventmegroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private EditText signInEmail, signInPass;
    private Button signInBtn;
    private TextView sign_up_text;
    private TextView guest_text;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        signInEmail = findViewById(R.id.sign_in_email);
        signInPass = findViewById(R.id.sign_in_pass);
        signInBtn = findViewById(R.id.sign_in_btn);
        sign_up_text = findViewById(R.id.sign_up_txt);
        guest_text = findViewById(R.id.guest_txt);

        guest_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, Map.class));
                finish();
            }
        });

        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signInEmail.getText().toString();
                String pass = signInPass.getText().toString();
                SignInVal val = new SignInVal(email, pass);
                int validSignin = val.check();
                System.out.println(validSignin);
                if(validSignin == 1) {
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Log In Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else if(validSignin == 2) {
                    Toast.makeText(SignInActivity.this, "Email Cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(validSignin == 3) {
                    Toast.makeText(SignInActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(validSignin == 4) {
                    Toast.makeText(SignInActivity.this, "Password cannot be less than 7 characters", Toast.LENGTH_SHORT).show();
                }
                else if(validSignin == 5) {
                    Toast.makeText(SignInActivity.this, "Incorrect email format", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignInActivity.this, "Unknown input error. Please check input formatting.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}