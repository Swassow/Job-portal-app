package com.example.jobportalforengineers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RagistrationActivity extends AppCompatActivity {

    private EditText emailreg,passreg;
    private Button btnreg,btnlogin;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragistration);
        mAuth=FirebaseAuth.getInstance();
        registration();
    }

    private void registration()
    {
        emailreg=findViewById(R.id.email_registration);
        passreg=findViewById(R.id.password_registration);

        btnreg=findViewById(R.id.btn_registration);
        btnlogin=findViewById(R.id.btn_login);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email=emailreg.getText().toString().trim();
                pass=passreg.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailreg.setError("Required field..");
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    passreg.setError("Required field..");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }
                    }
                });

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

    }
}