package com.example.countrydetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Country_detail_list extends AppCompatActivity {

    TextView name, capital, calling_code, native_name, Demonym, Population, time_zone,
            Region, sub_Region, gini, area, domain, alpha_2_code, alpha_3_code, numeric_code, alt_spelings,
            borders, translation, currencies, languages, region_blocs;

    public final String URL = "https://restcountries.com/v2/all";

    ImageView flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail_list);


        name = findViewById(R.id.name);
        flags = findViewById(R.id.flags);
        capital = findViewById(R.id.capital);
        calling_code = findViewById(R.id.calling_code);
        native_name = findViewById(R.id.native_name);
        Demonym = findViewById(R.id.Demonym);
        Population = findViewById(R.id.Population);
        time_zone = findViewById(R.id.time_zone);
        Region = findViewById(R.id.Region);
        sub_Region = findViewById(R.id.sub_Region);
        gini = findViewById(R.id.gini);
        area = findViewById(R.id.area);
        domain = findViewById(R.id.domain);
        alpha_2_code = findViewById(R.id.alpha_2_code);
        alpha_3_code = findViewById(R.id.alpha_3_code);
        numeric_code = findViewById(R.id.numeric_code);
        alt_spelings = findViewById(R.id.alt_spelings);
        borders = findViewById(R.id.borders);

        languages = findViewById(R.id.languages);
        region_blocs = findViewById(R.id.region_blocs);


        Apiinterface apiinterface = ApiClient.getApiClient().create(Apiinterface.class);
        apiinterface.getdata().enqueue(new Callback<List<CountryDatum>>() {
            @Override
            public void onResponse(Call<List<CountryDatum>> call, Response<List<CountryDatum>> response) {

                List<CountryDatum> modellist = response.body();
                int pos = getIntent().getIntExtra("pos", 0);

                Toast.makeText(Country_detail_list.this, "" + pos, Toast.LENGTH_SHORT).show();
                Glide.with(Country_detail_list.this).load(modellist.get(pos).getFlags().getPng()).into(flags);

                name.setText("" + modellist.get(pos).getName());
                capital.setText("" + modellist.get(pos).getCapital());
                calling_code.setText("" + modellist.get(pos).getCallingCodes());
                native_name.setText("" + modellist.get(pos).getNativeName());
                Demonym.setText("" + modellist.get(pos).getDemonym());
                Population.setText("" + modellist.get(pos).getPopulation());
                time_zone.setText("" + modellist.get(pos).getTimezones());
                Region.setText("" + modellist.get(pos).getRegion());
                sub_Region.setText("" + modellist.get(pos).getSubregion());

                area.setText("" + modellist.get(pos).getArea());
                domain.setText("" + modellist.get(pos).getTopLevelDomain());
                alpha_2_code.setText("" + modellist.get(pos).getAlpha2Code());
                alpha_3_code.setText("" + modellist.get(pos).getAlpha3Code());
                numeric_code.setText("" + modellist.get(pos).getNumericCode());
                alt_spelings.setText("" + modellist.get(pos).getAltSpellings());


                borders.setText("" + modellist.get(pos).getBorders());
                languages.setText("" + modellist.get(pos).getLanguages());
                region_blocs.setText("" + modellist.get(pos).getRegionalBlocs());


            }

            @Override
            public void onFailure(Call<List<CountryDatum>> call, Throwable t) {

            }
        });
    }
}