package com.example.testphase_loginregistration;

public class bus_class {
    int id;
    String name;
    String city;
    String stime;
    String etime;
//Alt+insert for making the constructor of bus_class
    //in constructor class name and constructor name must same.
    public bus_class(int id, String name, String city, String stime, String etime) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stime = stime;
        this.etime = etime;
    }
//Alt+Insert and make a getter and setter method.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getcity() {
        return city;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }
}
