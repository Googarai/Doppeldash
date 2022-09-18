package com.example.doppeldash;

public class Order {
    private int id;
    private String user;
    private double totalCost;
    private String date;
    private String time;

    public Order(int id, String user, double totalCost, String date, String time)
    {
        this.id = id;
        this.user = user;
        this.totalCost = totalCost;
        this.date = date;
        this.time = time;
    }

    public int getId()
    {
        return id;
    }

    public String getUser()
    {
        return user;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public void setId(int id) { this.id = id; }

    public void setUser(String password) { this.user = user; }

    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public void setDate(String date) { this.date = date; }

    public void setTime(String time) { this.time = time; }
}
