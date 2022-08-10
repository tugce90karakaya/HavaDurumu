package com.example.havadurumu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class wind {
    @SerializedName("speed")
    @Expose
    private float speed;

    @SerializedName("deg")
    @Expose
    private int deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
