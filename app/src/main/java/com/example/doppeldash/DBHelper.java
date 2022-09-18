package com.example.doppeldash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.doppeldash.DoppeldashSchema.RestaurantTable;
import com.example.doppeldash.DoppeldashSchema.FoodTable;
import com.example.doppeldash.DoppeldashSchema.UserTable;
import com.example.doppeldash.DoppeldashSchema.OrderTable;
import com.example.doppeldash.DoppeldashSchema.OrderItemTable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "doppeldash.db";

    public DBHelper(Context context)
    {
            super(context, DATABASE_NAME, null, VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + RestaurantTable.NAME + "(" +
                RestaurantTable.Cols.ID + " INTEGER, " +
                RestaurantTable.Cols.NAME + " TEXT, " +
                RestaurantTable.Cols.IMAGE + "TEXT)");
        db.execSQL("CREATE TABLE " + FoodTable.NAME + "(" +
                FoodTable.Cols.ID + " INTEGER, " +
                FoodTable.Cols.NAME + " TEXT, " +
                FoodTable.Cols.DESCRIPTION + " TEXT, " +
                FoodTable.Cols.IMAGE + " TEXT, " +
                FoodTable.Cols.PRICE + " REAL, " +
                FoodTable.Cols.RESTAURANT + " INTEGER)");
        db.execSQL("CREATE TABLE " + UserTable.NAME + "(" +
                UserTable.Cols.EMAIL + " TEXT, " +
                UserTable.Cols.PASSWORD + " TEXT)");
        db.execSQL("CREATE TABLE " + OrderTable.NAME + "(" +
                OrderTable.Cols.ID + " INTEGER, " +
                OrderTable.Cols.USER + " TEXT, " +
                OrderTable.Cols.TOTALCOST + " REAL, " +
                OrderTable.Cols.DATE + " TEXT, " +
                OrderTable.Cols.TIME + " TEXT)");
        db.execSQL("CREATE TABLE " + OrderItemTable.NAME + "(" +
                OrderItemTable.Cols.ID + " INTEGER, " +
                OrderItemTable.Cols.FOOD + " INTEGER, " +
                OrderItemTable.Cols.ORDER + " INTEGER)");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int i, int j) { }
}