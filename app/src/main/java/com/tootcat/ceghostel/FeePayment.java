package com.tootcat.ceghostel;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class FeePayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_payment);

        String key = getIntent().getStringExtra("key");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("students").child(key).child("feePaymentStatus");

            final EditText textView1 = (EditText) findViewById(R.id.textView1);
            final EditText textView2 = (EditText) findViewById(R.id.textView2);
            final EditText textView3 = (EditText) findViewById(R.id.textView3);

            Button bPay = (Button) findViewById(R.id.bPay);
            Button bCancel = (Button) findViewById(R.id.bCancel);

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupPayment);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    RadioButton radioButton = (RadioButton) findViewById(i);
                    String rb = radioButton.getText().toString();

                    if (rb.contentEquals("Credit card")) {
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView1.setText("Enter Card number");
                        textView2.setText("Enter CVV");
                        textView3.setText("Enter PIN number");
                    } else if (rb.contentEquals("Net Banking")) {
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.GONE);
                        textView1.setText("Enter Username");
                        textView2.setText("Enter Password");

                    } else {
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.GONE);
                        textView1.setText("Enter the DD number");
                        textView2.setText("Date");
                    }

                }
            });

            bCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FeePayment.this.finish();
                }
            });

            bPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(FeePayment.this, "Payment success", Toast.LENGTH_SHORT).show();
                    dbRef.setValue("true");
                    FeePayment.this.finish();
                }
            });

        }

}
