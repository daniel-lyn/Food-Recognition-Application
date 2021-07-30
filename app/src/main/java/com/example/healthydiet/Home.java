package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.healthydiet.Model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Home extends AppCompatActivity {
    long now = System.currentTimeMillis();
    static int dbDate;
    private String m_Text = "";
    int complete = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Calendar current = Calendar.getInstance();
        int currentHour = current.get(Calendar.HOUR_OF_DAY);
        int currentMin = current.get(Calendar.MINUTE);
        int currentDay = current.get(Calendar.DAY_OF_MONTH);

        System.out.println(currentDay);
        System.out.println(currentHour);
        System.out.println(currentMin);
        System.out.println(now);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserTarget").child(uid);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Home.dbDate = Integer.parseInt(snapshot.child("Kcal_Consumed").child("Date").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if(Home.dbDate != currentDay){
                            reference2.child("Kcal_Consumed").child("Consumed").setValue("0");
                            reference2.child("Kcal_Consumed").child("Date").setValue(currentDay);
                        }
                    }
                },
                2000
        );
    }

    public void Logout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Feedback");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                System.out.println(m_Text);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                reference.child("Feedback").child("Feedback").setValue(m_Text);
                dialog.dismiss();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        builder.setNegativeButton("N/A", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        builder.show();
        }


    public void News(View view) {
        startActivity(new Intent(getApplicationContext(), healthNewsActivity.class));
        finish();
    }

    public void Maps(View view) {
        startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
        finish();
    }

    public void Profile(View view) {
        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
        finish();
    }

    public void Recognition(View view) {
        startActivity(new Intent(getApplicationContext(), ScanActivity.class));
        finish();
    }

    public void FoodDiary(View view) {
        startActivity(new Intent(getApplicationContext(), FoodDiaryActivity.class));
        finish();
    }

    public void Target(View view) {
        startActivity(new Intent(getApplicationContext(), TargetActivity.class));
        finish();
    }

}