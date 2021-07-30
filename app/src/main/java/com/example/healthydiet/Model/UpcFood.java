package com.example.healthydiet.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpcFood {

    @SerializedName("hints")
    @Expose
    private ArrayList<Parsedupc> Parsedupc = null;

    public ArrayList<Parsedupc> getParsedUpc() {
        if (Parsedupc != null) {
            return Parsedupc;
        }else{
            return null;
        }
    }

    public void setParsedupc(ArrayList<Parsedupc> Parsedupc) {
        this.Parsedupc = Parsedupc;
    }

    @Override
    public String toString() {
        return "FoodPost{" +
                "Parsedupc=" + Parsedupc +
                '}';
    }
}
