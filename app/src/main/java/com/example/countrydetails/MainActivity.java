package com.example.countrydetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText cou;

    public final String URL = "https://restcountries.com/v2/all";

    RecyclerView list;
    public static RecyclerAdapter adapter;
    private static final String TAG = "MainActivity";

    List<CountryDatum> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cou = findViewById(R.id.cou);

        cou.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                filter(charSequence.toString(), MainActivity.this);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


//        String[] places = new String[]{
//                "Afghanistan",
//                "Albania",
//                "Algeria",
//                "Andorra",
//                "Angola",
//                "Antigua and Barbuda",
//                "Argentina",
//                "Armenia",
//                "Australia",
//                "Austria",
//                "Azerbaijan",
//
//                "The Bahamas",
//                "Bahrain",
//                "Bangladesh",
//                "Barbados",
//                "Belarus",
//                "Belgium",
//                "Belize",
//                "Benin",
//                "Bhutan",
//                "Bolivia",
//                "Bosnia and Herzegovina",
//                "Botswana",
//                "Brazil",
//                "Brunei",
//                "Bulgaria",
//                "Burkina Faso",
//                "Burundi",
//
//                "Cabo Verde",
//                "Cambodia",
//                "Cameroon",
//                "Canada",
//                "Central African Republic",
//                "Chad",
//                "Chile",
//                "China",
//                "Colombia",
//                "Comoros",
//
//                "Costa Rica",
//                "Côte d’Ivoire",
//                "Croatia",
//                "Cuba",
//                "Cyprus",
//                "Czech Republic",
//
//                "Denmark",
//                "Djibouti",
//                "Dominica",
//                "Dominican Republic",
//
//                "East Timor (Timor-Leste)",
//                "Ecuador",
//                "Egypt",
//                "El Salvador",
//                "Equatorial Guinea",
//                "Eritrea",
//                "Estonia",
//                "Eswatini",
//                "Ethiopia",
//
//                "Fiji",
//                "Finland",
//                "France",
//
//                "Gabon",
//                "The Gambia",
//                "Georgia",
//                "Germany",
//                "Ghana",
//                "Greece",
//                "Grenada",
//                "Guatemala",
//                "Guinea",
//                "Guinea-Bissau",
//                "Guyana",
//
//                "Haiti",
//                "Honduras",
//                "Hungary",
//
//                "Iceland",
//                "India",
//                "Indonesia",
//                "Iran",
//                "Iraq",
//                "Ireland",
//                "Israel",
//                "Italy",
//
//                "Jamaica",
//                "Japan",
//                "Jordan",
//
//                "Kazakhstan",
//                "Kenya",
//                "Kiribati",
//                "Korea, North",
//                "Korea South",
//                "Kosovo",
//                "Kuwait",
//                "Kyrgyzstan",
//
//                "Laos",
//                "Latvia",
//                "Lebanon",
//                "Lesotho",
//                "Liberia",
//                "Libya",
//                "Liechtenstein",
//                "Lithuania",
//                "Luxembourg",
//
//                "Madagascar",
//                "Malawi",
//                "Malaysia",
//                "Maldives",
//                "Mali",
//                "Malta",
//                "Marshall Islands",
//                "Mauritania",
//                "Mauritius",
//                "Mexico",
//                "Micronesia",
//                "Moldova",
//                "Monaco",
//                "Mongolia",
//                "Montenegro",
//                "Morocco",
//                "Mozambique",
//                "Myanmar (Burma)",
//
//                "Namibia",
//                "Nauru",
//                "Nepal",
//                "Netherlands",
//                "New Zealand",
//                "Nicaragua",
//                "Niger",
//                "Nigeria",
//                "North Macedonia",
//                "Norway",
//
//                "Oman",
//
//                "Pakistan",
//                "Palau",
//                "Panama",
//                "Papua New Guinea",
//                "Paraguay",
//                "Peru",
//                "Philippines",
//                "Poland",
//                "Portugal",
//
//                "Qatar",
//
//                "Romania",
//                "Russia",
//                "Rwanda",
//
//                "Saint Kitts and Nevis",
//                "Saint Lucia",
//                "Saint Vincent and the Grenadines",
//                "Samoa",
//                "San Marino",
//                "Sao Tome and Principe",
//                "Saudi Arabia",
//                "Senegal",
//                "Serbia",
//                "Seychelles",
//                "Sierra Leone",
//                "Singapore",
//                "Slovakia",
//                "Slovenia",
//                "Solomon Islands",
//                "Somalia",
//                "South Africa",
//                "Spain",
//                "Sri Lanka",
//                "Sudan",
//                "Sudan",
//                "Suriname",
//                "Sweden",
//                "Switzerland",
//                "Syria",
//
//                "Taiwan",
//                "Tajikistan",
//                "Tanzania",
//                "Thailand",
//                "Togo",
//                "Tonga",
//                "Trinidad and Tobago",
//                "Tunisia",
//                "Turkey",
//                "Turkmenistan",
//                "Tuvalu",
//
//                "Uganda",
//                "Ukraine",
//                "United Arab Emirates",
//                "United Kingdom",
//                "United States",
//                "Uruguay",
//                "Uzbekistan",
//
//                "Vanuatu",
//                "Vatican City",
//                "Venezuela",
//                "Vietnam",
//
//                "Yemen",
//
//                "Zambia",
//                "Zimbabwe",
//        };

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
//                this, android.R.layout.simple_dropdown_item_1line, places
//        );
//        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(
//                R.id.suggetions
//        );
//        autoCompleteTextView.setAdapter(adapter1);

        list = findViewById(R.id.list);

        Apiinterface apiinterface = ApiClient.getApiClient().create(Apiinterface.class);
        apiinterface.getdata().enqueue(new Callback<List<CountryDatum>>() {
            @Override
            public void onResponse(Call<List<CountryDatum>> call, Response<List<CountryDatum>> response) {
                datalist = response.body();
                adapter = new RecyclerAdapter(MainActivity.this, datalist);
                list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CountryDatum>> call, Throwable t) {

                Log.e(TAG, "onFailure: =========== " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    void filter(String string, Activity activity) {

        List<CountryDatum> detalist2 = new ArrayList<>();

        for (int i = 0; i < datalist.size(); i++) {

            if (datalist.get(i).getName().toLowerCase().contains(string.toLowerCase())) {
                detalist2.add(datalist.get(i));
            }
        }
        adapter = new RecyclerAdapter(MainActivity.this, detalist2);
        list.setAdapter(adapter);

    }

}