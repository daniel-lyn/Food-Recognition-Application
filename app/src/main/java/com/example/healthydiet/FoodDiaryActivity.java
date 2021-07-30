package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.healthydiet.Model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodDiaryActivity extends AppCompatActivity {

    private static float progr = 0;
    private ProgressBar progressBar;
    TextView consumed, total,percent;
    ListView listView;
    static float consumedKcal;
    static int totalKcal;
    public static String combined;
    final List<String> foodList = new ArrayList<>();
    public static List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);
        total = findViewById(R.id.fdGiven);
        consumed = findViewById(R.id.fdConsumed);
        listView = findViewById(R.id.foodContentList);
        progressBar = findViewById(R.id.CircleBar);
        percent = findViewById(R.id.progress_percent);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                pullToRefresh.setRefreshing(false);
            }
        });
        Button toTargetButton;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserTarget").child(uid);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalKcal = Integer.parseInt(snapshot.child("Target").child("Kcal").getValue().toString());
                total.setText(String.valueOf(totalKcal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                consumedKcal = Float.parseFloat(snapshot.child("Kcal_Consumed").child("Consumed").getValue().toString());
                consumed.setText(String.valueOf(consumedKcal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toTargetButton = findViewById(R.id.toTarget);
        toTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TargetActivity.class));
                finish();
            }
        });
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        DecimalFormat df = new DecimalFormat("0.00");
                        FoodDiaryActivity.progr = (FoodDiaryActivity.consumedKcal/FoodDiaryActivity.totalKcal)*100;
                        System.out.println(progr);
                        progressBar.setMax(100);
                        progressBar.setProgress((int) FoodDiaryActivity.progr);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                percent.setText(df.format(FoodDiaryActivity.progr)+"%");
                                // Stuff that updates the UI

                            }
                        });
                    }
                },
                2000
        );

        reference2.child("Food").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                FoodDiaryActivity.list.clear();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    long count = snapshot.getChildrenCount();
                    String foodtime = childSnapshot.getKey().toString();
                    String foodinfo = childSnapshot.getValue().toString();
                    FoodDiaryActivity.combined = foodtime + foodinfo;
                    FoodDiaryActivity.list.add(FoodDiaryActivity.combined);
                    System.out.println(FoodDiaryActivity.combined+count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void refreshData() {
        ArrayAdapter<String> foodAdapter =
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, FoodDiaryActivity.list);
        listView.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(this, Home.class);
        this.startActivity(new_intent);
    }
}