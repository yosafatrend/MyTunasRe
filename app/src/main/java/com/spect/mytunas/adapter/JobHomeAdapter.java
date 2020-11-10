package com.spect.mytunas.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.spect.mytunas.R;
import com.spect.mytunas.activity.JobDetailActivity;
import com.spect.mytunas.models.Job;

import java.util.List;

public class JobHomeAdapter extends RecyclerView.Adapter<JobHomeAdapter.MyViewHolder> {
    private List<Job> jobs;
    private Context context;
    private Activity mActivity;

    public JobHomeAdapter(List<Job> jobs, Context context, Activity activity) {
        this.jobs = jobs;
        this.context = context;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public JobHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job_home, parent, false);
        return new JobHomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobHomeAdapter.MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        final Job model = jobs.get(position);

        holder.jobName.setText(model.getJobName());holder.jobCompany.setText(model.getJobCompany());
        holder.jobLocation.setText(model.getJobLocation());

        holder.cvJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, JobDetailActivity.class);
                i.putExtra("jobName", model.getJobName());
                i.putExtra("jobDate", model.getJobDate());
                i.putExtra("jobCompany", model.getJobCompany());
                i.putExtra("jobLocation", model.getJobLocation());
                i.putExtra("jobDesc", model.getJobDesc());
                i.putExtra("jobSalary", model.getJobSalary());
                i.putExtra("jobLink", model.getJobLink());
                mActivity.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (jobs.size() < 4){
            return jobs.size();
        }else{
            return 4;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jobName, jobCompany, jobLocation;
        LinearLayout cvJob;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.jobNameHome);
            jobCompany = itemView.findViewById(R.id.jobCompanyHome);
            jobLocation = itemView.findViewById(R.id.jobLocationHome);
            cvJob = itemView.findViewById(R.id.item_job_home);
        }

    }
}
