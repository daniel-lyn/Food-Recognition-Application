package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydiet.Model.Food;
import com.example.healthydiet.Model.FoodHolderApi;
import com.example.healthydiet.Model.FoodPost;
import com.example.healthydiet.Model.Nutrient;
import com.example.healthydiet.Model.Parsed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaloriesActivity extends AppCompatActivity {

    TextView foodName, calories, protein, fat, carbs, fiber;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button mDiaryButton, mCameraButton;
    static public float calories_;
    static public float Protein_;
    static public float Fat_;
    static public float Carbs_;
    static public float Fiber_;
    static public float consumed;
    boolean completed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        foodName = findViewById(R.id.caloriesText);
        foodName.setText(CameraActivity.chosenFood+" (100g) Nutrient Information:");

        calories = findViewById(R.id.calories_get);

        protein = findViewById(R.id.protein_get);

        fat = findViewById(R.id.fat_get);

        carbs = findViewById(R.id.crabs_get);

        fiber = findViewById(R.id.fiber_get);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://edamam-food-and-grocery-database.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodHolderApi foodHolderApi = retrofit.create(FoodHolderApi.class);

        Call<FoodPost> call = foodHolderApi.getParsed(CameraActivity.chosenFood);

        call.enqueue(new Callback<FoodPost>() {
            @Override
            public void onResponse(Call<FoodPost> call, Response<FoodPost> response) {
                ArrayList<Parsed> parseds = response.body().getParsed();
                CaloriesActivity.calories_ = parseds.get(0).getFood().getNutrient().getCalories();
                System.out.println("calories is :" + calories_);
                calories.setText("Calories :  " + calories_);

                CaloriesActivity.Protein_ = parseds.get(0).getFood().getNutrient().getProtein();
                System.out.println("Protein is :" + Protein_);
                if(Protein_ >= 10){
                    protein.setText("Protein :  " + Protein_);
                    protein.setTextColor(Color.parseColor("#00FF00"));
                } else if (Protein_ < 10) {
                    protein.setText("Protein :  " + Protein_);
                    protein.setTextColor(Color.parseColor("#FF0000"));
                }

                CaloriesActivity.Fat_ = parseds.get(0).getFood().getNutrient().getFat();
                System.out.println("Fat is :" + Fat_);
                if(Fat_ <= 10){
                    fat.setTextColor(Color.parseColor("#00FF00"));
                    fat.setText("Fat :  " + Fat_);
                }else if(Fat_ > 10){
                    fat.setTextColor(Color.parseColor("#FF0000"));
                    fat.setText("Fat :  " + Fat_);
                }

                CaloriesActivity.Carbs_ = parseds.get(0).getFood().getNutrient().getCarbs();
                System.out.println("Carbs is :" + Carbs_);
                carbs.setText("Carbohydrate :  " + Carbs_);

                CaloriesActivity.Fiber_ = parseds.get(0).getFood().getNutrient().getFiber();
                System.out.println("Fiber is :" + Fiber_);
                if(Fiber_ > 3 ){
                    fiber.setTextColor(Color.parseColor("#00FF00"));
                    fiber.setText("Fiber :  " + Fiber_);
                }else if(Fiber_ <= 3){
                    fiber.setTextColor(Color.parseColor("#FF0000"));
                    fiber.setText("Fiber :  " + Fiber_);
                }
            }

            @Override
            public void onFailure(Call<FoodPost> call, Throwable t) {
                System.out.println("onFailure: Wrong:" + t.getMessage());
            }

        });

    }

    public void AddFoodLog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How many (g) did you eat?");
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float percentTaken;
                percentTaken = Integer.parseInt(input.getText().toString());
                System.out.println(percentTaken);
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(currentTime);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("UserTarget");
                reference.child(uid).child("Food").child(formattedDate).setValue(CameraActivity.chosenFood+"  " + CaloriesActivity.calories_*(percentTaken/100));
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        CaloriesActivity.consumed = Float.parseFloat(snapshot.child(uid).child("Kcal_Consumed").child("Consumed").getValue().toString());
                        completed = true;
                        System.out.println(CaloriesActivity.consumed);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                CaloriesActivity.consumed = CaloriesActivity.consumed + CaloriesActivity.calories_*(percentTaken/100);
                                reference.child(uid).child("Kcal_Consumed").child("Consumed").setValue(CaloriesActivity.consumed);
                            }
                        },
                        2000
                );
                dialog.dismiss();
                Toast.makeText(CaloriesActivity.this,"Food Added To Food Diary" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void captureAgain(View view){
        startActivity(new Intent(getApplicationContext(), CameraActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}