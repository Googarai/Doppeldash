package com.example.doppeldash;

public class OrderItem {
    private int id;
    private int food;
    private int order;

    public OrderItem(int id, int food, int order)
    {
        this.id = id;
        this.food = food;
        this.order = order;
    }

    public int getId()
    {
        return id;
    }

    public int getFood()
    {
        return food;
    }

    public int getOrder()
    {
        return order;
    }

    public void setId(int id) { this.id = id; }

    public void setFood(int food) { this.food = food; }

    public void setOrder(int order) { this.order = order; }
}
