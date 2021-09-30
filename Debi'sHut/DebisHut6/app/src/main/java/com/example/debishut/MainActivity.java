package com.example.debishut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText fullName, email, phone, cardNo, cvc;
    RadioButton cash, card;
    String payment;
    Button proceed, cancel;
    TextInputEditText dname, dorderId, dphone, daddress, dloction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_page);
        Log.d(TAG, "onCreate: started");
        fullName = findViewById(R.id.full_name);
        phone = findViewById(R.id.phone_no);
        email = findViewById(R.id.email_address);
        cardNo = findViewById(R.id.card_number);
        cvc = findViewById(R.id.cvc_no);
        proceed = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        cash = (RadioButton) findViewById(R.id.cash);
        card = (RadioButton) findViewById(R.id.card);
    }

    public void saveBill(View view){
        String name = fullName.getText().toString();
        String contactNo = phone.getText().toString();
        String emailAd = email.getText().toString();
        String cno = cardNo.getText().toString();
        String cvc_no = cvc.getText().toString();
        String pay;
        DBHelper dbHelper = new DBHelper(this);
        List info = dbHelper.readAll();

        String[] infoArray = (String[]) info.toArray(new String[0]);

        if(cash.isChecked()){
            payment = "cash";
        }else if(card.isChecked()){
            payment = "card";
        }else{
            Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
        }

        pay = payment;

        if(name.isEmpty() || contactNo.isEmpty() || emailAd.isEmpty() || cno.isEmpty() || cvc_no.isEmpty() || pay.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dbHelper.addInfo(name, contactNo, emailAd, cno, cvc_no, pay);

            if(inserted > 0){
                Toast.makeText(this, "Payment successfully completed!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bill details");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = infoArray[i].split(":")[0];
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                fullName.setText(name);
                phone.setText(contactNo);
                email.setText(emailAd);
                cardNo.setText(cno);
                cvc.setText(cvc_no);

                if(cash.isChecked()){
                    payment = "cash";
                }else if(card.isChecked()){
                    payment = "card";
                }else {
                    Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
                }

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    /*public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);
        String name = fullName.getText().toString();
        String contactNo = phone.getText().toString();
        String emailAd = email.getText().toString();
        String cno = cardNo.getText().toString();
        String cvc_no = cvc.getText().toString();
        String pay;
        List info = dbHelper.readAll();

        String[] infoArray = (String[]) info.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bill details");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = infoArray[i].split(":")[0];
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                fullName.setText(name);
                phone.setText(contactNo);
                email.setText(emailAd);
                cardNo.setText(cno);
                cvc.setText(cvc_no);

                if(cash.isChecked()){
                    payment = "cash";
                }else if(card.isChecked()){
                    payment = "card";
                }else {
                    Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
                }

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

    }*/

    /*public void deleteBill(View view){
            DBHelper db = new DBHelper(this);
            String fullname = fullName.getText().toString();
            String contact = phone.getText().toString();
            String emailad = email.getText().toString();
            String cardno = cardNo.getText().toString();
            String cvcno = cvc.getText().toString();
            String paymeth;

            if (cash.isChecked()) {
                payment = "cash";
            } else if (card.isChecked()) {
                payment = "card";
            } else {
                Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
            }

            pay = payment;

            if (fullname.isEmpty() || contact.isEmpty() || emailad.isEmpty() || cardno.isEmpty() || cvcno.isEmpty() || paymeth.isEmpty()) {
                dbHelper.deleteInfo(fullname);
            }
        }
    }*/
}