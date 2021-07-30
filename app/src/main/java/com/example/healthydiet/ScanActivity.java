package com.example.healthydiet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

    }

    public void toCamera(View view) {
        startActivity(new Intent(getApplicationContext(), CameraActivity.class));
        finish();
    }

    public void ScanPage(View view) {
        startActivity(new Intent(getApplicationContext(), QrBarActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }

}