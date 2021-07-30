package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginButton;
    TextView mCreate;
    FirebaseAuth fAuth;
    ProgressBar mProgressBar;
    FirebaseDatabase rootNode;
    DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCreate = findViewById(R.id.txtCreate);
        mEmail = findViewById(R.id.registerEmail);
        mPassword = findViewById(R.id.registerPassword);
        mLoginButton = findViewById(R.id.loginButton);
        fAuth = FirebaseAuth.getInstance();
        mProgressBar = findViewById(R.id.progressBar2);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Please Input Your Email Here.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Please Input Your Password Here.");
                    return;
                }
                if(password.length() < 5){
                    mPassword.setError("Password Must Be At Least 6 Characters");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                // authenticate user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();
                            rootNode = FirebaseDatabase.getInstance();
                            reference2 = rootNode.getReference("UserTarget");
                            reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(!snapshot.child(uid).child("Kcal_Consumed").child("Date").exists()){
                                        reference2.child(uid).child("Kcal_Consumed").child("Date").setValue(0);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Toast.makeText(Login.this,"Login Successfully.",Toast.LENGTH_SHORT).show();
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                                startActivity(new Intent(getApplicationContext(),Home.class));
                                        }
                                    },
                                    2000
                            );
                        }else{
                            Toast.makeText(Login.this,"Please Try Again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    public void toCreate(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
}