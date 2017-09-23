package com.tootcat.ceghostel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tootcat.ceghostel.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bAnnouncement=(Button)findViewById(R.id.bAnnouncement);
        Button bFeePayment=(Button)findViewById(R.id.bFeePayment);
        Button bReceipt=(Button)findViewById(R.id.bReceipt);
        Button bMenu=(Button)findViewById(R.id.bMenu);
        Button bFeedback=(Button)findViewById(R.id.bFeedback);

        final String key=getIntent().getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("students").child(key).child("feePaymentStatus");

        bAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Announcement.class));
            }
        });

        bFeePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               dbRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       String feePaymentStatus = dataSnapshot.getValue().toString();

                       if (feePaymentStatus.equalsIgnoreCase("true"))
                           Toast.makeText(MainActivity.this,"Fee already payed!",Toast.LENGTH_SHORT).show();
                       else
                       {
                           Intent next=new Intent(MainActivity.this,FeePayment.class);
                           next.putExtra("key",key);
                           startActivity(next);
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });

            }
        });

        bReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Receipt.class));
            }
        });

        bMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(MainActivity.this,Menu.class);
                next.putExtra("key",key);
                startActivity(next);
            }
        });

        bFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next=new Intent(MainActivity.this,Feedback.class);
                next.putExtra("key",key);
                startActivity(next);
            }
        });
    }
}
