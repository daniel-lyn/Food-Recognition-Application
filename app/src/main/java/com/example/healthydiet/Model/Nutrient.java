package com.example.healthydiet.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nutrient {

    @SerializedName("ENERC_KCAL")
    @Expose
    private float calories;

    @SerializedName("PROCNT")
    @Expose
    private float Protein;

    @SerializedName("FAT")
    @Expose
    private float Fat;

    @SerializedName("CHOCDF")
    @Expose
    private float Carbs;

    @SerializedName("FIBTG")
    @Expose
    private float Fiber;

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return Protein;
    }

    public void setProtein(float protein) {
        Protein = protein;
    }

    public float getFat() {
        return Fat;
    }

    public void setFat(float fat) {
        Fat = fat;
    }

    public float getCarbs() {
        return Carbs;
    }

    public void setCarbs(float carbs) {
        Carbs = carbs;
    }

    public float getFiber() {
        return Fiber;
    }

    public void setFiber(float fiber) {
        Fiber = fiber;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "calories=" + calories +
                ", Protein=" + Protein +
                ", Fat=" + Fat +
                ", Carbs=" + Carbs +
                ", Fiber=" + Fiber +
                '}';
    }
}
