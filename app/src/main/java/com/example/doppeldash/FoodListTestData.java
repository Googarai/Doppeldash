package com.example.doppeldash;

import java.util.Arrays;
import java.util.List;

public class FoodListTestData {

    public static final int DRAWABLE = R.drawable.test_food_image;

    private List<FoodItem> foodList = Arrays.asList(new FoodItem[]{
        new FoodItem(R.drawable.test_food_image, "TestFood1", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood2", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood3", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood4", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood5", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood6", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood7", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood8", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood9", "This is a placeholder", "$0.00"),
        new FoodItem(R.drawable.test_food_image, "TestFood10", "This is a placeholder", "$0.00")
    });

    public static int savedPositionFood = 0;

    private static FoodListTestData instance = null;

    public static FoodListTestData get(){
        if (instance == null){
            instance = new FoodListTestData();
        }
        return instance;
    }

    protected FoodListTestData(){}

    public FoodItem getItem(int i){
        return foodList.get(i);
    }

    public int getSize(){
        return foodList.size();
    }

}
