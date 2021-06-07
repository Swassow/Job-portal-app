package com.example.jobportalforengineers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnallJobs,btnpostJobs;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnallJobs=findViewById(R.id.btn_alljob);
        btnpostJobs=findViewById(R.id.btn_postjob);
        toolbar=findViewById(R.id.toolbar_home);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Job portal");
        btnallJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllJobActivity.class));

            }
        });
        btnpostJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));

            }
        });
    }
}