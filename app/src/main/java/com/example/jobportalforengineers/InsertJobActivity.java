package com.example.jobportalforengineers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobportalforengineers.model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class InsertJobActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText jobtitle,jobdescription,jobskills,jobsalary;
    private Button btnpostjob;
    private FirebaseAuth mAuth;
    private DatabaseReference mpostjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job);
        toolbar=findViewById(R.id.insert_job_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Job");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();
        mpostjob=FirebaseDatabase.getInstance().getReference().child("Job Post").child(uid);

        insertjob();
    }
    private void insertjob()
    {
        jobtitle=findViewById(R.id.job_title);
        jobdescription=findViewById(R.id.job_description);
        jobskills=findViewById(R.id.job_skills);
        jobsalary=findViewById(R.id.job_salary);
        btnpostjob=findViewById(R.id.btn_post_job);
        btnpostjob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String title =jobtitle.getText().toString().trim();
                String description = jobdescription.getText().toString().trim();
                String skills =jobskills.getText().toString().trim();
                String salary=jobsalary.getText().toString().trim();

                if(TextUtils.isEmpty(title))
                {
                    jobtitle.setError("Required field..");
                    return;
                }
                if(TextUtils.isEmpty(description))
                {
                    jobdescription.setError("Required field..");
                    return;
                }
                if(TextUtils.isEmpty(skills))
                {
                    jobskills.setError("Required field..");
                    return;
                }
                if(TextUtils.isEmpty(salary))
                {
                    jobsalary.setError("Required field..");
                    return;
                }
                String id=mpostjob.push().getKey();
                String date= DateFormat.getDateInstance().format(new Date());
                Data data=new Data(title,description,skills,salary,id,date);
                mpostjob.child(id).setValue(data);
                Toast.makeText(getApplicationContext(),"Successfull..",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));

            }
        });
    }
}