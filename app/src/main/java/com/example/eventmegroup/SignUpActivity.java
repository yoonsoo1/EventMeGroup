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

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSignUp, passSignUp;
    private Button signUpBtn;
    private TextView signInText;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        emailSignUp = findViewById(R.id.sign_in_email);
        passSignUp = findViewById(R.id.sign_in_pass);
        signUpBtn = findViewById(R.id.sign_in_btn);
        signInText = findViewById(R.id.sign_up_txt);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailSignUp.getText().toString();
                String pass = passSignUp.getText().toString();

                if(email.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please Enter a Valid Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, SetUpActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}