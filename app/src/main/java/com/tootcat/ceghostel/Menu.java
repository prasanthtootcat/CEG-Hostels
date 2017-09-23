package com.tootcat.ceghostel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final String key=getIntent().getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("currentMenu").child("menu");

        final TextView tCurrentMenu=(TextView)findViewById(R.id.tCurrentMenu);
        Button bCancel=(Button)findViewById(R.id.bCancel);
        Button bPreference=(Button)findViewById(R.id.bPreference);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String menu=dataSnapshot.getValue().toString();
                if(menu!=null)
                    tCurrentMenu.setText(menu);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next=new Intent(Menu.this,MenuPreference.class);
                next.putExtra("key",key);
                startActivity(next);

                Menu.this.finish();
            }
        });



        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu.this.finish();
            }
        });
    }
}
