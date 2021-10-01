package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ForecastFragment firstFragment = new ForecastFragment();
        // Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment).commit();

    }
}