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
import android.widget.TextView;

import com.example.healthydiet.classifier.ImageClassifier;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    TextView name, email, height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.Profile_name);
        email = findViewById(R.id.Profile_email);
        height = findViewById(R.id.Profile_height);
        weight = findViewById(R.id.Profile_weight);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        System.out.println(uid);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dbname = snapshot.child("name").getValue().toString();
                name.setText(dbname);
                String dbEmail = snapshot.child("email").getValue().toString();
                email.setText(dbEmail);
                String dbHeight = snapshot.child("height").getValue().toString();
                height.setText(dbHeight);
                String dbWeight = snapshot.child("weight").getValue().toString();
                weight.setText(dbWeight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Edit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Profile");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Height", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                System.out.println(m_Text);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                reference.child("height").setValue(m_Text);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Weight", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                System.out.println(m_Text);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                reference.child("weight").setValue(m_Text);
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}