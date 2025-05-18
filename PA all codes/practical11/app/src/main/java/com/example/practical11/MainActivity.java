package com.example.practical11;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    interface HoroscopeApiService {
        @GET("daily_horoscope")
        Call<JsonObject> getHoroscope(
                @Query("key") String apiKey,
                @Query("sign") String sign,
                @Query("day") String day
        );
    }

    private static final String BASE_URL = "https://astrologyapi.com/";
    private static final String API_KEY = "d4025d0107a5da663004211c46bb3d24b46304c2"; // Replace with your valid API key.

    private Spinner zodiacSpinner;
    private TextView horoscopeInfoTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zodiacSpinner = findViewById(R.id.zodiac_spinner);
        horoscopeInfoTextView = findViewById(R.id.horoscope_info);
        progressBar = findViewById(R.id.progress_bar);

        Button fetchHoroscopeButton = findViewById(R.id.fetch_horoscope_button);
        Button clearButton = findViewById(R.id.clear_button);

        setupZodiacSpinner();

        fetchHoroscopeButton.setOnClickListener(this::fetchHoroscope);
        clearButton.setOnClickListener(this::clearFields);
    }

    private void setupZodiacSpinner() {
        String[] zodiacSigns = {
                "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, zodiacSigns
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zodiacSpinner.setAdapter(adapter);
    }

    private void fetchHoroscope(View view) {
        if (!isNetworkAvailable()) {
            horoscopeInfoTextView.setText("No Internet connection.");
            return;
        }

        String selectedSign = zodiacSpinner.getSelectedItem().toString();
        String day = "today";

        progressBar.setVisibility(View.VISIBLE);
        makeApiCall(selectedSign, day);
    }

    private void clearFields(View view) {
        horoscopeInfoTextView.setText("Your Horoscope will appear here.");
        progressBar.setVisibility(View.GONE);
    }

    private void makeApiCall(String sign, String day) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HoroscopeApiService apiService = retrofit.create(HoroscopeApiService.class);
        Call<JsonObject> call = apiService.getHoroscope(API_KEY, sign, day);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    handleApiResponse(response.body());
                } else {
                    Log.e("Horoscope", "Response failed: " + response.message());
                    horoscopeInfoTextView.setText("Failed to fetch horoscope.");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("Horoscope", "Request failed: " + t.getMessage());
                horoscopeInfoTextView.setText("Request failed: " + t.getMessage());
            }
        });
    }

    private void handleApiResponse(JsonObject horoscopeResponse) {
        try {
            if (horoscopeResponse.has("horoscope")) {
                String horoscope = horoscopeResponse.get("horoscope").getAsString();
                horoscopeInfoTextView.setText(horoscope);
            } else {
                horoscopeInfoTextView.setText("Horoscope data not available.");
                Log.e("Horoscope", "Horoscope key missing in response.");
            }
        } catch (Exception e) {
            Log.e("Horoscope", "Parsing error: " + e.getMessage());
            horoscopeInfoTextView.setText("Error parsing horoscope data.");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
