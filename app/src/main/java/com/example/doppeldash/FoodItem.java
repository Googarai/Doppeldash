package com.example.doppeldash;

public class FoodItem {

    private final int drawableID;
    private String label;
    private String description;
    private String price;

    public FoodItem(int drawableID, String label, String description, String price){
        this.drawableID = drawableID;
        this.label = label;
        this.description = description;
        this.price = price;

    }

    public int getDrawableID(){return drawableID;}

    public String getLabel(){return label;}

    public String getDescription(){return description;}

    public String getPrice(){return price;}

}
