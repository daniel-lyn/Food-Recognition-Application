package com.example.healthydiet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrBarActivity extends AppCompatActivity {

    Button btScan;
    public static String Upc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_bar);

        btScan = findViewById(R.id.bt_scan);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        QrBarActivity.this
                );
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );
        if(intentResult.getContents() != null){
            QrBarActivity.Upc = intentResult.getContents().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    QrBarActivity.this
            );
            builder.setTitle("Result:");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(),CaloriesUpcActivity.class));
                    finish();
                }
            });
            builder.show();
        }else {
            Toast.makeText(getApplicationContext(),"No Result Obtained",Toast.LENGTH_SHORT).show();
        }
        System.out.println(QrBarActivity.Upc);
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}