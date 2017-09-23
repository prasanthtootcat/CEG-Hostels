package com.tootcat.ceghostel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuPreference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preference);

        final Spinner sWeek=(Spinner)findViewById(R.id.sWeek);
        final Spinner sTime=(Spinner)findViewById(R.id.sTime);
        final Spinner sFood=(Spinner)findViewById(R.id.sFood);
        final EditText tMenuPreference=(EditText)findViewById(R.id.tMenuPreference);
        Button bSubmit=(Button)findViewById(R.id.bSubmit);
        Button bCancel=(Button)findViewById(R.id.bCancel);

        final String key=getIntent().getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("students").child(key).child("menuPreference");

        sWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected=sWeek.getSelectedItem().toString();
                tMenuPreference.append("\n\n"+selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected=sTime.getSelectedItem().toString();
                tMenuPreference.append("\n"+selected+": ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected=sFood.getSelectedItem().toString();
                tMenuPreference.append(selected+"  ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuPreference=tMenuPreference.getText().toString();
                if(!menuPreference.isEmpty()) {
                    dbRef.setValue(menuPreference);
                    Toast.makeText(MenuPreference.this, "Your preference is submitted!", Toast.LENGTH_SHORT).show();
                }
            }
        });





        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPreference.this.finish();
            }
        });
    }
}
