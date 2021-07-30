package com.example.healthydiet.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("nutrients")
    @Expose
    private Nutrient nutrient;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @SerializedName("label")
    @Expose
    private String label;

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }


    @Override
    public String toString() {
        return "Food{" +
                "nutrient=" + nutrient +
                '}';
    }
}
