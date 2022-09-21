package com.example.doppeldash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import com.example.doppeldash.DoppeldashSchema.RestaurantTable;
import com.example.doppeldash.DoppeldashSchema.FoodTable;
import java.util.List;

public class Doppeldash
{
    private List<Restaurant> restaurantList;
    private List<Food> food;
    private SQLiteDatabase db;

    public void load(Context context)
    {
        this.db = new DBHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void addRestaurant(Restaurant restaurant)
    {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantTable.Cols.ID, restaurant.getId());
        cv.put(RestaurantTable.Cols.NAME, restaurant.getName());
        cv.put(RestaurantTable.Cols.IMAGE, restaurant.getImage());
        db.insert(RestaurantTable.NAME, null, cv);
    }

    public void removeRestaurant(Restaurant restaurant)
    {
        String[] whereValue = { String.valueOf(restaurant.getId()) };
        db.delete(RestaurantTable.NAME, RestaurantTable.Cols.ID + " = ?", whereValue);
    }

    public void updateRestaurant(Restaurant restaurant)
    {
        ContentValues cv = new ContentValues();
        String[] whereValue = { String.valueOf(restaurant.getId()) };
        db.update(RestaurantTable.NAME, cv, RestaurantTable.Cols.ID + " = ?", whereValue);
    }

    public class RestaurantCursor extends CursorWrapper
    {
        public RestaurantCursor(Cursor cursor) { super(cursor); }

        public Restaurant getRestaurant()
        {
            int id = getInt(getColumnIndex(DoppeldashSchema.RestaurantTable.Cols.ID));
            String name = getString(getColumnIndex(DoppeldashSchema.RestaurantTable.Cols.NAME));
            String image = getString(getColumnIndex(DoppeldashSchema.RestaurantTable.Cols.IMAGE));
            return new Restaurant(id, name, image);
        }
    }

    public void addFood(Food food)
    {
        ContentValues cv = new ContentValues();
        cv.put(FoodTable.Cols.ID, food.getId());
        cv.put(FoodTable.Cols.NAME, food.getName());
        cv.put(FoodTable.Cols.DESCRIPTION, food.getDescription());
        cv.put(FoodTable.Cols.IMAGE, food.getImage());
        cv.put(FoodTable.Cols.PRICE, food.getPrice());
        cv.put(FoodTable.Cols.RESTAURANT, food.getRestaurant());
        db.insert(FoodTable.NAME, null, cv);
    }

    public void removeFood(Food food)
    {
        String[] whereValue = { String.valueOf(food.getId()) };
        db.delete(FoodTable.NAME, FoodTable.Cols.ID + " = ?", whereValue);
    }

    public void updateFood(Food food)
    {
        ContentValues cv = new ContentValues();
        String[] whereValue = { String.valueOf(food.getId()) };
        db.update(FoodTable.NAME, cv, FoodTable.Cols.ID + " = ?", whereValue);
    }
}
