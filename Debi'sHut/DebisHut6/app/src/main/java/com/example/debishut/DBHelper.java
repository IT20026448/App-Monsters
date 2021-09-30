package com.example.debishut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Restaurant.db";

    public DBHelper(Context context){super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BillingController.Bill.TABLE_NAME + " (" +
                        BillingController.Bill._ID + " INTEGER PRIMARY KEY," +
                        BillingController.Bill.COLUMN_NAME_fullname + " TEXT," +
                        BillingController.Bill.COLUMN_NAME_contact + " TEXT," +
                        BillingController.Bill.COLUMN_NAME_email + " TEXT," +
                        BillingController.Bill.COLUMN_NAME_cardNo + " TEXT," +
                        BillingController.Bill.COLUMN_NAME_cvc + " TEXT," +
                        BillingController.Bill.COLUMN_NAME_payMeth + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String full_Name, String contact, String Email, String card_no, String cvcNo, String pay) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BillingController.Bill.COLUMN_NAME_fullname, full_Name);
        values.put(BillingController.Bill.COLUMN_NAME_contact, contact);
        values.put(BillingController.Bill.COLUMN_NAME_email, Email);
        values.put(BillingController.Bill.COLUMN_NAME_cardNo, card_no);
        values.put(BillingController.Bill.COLUMN_NAME_cvc, cvcNo);
        values.put(BillingController.Bill.COLUMN_NAME_payMeth, pay);

        return db.insert(BillingController.Bill.TABLE_NAME, null, values);
    }

    public List readAllInfo(){
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {
                BillingController.Bill._ID,
                BillingController.Bill.COLUMN_NAME_fullname,
                BillingController.Bill.COLUMN_NAME_contact,
                BillingController.Bill.COLUMN_NAME_email,
                BillingController.Bill.COLUMN_NAME_cardNo,
                BillingController.Bill.COLUMN_NAME_cvc,
                BillingController.Bill.COLUMN_NAME_payMeth
        };

        String sortOrder = BillingController.Bill._ID + " DESC";

        Cursor cursor = db.query(
                BillingController.Bill.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List info = new ArrayList<>();

        while (cursor.moveToNext()){
            String fullname = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_fullname));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_contact));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_email));
            String cardno = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_cardNo));
            String cvc = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_cvc));
            String paymeth = cursor.getString(cursor.getColumnIndexOrThrow(BillingController.Bill.COLUMN_NAME_payMeth));

            info.add(fullname + ":" + contact + ":" + email + ":" + cardno + ":" + cvc + ":" + paymeth + ":");
        }
        cursor.close();
        return info;
    }

    public void deleteInfo(String full_Name, String contact, String Email, String card_no, String cvcNo, String pay){

    }
}