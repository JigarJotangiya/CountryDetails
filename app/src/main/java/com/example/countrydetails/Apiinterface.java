package com.example.countrydetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

interface Apiinterface {


    @GET("v2/all")
    Call<List<CountryDatum>> getdata();

};
