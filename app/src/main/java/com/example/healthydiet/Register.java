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

public class Register extends AppCompatActivity {
    private static String name,email;
    EditText mName, mEmail, mPassword, mSex, mHeight, mWeight, mAge;
    Button mRegisterButton;
    TextView mLogin;
    FirebaseAuth fAuth;
    ProgressBar mProgressBar;
    public static String regSex,regName,regHeight,regEmail,regWeight,regAge;
    FirebaseDatabase rootNode;
    DatabaseReference reference,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = findViewById(R.id.registerName);
        mEmail = findViewById(R.id.registerEmail);
        mPassword = findViewById(R.id.registerPassword);
        mSex = findViewById(R.id.registerSex);
        mHeight = findViewById(R.id.registerHeight);
        mWeight = findViewById(R.id.registerWeight);
        mRegisterButton = findViewById(R.id.registerButton);
        mLogin = findViewById(R.id.txtCreate);
        mAge= findViewById(R.id.registerAge);
        fAuth = FirebaseAuth.getInstance();
        mProgressBar = findViewById(R.id.progressBar);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString().trim();
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
                name = mName.getText().toString();
                regName = mName.getText().toString();
                regSex = mSex.getText().toString();
                regHeight = mHeight.getText().toString();
                regEmail = mEmail.getText().toString();
                regWeight = mWeight.getText().toString();
                regAge = mAge.getText().toString();
                // start register in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),TargetActivity.class));
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("User");
                            reference2 = rootNode.getReference("UserTarget");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();
                            UserHelperClass userHelperClass = new UserHelperClass(regName, regEmail, regSex, regHeight, regWeight,regAge);
                            reference.child(uid).setValue(userHelperClass);
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
                            reference2.child(uid).child("Kcal_Consumed").child("Consumed").setValue("0");
                        }else{
                            Toast.makeText(Register.this,"Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }

    public void toLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public static String getUserName() {
        return name;
    }

    public static String getUserEmail() {
        return email;
    }


}