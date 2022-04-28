package com.example.countrydetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Callback<T> implements retrofit2.Callback<java.util.List<CountryDatum>> {
    @Override
    public void onResponse(Call<List<CountryDatum>> call, Response<List<CountryDatum>> response) {

    }

    @Override
    public void onFailure(Call<List<CountryDatum>> call, Throwable t) {

    }
}
