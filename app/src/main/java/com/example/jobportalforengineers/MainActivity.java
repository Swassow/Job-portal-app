package com.example.jobportalforengineers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnLogin,btnRegistration;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);
        LoginFunction();
    }
    private void LoginFunction()
    {
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        btnLogin=findViewById(R.id.btn_login);
        btnRegistration=findViewById(R.id.btn_registration);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email,Pass;
                Email=email.getText().toString().trim();
                Pass=password.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Required field..");
                    return;
                }

                if(TextUtils.isEmpty(Pass))
                {
                    password.setError("Required field..");
                    return;
                }
                mDialog.setMessage("Processing...");
                mDialog.show();
                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            mDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Login Failed..",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }


                    }
                });

            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RagistrationActivity.class));

            }
        });

    }
}