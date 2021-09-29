package com.example.debis_hut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText fullName, email, phone, cardNo, cvc;
    RadioButton cash, card;
    String payment;
    Button proceed, cancel;
    String s1[], s2[];
    int images[] = {R.drawable.pizza, R.drawable.lasagna, R.drawable.lava_cake, R.drawable.chicken_wings, R.drawable.garlic_bread, R.drawable.birthday_cakes};
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_page);
        Log.d(TAG, "onCreate: started");
        fullName = findViewById(R.id.full_name);
        phone = findViewById(R.id.phone_no);
        email = findViewById(R.id.email_address);
        cardNo = findViewById(R.id.card_number);
        cvc = findViewById(R.id.cvc_no);
        proceed = findViewById(R.id.proceed);
        cancel = findViewById(R.id.cancel);
        recyclerView = findViewById(R.id.recyclerView);

        //s1 = getResources().getStringArray(R.array.items);
        //s2 = getResources().getStringArray(R.array.description);

        //MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        //recyclerView.setAdapter(myAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyAdapter.class));
            }
        });*/
    }

    public void saveBill(View view){
            String name = fullName.getText().toString();
            String contactNo = phone.getText().toString();
            String emailAd = email.getText().toString();
            String cno = cardNo.getText().toString();
            String cvc_no = cvc.getText().toString();
            String pay = payment;
            DBHelper dbHelper = new DBHelper(this);

            if(cash.isChecked()){
                payment = "cash";
            }else if(card.isChecked()){
                payment = "card";
            }else{
                Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
            }

            if(name.isEmpty() || contactNo.isEmpty() || emailAd.isEmpty() || cno.isEmpty() || cvc_no.isEmpty() || pay.isEmpty()){
                Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
            }else{
                long inserted = dbHelper.addInfo(name, contactNo, emailAd, cno, cvc_no, payment);

                if(inserted > 0){
                    Toast.makeText(this, "Data inserted successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /*public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Billing details");

        builder.setItems(infoArray, newDialogInterface.OnCl)
    }*/
}