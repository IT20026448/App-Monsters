zzpackage com.example.debishut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //vars
    // private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button proceed, cancel;
    EditText fullName, contactNo, email, cardNo, cvc;
    RadioButton cash, card;
    String payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        proceed = (Button)findViewById(R.id.button1);
        cancel = (Button)findViewById(R.id.button2);
        fullName = (EditText)findViewById(R.id.editTextName);
        contactNo = (EditText)findViewById(R.id.editTextContact);
        email = (EditText)findViewById(R.id.editTextEmail);
        cash = (RadioButton)findViewById(R.id.radioButton1);
        card = (RadioButton)findViewById(R.id.radioButton2);
        cardNo = (EditText)findViewById(R.id.editTextCardNo);
        cvc = (EditText)findViewById(R.id.editTextCvc);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fullname = fullName.getText().toString();
                String contact = contactNo.getText().toString();
                String Email = email.getText().toString();
                String cNo = cardNo.getText().toString();
                String CVC = cvc.getText().toString();

                if(cash.isChecked()){
                    payment = "cash";
                }else if(card.isChecked()){
                    payment = "card";
                }else {
                    Toast.makeText(getApplicationContext(), "select payment option!", Toast.LENGTH_SHORT).show();
                }

                insertdata(fullname, contact, Email, payment, cNo, CVC);
                Toast.makeText(getApplicationContext(), "registered successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://lp-cms-production.imgix.net/2019- 06/b4fbc706dab2a70a96588309ed268a1a-sri-lanka.jpeg");mNames.add("Seegiriya");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Demodara-Nine-Arch-Bridge.jpg");
        mNames.add("Ella");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Train-ride-from-Kandy-to-Nuwara-Eliya.jpg");
        mNames.add("Nuwara Eliya");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Pinnawala-Elephant-Orphanage.jpg");
        mNames.add("Pinnawala Elephant Orphanage");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Ruins-of-Polonnaruwa.jpg");
        mNames.add("Polonnaruwa");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Adams-Peak.jpg");
        mNames.add("Adams Peak");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Mirissa-Fisheries-Harbor.jpg");
        mNames.add("Mirissa");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Leopards.jpg");
        mNames.add("Yala National Park");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Colombo.jpg");
        mNames.add("Colombo");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Jaffna.jpg");
        mNames.add("Jaffna");
        initRecyclerView();
    }*/

    public void insertdata(String full_name, String contact, String email, String payment, String card_no, String cvc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_2, full_name);
        contentValues.put(DBHelper.COL_3, contact);
        contentValues.put(DBHelper.COL_4, email);
        contentValues.put(DBHelper.COL_5, payment);
        contentValues.put(DBHelper.COL_6, card_no);
        contentValues.put(DBHelper.COL_7, cvc);
        contentValues.put(DBHelper.COL_8, payment);
        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

     /* private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
*/
}