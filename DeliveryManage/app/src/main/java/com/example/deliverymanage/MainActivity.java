package com.example.deliverymanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputEditText dname, dorderId, dphone, daddress, dloction;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dname = findViewById(R.id.dname);
        dorderId = findViewById(R.id.dId);
        dphone = findViewById(R.id.dPhone);
        daddress = findViewById(R.id.dAddress);
        dloction = findViewById(R.id.dLocation);


    }

    public void SubmitData(View view) {


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("delivery");

        String orderName = dname.getText().toString();
        String orderID = dorderId.getText().toString();
        String phone = dphone.getText().toString();
        String address = daddress.getText().toString();
        String location = dloction.getText().toString();

        if (!TextUtils.isEmpty(location)) {
            if (!TextUtils.isEmpty(address)) {
                if (!TextUtils.isEmpty(phone)) {
                    if (!TextUtils.isEmpty(orderID)) {
                        if (!TextUtils.isEmpty(orderName)) {


                            Delivery delivery = new Delivery(orderName, orderID, phone, address, location);

                            databaseReference.child(orderID).setValue(delivery);
                            Toast.makeText(com.example.deliverymanage.MainActivity.this, "Delivery Details filled Sucessfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                            intent.putExtra("name", dname.getText().toString());
                            intent.putExtra("orderID", dorderId.getText().toString());
                            intent.putExtra("phone", dphone.getText().toString());
                            intent.putExtra("address", daddress.getText().toString());
                            intent.putExtra("location", dloction.getText().toString());
                            startActivity(intent);

                        } else {
                            Toast.makeText(com.example.deliverymanage.MainActivity.this, "Please Enter Name", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(com.example.deliverymanage.MainActivity.this, "Please Enter Order ID ", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(com.example.deliverymanage.MainActivity.this, "Please Enter Phone", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(com.example.deliverymanage.MainActivity.this, "Please Enter Address", Toast.LENGTH_LONG).show();
            }

            } else{
                Toast.makeText(com.example.deliverymanage.MainActivity.this, "Please Enter Location", Toast.LENGTH_LONG).show();

            }

        }
    }
