package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentResult;

public class TargetActivity extends AppCompatActivity {

    EditText mTarget;
    Button mGain, mLose;
    FirebaseDatabase rootNode;
    DatabaseReference reference,reference2;
    public static int kcal;
    private String mSex;
    private int mHeight, mWeight, mAge;
    float finalValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        mTarget = findViewById(R.id.targetValue);
        mGain = findViewById(R.id.gainButton);
        mLose = findViewById(R.id.loseButton);

        mGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target = mTarget.getText().toString();
                finalValue=Float.parseFloat(target);
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("User");
                reference2 = rootNode.getReference("UserTarget");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                GainTargetHelper gainTargetHelper = new GainTargetHelper(finalValue);
                reference2.child(uid).child("Target").setValue(gainTargetHelper);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mSex = snapshot.child(uid).child("sex").getValue().toString();
                        mAge = Integer.parseInt(snapshot.child(uid).child("age").getValue().toString());
                        mHeight = Integer.parseInt(snapshot.child(uid).child("height").getValue().toString());
                        mWeight = Integer.parseInt(snapshot.child(uid).child("weight").getValue().toString());
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        if(mSex.equals("M")){
                            if(mAge < 18){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1996;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2261;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2774;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2200;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2453;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2940;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                        finish();
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2336;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2563;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 3059;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 30){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1915;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2102;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2610;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2119;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2381;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2869;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2315;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2563;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 3059;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 50){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1779;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1953;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2394;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1983;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2246;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2787;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2200;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2453;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2940;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1637;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1797;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2188;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1840;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2020;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2460;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2057;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2258;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2822;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else{
                            if(mAge < 18){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1771;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1944;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2368;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1975;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2255;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2774;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2192;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2444;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2949;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 30){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1690;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1900;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2498;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1894;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2078;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2531;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2111;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2316;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2821;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 50){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1555;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1706;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2078;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1758;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1929;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2350;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1975;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2168;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2640;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1413;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1550;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1888;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1616;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1773;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2159;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1832;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 2011;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 2449;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        mLose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target = mTarget.getText().toString();
                finalValue=Float.parseFloat(target);
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("User");
                reference2 = rootNode.getReference("UserTarget");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                LoseTargetHelper loseTargetHelper = new LoseTargetHelper(finalValue*-1);
                reference2.child(uid).child("Target").setValue(loseTargetHelper);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mSex = snapshot.child(uid).child("sex").getValue().toString();
                        mAge = Integer.parseInt(snapshot.child(uid).child("age").getValue().toString());
                        mHeight = Integer.parseInt(snapshot.child(uid).child("height").getValue().toString());
                        mWeight = Integer.parseInt(snapshot.child(uid).child("weight").getValue().toString());
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        if(mSex.equals("M")){
                            if(mAge < 18){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1517;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1267;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 767;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Too Less",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1697;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1447;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 947;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1889;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1639;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1139;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 30){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1692;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1442;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 942;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1898;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1648;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1148;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 2118;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1868;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1368;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 50){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1555;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1305;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 805;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1761;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1511;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1011;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1981;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1731;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1231;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1444;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1228;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 813;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1617;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1367;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 867;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1837;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1587;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1087;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else{
                            if(mAge < 18){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1318;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1068;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 568;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1498;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1248;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 748;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1690;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1440;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 940;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 30){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1246;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 996;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 496;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1426;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1176;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 676;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1618;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1368;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 868;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else if(mAge < 50){
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1326;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1076;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 576;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1533;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1283;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 783;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1753;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1503;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 1003;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                if(mWeight < 50){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1186;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 981;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 695;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else if(mWeight < 65){
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1320;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1070;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 570;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if(finalValue < 0.25){
                                        TargetActivity.kcal = 1540;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue < 0.5){
                                        TargetActivity.kcal = 1290;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else if (finalValue <=1){
                                        TargetActivity.kcal = 790;
                                        reference2.child(uid).child("Target").child("Kcal").setValue(TargetActivity.kcal);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(TargetActivity.this,"Having a weight change >1(KG) is not recommended in a week, please set a smaller target",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}