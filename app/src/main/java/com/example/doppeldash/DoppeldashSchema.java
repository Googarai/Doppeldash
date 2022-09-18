package com.example.doppeldash;

import android.database.Cursor;
import android.database.CursorWrapper;

public class DoppeldashSchema
{
    public static class RestaurantTable
    {
        public static final String NAME = "restaurants";
        public static class Cols
        {
            public static final String ID = "restaurant_id"; //Primary Key
            public static final String NAME = "name";
            public static final String IMAGE = "image";
        }
    }

    public static class FoodTable
    {
        public static final String NAME = "food";
        public static class Cols
        {
            public static final String ID = "food_id"; //Primary Key
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String IMAGE = "image";
            public static final String PRICE = "price";
            public static final String RESTAURANT = "restaurant"; //Reference Key to Restaurant Table
        }
    }

    public static class UserTable
    {
        public static final String NAME = "users";
        public static class Cols
        {
            public static final String EMAIL = "email"; //Primary Key
            public static final String PASSWORD = "password";
        }
    }

    public static class OrderTable
    {
        public static final String NAME = "orders";
        public static class Cols
        {
            public static final String ID = "order_id"; //Primary Key
            public static final String USER = "user"; //Reference Key to User Table
            public static final String TOTALCOST = "total_cost";
            public static final String TIME = "time";
            public static final String DATE = "date";
        }
    }

    public static class OrderItemTable
    {
        public static final String NAME = "order_items";
        public static class Cols
        {
            public static final String ID = "order_item_id"; //Primary Key
            public static final String FOOD = "food"; //Reference Key to Food Table
            public static final String ORDER = "order"; //Reference Key to Order Table
        }
    }
}
