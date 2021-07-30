package com.example.healthydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydiet.Model.FoodHolderApi;
import com.example.healthydiet.Model.FoodPost;
import com.example.healthydiet.Model.Parsed;
import com.example.healthydiet.Model.Parsedupc;
import com.example.healthydiet.Model.UpcFood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaloriesUpcActivity extends AppCompatActivity {

    TextView foodName1, calories1, protein1, fat1, carbs1, fiber1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    static public float calories_upc;
    static public float Protein_upc;
    static public float Fat_upc;
    static public float Carbs_upc;
    static public float Fiber_upc;
    static public float consumed;
    static public String chosenFoodUpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_upc);
        foodName1 = findViewById(R.id.caloriesText_upc);

        calories1 = findViewById(R.id.calories_get_upc);

        protein1 = findViewById(R.id.protein_get_upc);

        fat1 = findViewById(R.id.fat_get_upc);

        carbs1 = findViewById(R.id.crabs_get_upc);

        fiber1 = findViewById(R.id.fiber_get_upc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://edamam-food-and-grocery-database.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodUpcHolderApi foodHolderApi = retrofit.create(FoodUpcHolderApi.class);

        Call<UpcFood> call = foodHolderApi.getParsedUpc(QrBarActivity.Upc);

        call.enqueue(new Callback<UpcFood>() {
            @Override
            public void onResponse(Call<UpcFood> call, Response<UpcFood> response) {
                ArrayList<Parsedupc> parseds = null;
                if(response.isSuccessful()) {
                    parseds = response.body().getParsedUpc();
                    CaloriesUpcActivity.calories_upc = parseds.get(0).getFood().getNutrient().getCalories();
                    System.out.println("calories is :" + calories_upc);
                    calories1.setText("Calories :  " + CaloriesUpcActivity.calories_upc);

                    CaloriesUpcActivity.Protein_upc = parseds.get(0).getFood().getNutrient().getProtein();
                    System.out.println("Protein is :" + Protein_upc);
                    if(Protein_upc >= 10){
                        protein1.setText("Protein :  " + Protein_upc);
                        protein1.setTextColor(Color.parseColor("#00FF00"));
                    } else if (Protein_upc < 10) {
                        protein1.setText("Protein :  " + Protein_upc);
                        protein1.setTextColor(Color.parseColor("#FF0000"));
                    }

                    CaloriesUpcActivity.Fat_upc = parseds.get(0).getFood().getNutrient().getFat();
                    System.out.println("Fat is :" + Fat_upc);
                    if(Fat_upc <= 10){
                        fat1.setTextColor(Color.parseColor("#00FF00"));
                        fat1.setText("Fat :  " + Fat_upc);
                    }else if(Fat_upc > 10){
                        fat1.setTextColor(Color.parseColor("#FF0000"));
                        fat1.setText("Fat :  " + Fat_upc);
                    }

                    CaloriesUpcActivity.Carbs_upc = parseds.get(0).getFood().getNutrient().getCarbs();
                    System.out.println("Carbs is :" + Carbs_upc);
                    carbs1.setText("Carbohydrate :  " + Carbs_upc);

                    CaloriesUpcActivity.Fiber_upc = parseds.get(0).getFood().getNutrient().getFiber();
                    System.out.println("Fiber is :" + Fiber_upc);
                    if(Fiber_upc > 3 ){
                        fiber1.setTextColor(Color.parseColor("#00FF00"));
                        fiber1.setText("Fiber :  " + Fiber_upc);
                    }else if(Fiber_upc <= 3){
                        fiber1.setTextColor(Color.parseColor("#FF0000"));
                        fiber1.setText("Fiber :  " + Fiber_upc);
                    }
                    CaloriesUpcActivity.chosenFoodUpc = parseds.get(0).getFood().getLabel();
                    foodName1.setText(CaloriesUpcActivity.chosenFoodUpc + " Nutrient Information:");
                }else{
                    foodName1.setText("No information about scanned object");
                }
            }

            @Override
            public void onFailure(Call<UpcFood> call, Throwable t) {
                System.out.println("onFailure: Wrong:" + t.getMessage());
            }

        });

    }


    public void AddFoodLog(View view) {
        if (CaloriesUpcActivity.calories_upc == 0) {
            Toast.makeText(CaloriesUpcActivity.this, "No food can be added.", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("How many % did you eat? (100 for all) ");
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
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(currentTime);
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("UserTarget");
                    reference.child(uid).child("Food").child(formattedDate).setValue(CaloriesUpcActivity.chosenFoodUpc + "  " + CaloriesUpcActivity.calories_upc * (percentTaken / 100));
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            CaloriesUpcActivity.consumed = Integer.parseInt(snapshot.child(uid).child("Kcal_Consumed").child("Consumed").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    CaloriesUpcActivity.consumed = CaloriesUpcActivity.consumed + CaloriesUpcActivity.calories_upc * (percentTaken / 100);
                                    reference.child(uid).child("Kcal_Consumed").child("Consumed").setValue(CaloriesUpcActivity.consumed);
                                }
                            },
                            2000
                    );
                    dialog.dismiss();
                    Toast.makeText(CaloriesUpcActivity.this, "Food Added To Food Diary", Toast.LENGTH_SHORT).show();
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
    }
    public void captureAgainupc(View view){
        startActivity(new Intent(getApplicationContext(), QrBarActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(this, Home.class);

        this.startActivity(new_intent);

    }
}
