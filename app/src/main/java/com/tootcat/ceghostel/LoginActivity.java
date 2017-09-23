package com.tootcat.ceghostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tootcat.ceghostel.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_xml);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        Button bRegister=(Button)findViewById(R.id.bRegister);
        Button bLogin=(Button)findViewById(R.id.bLogin);
        final EditText tUsername=(EditText)findViewById(R.id.tUsername);
        final EditText tPassword=(EditText)findViewById(R.id.tPassword);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username,password;
                username=tUsername.getText().toString().trim();
                password=tPassword.getText().toString().trim();

                if(username!=null && password!=null && password.length()>6)
                {

                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this,R.string.error,Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Intent next=new Intent(LoginActivity.this, MainActivity.class);
                                        String key=username.replaceAll(".com","");
                                        next.putExtra("key",key);
                                        startActivity(next);
                                        LoginActivity.this.finish();
                                    }
                                }
                            });
                }
                else
                    Toast.makeText(LoginActivity.this,R.string.error,Toast.LENGTH_SHORT).show();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Registeration.class));
                LoginActivity.this.finish();
            }
        });
    }
}