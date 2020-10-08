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
import androidx.recyclerview.widget.RecyclerView;

import com.spect.mytunas.Adapter;
import com.spect.mytunas.JobDetail;
import com.spect.mytunas.R;
import com.spect.mytunas.models.Article;
import com.spect.mytunas.models.Job;
import com.spect.mytunas.models.Requests;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {
    private List<Job> jobs;
    private Context context;
    private Activity mActivity;

    public JobAdapter(List<Job> jobs, Context context, Activity activity) {
        this.jobs = jobs;
        this.context = context;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public JobAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
        return new JobAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        final Job model = jobs.get(position);

        holder.jobName.setText(model.getJobName());
        holder.jobDate.setText(model.getJobDate());
        holder.jobCompany.setText(model.getJobCompany());
        holder.jobLocation.setText(model.getJobLocation());

        holder.cvJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, JobDetail.class);
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
        return jobs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jobName, jobCompany, jobDate, jobLocation;
        LinearLayout cvJob;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.jobName);
            jobCompany = itemView.findViewById(R.id.jobCompany);
            jobDate = itemView.findViewById(R.id.jobDate);
            jobLocation = itemView.findViewById(R.id.jobLocation);
            cvJob = itemView.findViewById(R.id.cvJob);
        }

    }
}
