package com.robot.weconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.robot.weconnect.Models.User;

public class SignUpActivity extends AppCompatActivity {

    //Reference variables from layout
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    TextView tvSignIn;
    Button btSignUp;
    ProgressDialog progressDialog;

    //Firebase variables
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etUsername = findViewById(R.id.user2);
        etEmail = findViewById(R.id.email2);
        etPassword = findViewById(R.id.pass2);
        tvSignIn = findViewById(R.id.open_sign_in);
        btSignUp = findViewById(R.id.sign_up);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please wait while we create your account");

        //Opens the sign ip activity when clicked on "Already have an account" textview.
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    User user = new User(username, email, password);
                                    String uid = task.getResult().getUser().getUid();
                                    database.getReference().child("users").child(uid).setValue(user);

                                    Toast.makeText(SignUpActivity.this, "Account created Successfully",
                                            Toast.LENGTH_SHORT).show();
                                    mAuth.signOut();
                                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}