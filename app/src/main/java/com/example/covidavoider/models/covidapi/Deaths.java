package com.example.covidavoider.models.covidapi;

import com.google.gson.annotations.SerializedName;

public class Deaths {
    @SerializedName("new")
    String change;
    @SerializedName("1M_pop")
    public double per1M;
    public int total;
}
