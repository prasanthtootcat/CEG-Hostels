package com.tootcat.ceghostel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tootcat.ceghostel.R;

public class Registeration extends AppCompatActivity {


    Button bRegister,bCancel;
    EditText tName,tRollNo,tDOB,tDepartment,tAddress,tEmail,tPassword;
    Spinner spinner;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference("students");

    String name,rollNo,dob,department,address,email,password,messPreference;
    Details obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mAuth = FirebaseAuth.getInstance();
        bRegister = (Button)findViewById(R.id.bRegister);
        bCancel = (Button)findViewById(R.id.bCancel);
        tName=(EditText)findViewById(R.id.tName);
        tRollNo=(EditText)findViewById(R.id.tRollNo);
        tDOB=(EditText)findViewById(R.id.tDOB);
        tDepartment=(EditText)findViewById(R.id.tDepartment);
        tAddress=(EditText)findViewById(R.id.tAddress);
        tEmail=(EditText)findViewById(R.id.tEmail);
        tPassword=(EditText)findViewById(R.id.tPassword);
        spinner=(Spinner)findViewById(R.id.spinner);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int flag=setCredentials();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registeration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registeration.this,R.string.error2,Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Registeration.this, R.string.success, Toast.LENGTH_SHORT).show();
                                    if(flag==1) {

                                        dbRef.child(email.replace(".com","")).setValue(obj);

                                    }
                                    startActivity(new Intent(Registeration.this, LoginActivity.class));
                                    Registeration.this.finish();
                                }
                            }
                        });



            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registeration.this,LoginActivity.class));
                Registeration.this.finish();
            }
        });
    }

    int setCredentials()
    {
        int flag=0;
        try
        {
            name=tName.getText().toString();
            rollNo=tRollNo.getText().toString().trim();
            dob=tDOB.getText().toString();
            department=tDepartment.getText().toString();
            address=tAddress.getText().toString();
            email=tEmail.getText().toString().trim();
            password=tPassword.getText().toString().trim();
            messPreference=spinner.getSelectedItem().toString();

            if(name!=null&&rollNo!=null&&dob!=null&&department!=null&&address!=null&&email!=null&&password!=null&password.length()>6&&messPreference!=null)
            {
                obj=new Details(name,rollNo,dob,department,address,email,messPreference,"false");
                flag=1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
}

