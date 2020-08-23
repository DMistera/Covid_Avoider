package com.example.covidavoider.models.covidapi;

import com.google.gson.annotations.SerializedName;

public class Cases {
    @SerializedName("new")
    public String change;
    public int active;
    public int critical;
    public int recovered;
    @SerializedName("1M_pop")
    public double per1M;
    public int total;
}
