package com.example.havadurumu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class coord {
  @SerializedName("lon")
  @Expose
  private float boylam;

  @SerializedName("lat")
  @Expose
  private float enlem;

  public float getBoylam() {
    return boylam;
  }

  public void setBoylam(float boylam) {
    this.boylam = boylam;
  }

  public float getEnlem() {
    return enlem;
  }

  public void setEnlem(float enlem) {
    this.enlem = enlem;
  }

}
