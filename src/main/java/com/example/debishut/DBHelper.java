package com.example.debishut;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "restaurant_db";
    public static final String TABLE_NAME = "payment";
    public static final String COL_2 = "FullName";
    public static final String COL_3 = "ContactNo";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "PaymentMethod";
    public static final String COL_6 = "CardNo";
    public static final String COL_7 = "CVC";
    public static final String COL_8 = "Payment";

    public DBHelper(Context context){super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, ContactNo TEXT, Email TEXT, PaymentMethod TEXT, CardNo TEXT, CVC INTEGER, Payment TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
