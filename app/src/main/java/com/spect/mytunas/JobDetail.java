package com.spect.mytunas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobDetail extends AppCompatActivity {
    private TextView tvJobName, tvJobCompany, tvJobLocation, tvJobSalary, tvJobDate, tvJobDesc;
    private Button btnOpenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        tvJobName = findViewById(R.id.tvJobNameD);
        tvJobCompany = findViewById(R.id.tvJobCompanyD);
        tvJobDesc = findViewById(R.id.tvJobDescD);
        tvJobDate = findViewById(R.id.tvJobDateD);
        tvJobSalary = findViewById(R.id.tvJobSalary);
        btnOpenLink = findViewById(R.id.btnOpenLink);

        tvJobName.setText(getIntent().getStringExtra("jobName"));
        tvJobCompany.setText(getIntent().getStringExtra("jobCompany") +" - "+ getIntent().getStringExtra("jobLocation"));
        tvJobDate.setText(getIntent().getStringExtra("jobDate"));
        tvJobSalary.setText(getIntent().getStringExtra("jobSalary"));
        tvJobDesc.setText(getIntent().getStringExtra("jobDesc"));
        btnOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getIntent().getStringExtra("jobLink");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}