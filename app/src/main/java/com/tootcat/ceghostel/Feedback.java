package com.tootcat.ceghostel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        String key = getIntent().getStringExtra("key");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("students").child(key);

        Button bSubmit=(Button)findViewById(R.id.bSubmit);
        Button bCancel=(Button)findViewById(R.id.bCancel);
        final EditText tFeedbackBox=(EditText)findViewById(R.id.tFeebackBox);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String feedback=tFeedbackBox.getText().toString();
                if(!feedback.isEmpty())
                    dbRef.child("feedback").setValue(feedback);

                Feedback.this.finish();
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feedback.this.finish();
            }
        });
    }
}
