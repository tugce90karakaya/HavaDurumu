package com.example.havadurumu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnaSinif {
    @SerializedName("coord")
    @Expose
    private coord koordinat;

    @SerializedName("weather")
    @Expose
    private ArrayList<weather> hava;

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("main")
    @Expose
    private main main_class;

    @SerializedName("visibility")
    @Expose
    private int visibility;

    @SerializedName("wind")
    @Expose
    private wind ruzgar;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("dt")
    @Expose
    private int dt;

    @SerializedName("sys")
    @Expose
    private Sys sys;

    @SerializedName("timezone")
    @Expose
    private int timezone;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cod")
    @Expose
    private String cod;

    public coord getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(coord koordinat) {
        this.koordinat = koordinat;
    }

    public ArrayList<weather> getHava() {
        return hava;
    }

    public void setHava(ArrayList<weather> hava) {
        this.hava = hava;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public main getMain_class() {
        return main_class;
    }

    public void setMain_class(main main_class) {
        this.main_class = main_class;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public wind getRuzgar() {
        return ruzgar;
    }

    public void setRuzgar(wind ruzgar) {
        this.ruzgar = ruzgar;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

}
