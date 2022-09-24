package com.example.doppeldash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.doppeldash.DoppeldashSchema.RestaurantTable;
import com.example.doppeldash.DoppeldashSchema.FoodTable;
import com.example.doppeldash.DoppeldashSchema.UserTable;
import com.example.doppeldash.DoppeldashSchema.OrderTable;
import com.example.doppeldash.DoppeldashSchema.OrderItemTable;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DoppeldashDatabase
{
    List<Restaurant> restaurants = new ArrayList<>();
    List<Food> food = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    List<OrderItem> orderItems = new ArrayList<>();
    SQLiteDatabase db;

    public void load(Context context)
    {
        this.db = new DBHelper(context.getApplicationContext()).getWritableDatabase();

        RestaurantCursor restaurantCursor = new RestaurantCursor(db.query(RestaurantTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            restaurantCursor.moveToFirst();
            while (!restaurantCursor.isAfterLast())
            {
                restaurants.add(restaurantCursor.getRestaurant());
                restaurantCursor.moveToNext();
            }
        }
        finally {
            restaurantCursor.close();
        }

        if (restaurants.isEmpty())
        {
            try
            {
                InputStreamReader inStream = new InputStreamReader(context.getResources().openRawResource(R.raw.restaurants));
                CSVReader csvReader = new CSVReader(inStream);
                String[] line;

                while ((line = csvReader.readNext()) != null)
                {
                    int id = Integer.parseInt(line[0]);
                    String name = line[1];
                    String image = line[2];
                    addRestaurant(new Restaurant(id, name, image));
                }

                inStream.close();
            }
            catch (IOException ignored) { }
        }

        FoodCursor foodCursor = new FoodCursor(db.query(FoodTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            foodCursor.moveToFirst();
            while (!foodCursor.isAfterLast())
            {
                food.add(foodCursor.getFood());
                foodCursor.moveToNext();
            }
        }
        finally {
            foodCursor.close();
        }

        if (food.isEmpty())
        {
            try
            {
                InputStreamReader inStream = new InputStreamReader(context.getResources().openRawResource(R.raw.food));
                CSVReader csvReader = new CSVReader(inStream);
                String[] line;

                while ((line = csvReader.readNext()) != null)
                {
                    int id = Integer.parseInt(line[0]);
                    String name = line[1];
                    String desc = line[2];
                    String image = line[3];
                    double price = Double.parseDouble(line[4]);
                    int restID = Integer.parseInt(line[5]);
                    addFood(new Food(id, name, desc, image, price, restID));
                }

                inStream.close();
            }
            catch (IOException ignored) { }
        }

        UserCursor userCursor = new UserCursor(db.query(UserTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            userCursor.moveToFirst();
            while (!userCursor.isAfterLast())
            {
                users.add(userCursor.getUser());
                userCursor.moveToNext();
            }
        }
        finally {
            userCursor.close();
        }

        OrderCursor orderCursor = new OrderCursor(db.query(OrderTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            orderCursor.moveToFirst();
            while (!orderCursor.isAfterLast())
            {
                orders.add(orderCursor.getOrder());
                orderCursor.moveToNext();
            }
        }
        finally {
            orderCursor.close();
        }

        OrderItemCursor orderItemCursor = new OrderItemCursor(db.query(OrderItemTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            orderItemCursor.moveToFirst();
            while (!orderItemCursor.isAfterLast())
            {
                orderItems.add(orderItemCursor.getOrderItem());
                orderItemCursor.moveToNext();
            }
        }
        finally {
            orderItemCursor.close();
        }
    }

    public void addRestaurant(Restaurant restaurant)
    {
        restaurants.add(restaurant);
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
        restaurants.remove(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant)
    {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantTable.Cols.ID, restaurant.getId());
        cv.put(RestaurantTable.Cols.NAME, restaurant.getName());
        cv.put(RestaurantTable.Cols.IMAGE, restaurant.getImage());
        String[] whereValue = { String.valueOf(restaurant.getId()) };
        db.update(RestaurantTable.NAME, cv, RestaurantTable.Cols.ID + " = ?", whereValue);
    }

    public class RestaurantCursor extends CursorWrapper
    {
        public RestaurantCursor(Cursor cursor) { super(cursor); }

        public Restaurant getRestaurant()
        {
            int id = getInt(getColumnIndex(RestaurantTable.Cols.ID));
            String name = getString(getColumnIndex(RestaurantTable.Cols.NAME));
            String image = getString(getColumnIndex(RestaurantTable.Cols.IMAGE));
            return new Restaurant(id, name, image);
        }
    }

    public void addFood(Food food)
    {
        this.food.add(food);
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
        this.food.remove(food);
    }

    public void updateFood(Food food)
    {
        ContentValues cv = new ContentValues();
        cv.put(FoodTable.Cols.ID, food.getId());
        cv.put(FoodTable.Cols.NAME, food.getName());
        cv.put(FoodTable.Cols.DESCRIPTION, food.getDescription());
        cv.put(FoodTable.Cols.IMAGE, food.getImage());
        cv.put(FoodTable.Cols.PRICE, food.getPrice());
        cv.put(FoodTable.Cols.RESTAURANT, food.getRestaurant());
        String[] whereValue = { String.valueOf(food.getId()) };
        db.update(FoodTable.NAME, cv, FoodTable.Cols.ID + " = ?", whereValue);
    }

    public class FoodCursor extends CursorWrapper
    {
        public FoodCursor(Cursor cursor) { super(cursor); }

        public Food getFood()
        {
            int id = getInt(getColumnIndex(FoodTable.Cols.ID));
            String name = getString(getColumnIndex(FoodTable.Cols.NAME));
            String description = getString(getColumnIndex(FoodTable.Cols.DESCRIPTION));
            String image = getString(getColumnIndex(FoodTable.Cols.IMAGE));
            double price = getDouble(getColumnIndex(FoodTable.Cols.PRICE));
            int restaurant = getInt(getColumnIndex(FoodTable.Cols.RESTAURANT));
            return new Food(id, name, description, image, price, restaurant);
        }
    }

    public void addUser(User user)
    {
        users.add(user);
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.NAME, user.getName());
        db.insert(UserTable.NAME, null, cv);
    }

    public void removeUser(User user)
    {
        String[] whereValue = { user.getEmail() };
        db.delete(UserTable.NAME, UserTable.Cols.EMAIL + " = ?", whereValue);
        users.remove(user);
    }

    public void updateUser(User user)
    {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.NAME, user.getName());
        String[] whereValue = { user.getEmail() };
        db.update(UserTable.NAME, cv, UserTable.Cols.EMAIL + " = ?", whereValue);
    }

    public class UserCursor extends CursorWrapper
    {
        public UserCursor(Cursor cursor) { super(cursor); }

        public User getUser()
        {
            String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
            String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
            String name = getString(getColumnIndex(UserTable.Cols.NAME));
            return new User(email, password, name);
        }
    }

    public void addOrder(Order order)
    {
        orders.add(order);
        ContentValues cv = new ContentValues();
        cv.put(OrderTable.Cols.ID, order.getId());
        cv.put(OrderTable.Cols.USER, order.getUser());
        cv.put(OrderTable.Cols.TOTALCOST, order.getTotalCost());
        cv.put(OrderTable.Cols.DATE, order.getDate());
        cv.put(OrderTable.Cols.TIME, order.getTime());
        db.insert(OrderTable.NAME, null, cv);
    }

    public void removeOrder(Order order)
    {
        String[] whereValue = { String.valueOf(order.getId()) };
        db.delete(OrderTable.NAME, OrderTable.Cols.ID + " = ?", whereValue);
        orders.remove(order);
    }

    public void updateOrder(Order order)
    {
        ContentValues cv = new ContentValues();
        cv.put(OrderTable.Cols.ID, order.getId());
        cv.put(OrderTable.Cols.USER, order.getUser());
        cv.put(OrderTable.Cols.TOTALCOST, order.getTotalCost());
        cv.put(OrderTable.Cols.DATE, order.getDate());
        cv.put(OrderTable.Cols.TIME, order.getTime());
        String[] whereValue = { String.valueOf(order.getId()) };
        db.update(OrderTable.NAME, cv, OrderTable.Cols.ID + " = ?", whereValue);
    }

    public class OrderCursor extends CursorWrapper
    {
        public OrderCursor(Cursor cursor) { super(cursor); }

        public Order getOrder()
        {
            int id = getInt(getColumnIndex(OrderTable.Cols.ID));
            String user = getString(getColumnIndex(OrderTable.Cols.USER));
            double totalCost = getDouble(getColumnIndex(OrderTable.Cols.TOTALCOST));
            String date = getString(getColumnIndex(OrderTable.Cols.DATE));
            String time = getString(getColumnIndex(OrderTable.Cols.TIME));
            return new Order(id, user, totalCost, date, time);
        }
    }

    public void addOrderItem(OrderItem orderItem)
    {
        orderItems.add(orderItem);
        ContentValues cv = new ContentValues();
        cv.put(OrderItemTable.Cols.ID, orderItem.getId());
        cv.put(OrderItemTable.Cols.FOOD, orderItem.getFood());
        cv.put(OrderItemTable.Cols.ORDER, orderItem.getOrder());
        db.insert(OrderItemTable.NAME, null, cv);
    }

    public void removeOrderItem(OrderItem orderItem)
    {
        String[] whereValue = { String.valueOf(orderItem.getId()) };
        db.delete(OrderItemTable.NAME, OrderItemTable.Cols.ID + " = ?", whereValue);
        orderItems.remove(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem)
    {
        ContentValues cv = new ContentValues();
        cv.put(OrderItemTable.Cols.ID, orderItem.getId());
        cv.put(OrderItemTable.Cols.FOOD, orderItem.getFood());
        cv.put(OrderItemTable.Cols.ORDER, orderItem.getOrder());
        String[] whereValue = { String.valueOf(orderItem.getId()) };
        db.update(OrderItemTable.NAME, cv, OrderItemTable.Cols.ID + " = ?", whereValue);
    }

    public class OrderItemCursor extends CursorWrapper
    {
        public OrderItemCursor(Cursor cursor) { super(cursor); }

        public OrderItem getOrderItem()
        {
            int id = getInt(getColumnIndex(OrderItemTable.Cols.ID));
            int food = getInt(getColumnIndex(OrderItemTable.Cols.FOOD));
            int order = getInt(getColumnIndex(OrderItemTable.Cols.ORDER));
            return new OrderItem(id, food, order);
        }
    }

    public Restaurant getRestaurant(int i)
    {
        return restaurants.get(i);
    }

    public int getNumRestaurants()
    {
        return restaurants.size();
    }

    public Food getFood(int i)
    {
        return food.get(i);
    }

    public int getNumFood()
    {
        return food.size();
    }

    public User getUser(int i)
    {
        return users.get(i);
    }

    public int getNumUsers()
    {
        return users.size();
    }

}