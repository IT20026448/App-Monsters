package com.example.debis_hut.database;

import android.provider.BaseColumns;

public class BillingController {
    private BillingController(){}

    public static class Bill implements BaseColumns{
        public static final String TABLE_NAME = "payment";
        public static final String COLUMN_NAME_fullname = "fullname";
        public static final String COLUMN_NAME_contact = "contact";
        public static final String COLUMN_NAME_email = "email";
        public static final String COLUMN_NAME_cardNo = "cardNo";
        public static final String COLUMN_NAME_cvc = "cvc";
        public static final String COLUMN_NAME_payMeth = "payMeth";

    }
}
