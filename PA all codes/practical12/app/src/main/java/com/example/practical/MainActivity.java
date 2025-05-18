package com.example.practical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    interface WeatherApiService {
        @GET("v1/current.json")
        Call<JsonObject> getCurrentWeather(
                @Query("key") String apiKey,
                @Query("q") String location
        );
    }

    private static final String BASE_URL = "https://api.weatherapi.com/";
    private static final String API_KEY = "9fa326c27c4f4b77bf734205241911"; // Replace with your actual API key

    private EditText cityInput;
    private TextView weatherInfoTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityInput = findViewById(R.id.city_input);
        weatherInfoTextView = findViewById(R.id.weather_info);
        progressBar = findViewById(R.id.progress_bar);

        Button fetchWeatherButton = findViewById(R.id.fetch_weather_button);
        Button clearButton = findViewById(R.id.clear_button);

        fetchWeatherButton.setOnClickListener(this::fetchWeather);
        clearButton.setOnClickListener(this::clearFields);
    }

    private void fetchWeather(View view) {
        String cityName = cityInput.getText().toString().trim();

        if (cityName.isEmpty()) {
            weatherInfoTextView.setText("Please enter a valid city name.");
            return;
        }

        progressBar.setVisibility(View.VISIBLE); // Show progress bar
        makeApiCall(cityName);
    }

    private void clearFields(View view) {
        cityInput.setText("");
        weatherInfoTextView.setText("Weather Info");
        progressBar.setVisibility(View.GONE);
    }

    private void makeApiCall(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService apiService = retrofit.create(WeatherApiService.class);
        Call<JsonObject> call = apiService.getCurrentWeather(API_KEY, cityName);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressBar.setVisibility(View.GONE); // Hide progress bar
                if (response.isSuccessful() && response.body() != null) {
                    handleApiResponse(response.body());
                } else {
                    Log.e("Weather", "Response failed: " + response.message());
                    weatherInfoTextView.setText("Failed to fetch weather data.");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide progress bar
                Log.e("Weather", "Request failed: " + t.getMessage());
                weatherInfoTextView.setText("Request failed: " + t.getMessage());
            }
        });
    }

    private void handleApiResponse(JsonObject weatherResponse) {
        try {
            JsonObject locationJson = weatherResponse.getAsJsonObject("location");
            JsonObject current = weatherResponse.getAsJsonObject("current");

            String locationName = locationJson.get("name").getAsString();
            double temperatureC = current.get("temp_c").getAsDouble();
            double feelsLikeC = current.get("feelslike_c").getAsDouble();
            double humidity = current.get("humidity").getAsDouble();
            double windKph = current.get("wind_kph").getAsDouble();
            String conditionText = current.getAsJsonObject("condition").get("text").getAsString();

            String weatherInfo = String.format(
                    "Location: %s\nTemperature: %.1f°C\nFeels Like: %.1f°C\nHumidity: %.1f%%\nWind Speed: %.1f kph\nCondition: %s",
                    locationName, temperatureC, feelsLikeC, humidity, windKph, conditionText);

            weatherInfoTextView.setText(weatherInfo);
        } catch (Exception e) {
            Log.e("Weather", "Parsing error: " + e.getMessage());
            weatherInfoTextView.setText("Error parsing weather data.");
        }

    }
}
