package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class WeatherFragmentAdapter extends FragmentStateAdapter {

    public WeatherFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WeatherAndForecastFragment();
            case 1:
                return new WeatherAndForecastFragment();
            case 2:
                return new WeatherAndForecastFragment();

        }
        return new WeatherAndForecastFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}