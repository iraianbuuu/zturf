package com.example.zturf.xml;

public class Turf {
    public String name;
    public String date;
    public String time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n Date: " + date + "\n Time: " + time;
    }
}
