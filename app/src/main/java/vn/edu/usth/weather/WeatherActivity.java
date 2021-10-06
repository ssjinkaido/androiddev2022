package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private WeatherFragmentAdapter mWeatherFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewPager);

        mWeatherFragmentAdapter = new WeatherFragmentAdapter(this);
        mViewPager.setAdapter(mWeatherFragmentAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    break;
                case 1:
                    tab.setText("Page1");
                    break;
                case 2:
                    tab.setText("Page2");
                    break;
            }
        }).attach();

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.lofistudy);
        mp.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(getApplicationContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
                recreate();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(WeatherActivity.this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}