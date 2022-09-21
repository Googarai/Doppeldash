package com.example.doppeldash;

import java.util.Arrays;
import java.util.List;

public class RestaurantData {

    private static final int DRAWABLE = R.drawable.test_food_image;

    private List<Restaurant> restList = Arrays.asList(new Restaurant[]{
            new Restaurant(11111111, "RestaurantName1", "test_food_image"),
            new Restaurant(11111111, "RestaurantName2", "test_food_image"),
            new Restaurant(11111111, "RestaurantName3", "test_food_image"),
            new Restaurant(11111111, "RestaurantName4", "test_food_image"),
            new Restaurant(11111111, "RestaurantName5", "test_food_image"),
            new Restaurant(11111111, "RestaurantName6", "test_food_image"),
            new Restaurant(11111111, "RestaurantName7", "test_food_image"),
            new Restaurant(11111111, "RestaurantName8", "test_food_image"),
            new Restaurant(11111111, "RestaurantName9", "test_food_image"),
            new Restaurant(11111111, "RestaurantName10", "test_food_image"),
    });

    private static RestaurantData instance = null;

    public static int savedPositionRest = 0;

    public static RestaurantData get(){
        if (instance == null){
            instance = new RestaurantData();
        }
        return instance;
    }

    protected RestaurantData(){}

    public Restaurant getRestaurant(int i) {return restList.get(i);}

    public int getSize() {return restList.size();}






}
