package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        getSupportFragmentManager().beginTransaction().
//                replace(R.id.fragmentAbove, new WeatherFragment(), "SOMETAG").
//                commit();
//        getSupportFragmentManager().beginTransaction().
//                replace(R.id.fragmentBelow, new ForecastFragment(), "SOMETAG").
//                commit();
        
        getSupportActionBar().setTitle(R.string.app_name);

    }
}