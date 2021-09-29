package com.example.debis_hut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StatusActivity extends AppCompatActivity {
    TextView dname, dphone, daddress, dloction;
    TextView dorderId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("delivery");

        dname = findViewById(R.id.rname);
        dorderId = findViewById(R.id.rID);
        dphone = findViewById(R.id.rTime);
        daddress = findViewById(R.id.raddress);
        dloction = findViewById(R.id.rfee);

        String name,id,phone,address,loction;

        name =getIntent().getStringExtra("name");
        id =getIntent().getStringExtra("orderID");
        phone =getIntent().getStringExtra("phone");
        address =getIntent().getStringExtra("address");
        loction =getIntent().getStringExtra("location");

        dname.setText(name);
        dorderId.setText(id);
        dphone.setText(phone);
        daddress.setText(address);
        dloction.setText(loction);
    }


    public void delete(View view) {
        String orderNum = dorderId.getText().toString().trim();

        databaseReference.child(orderNum).setValue(null);
        Toast.makeText(com.example.deliverymanage.StatusActivity.this, "Delivery Cancelled ", Toast.LENGTH_LONG).show();
        dorderId.setText("");
        dname.setText("");
        dorderId.setText("");
        dphone.setText("");
        dloction.setText("");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void Track(View view) {
        Intent intent = new Intent(getApplicationContext(), Track.class);
        startActivity(intent);
    }
}
