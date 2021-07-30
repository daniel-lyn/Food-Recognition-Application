package com.example.healthydiet.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodPost {

    @SerializedName("hints")
    @Expose
    private ArrayList<Parsed> parsed;

    public ArrayList<Parsed> getParsed() {
        return parsed;
    }

    public void setParsed(ArrayList<Parsed> parsed) {
        this.parsed = parsed;
    }

    @Override
    public String toString() {
        return "FoodPost{" +
                "parsed=" + parsed +
                '}';
    }
}
