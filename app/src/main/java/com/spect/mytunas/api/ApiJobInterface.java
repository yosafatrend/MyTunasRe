package com.spect.mytunas.api;

import com.spect.mytunas.models.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiJobInterface {
    @GET("values/{job}")
    Call<List<Job>> getJob(@Path("job") String job);

    @GET("values/{job}/{location}")
    Call<List<Job>> getJobLoc(@Path("job") String job, @Path("location") String location);
}
