package vn.edu.usth.weather;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;

import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import com.android.volley.toolbox.Volley;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class WeatherActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private WeatherFragmentAdapter mWeatherFragmentAdapter;
    private ImageView imageLogo;
    private static final String MSG_KEY = "yo";
    private static final String downloadUrl = "https://usth.edu.vn/uploads/logo_moi-eng.png";

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
                    tab.setText("Hanoi");
                    break;
                case 1:
                    tab.setText("Hanoi");
                    break;
                case 2:
                    tab.setText("Hanoi");
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
                //https://android--examples.blogspot.com/2017/02/android-volley-image-request-example.html
                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(this);

                // Initialize a new ImageRequest
                ImageRequest imageRequest = new ImageRequest(
                        downloadUrl, // Image URL
                        new Response.Listener<Bitmap>() { // Bitmap listener
                            @Override
                            public void onResponse(Bitmap response) {
                                // Do something with response
                                imageLogo = (ImageView) findViewById(R.id.logo);
                                imageLogo.setImageBitmap(response);
                            }
                        },
                        0, // Image width
                        0, // Image height
                        ImageView.ScaleType.CENTER_CROP, // Image scale type
                        Bitmap.Config.RGB_565, //Image decode configuration
                        new Response.ErrorListener() { // Error listener
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something with error response
                                error.printStackTrace();
                                Toast.makeText(WeatherActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                // Add ImageRequest to the RequestQueue
                requestQueue.add(imageRequest);

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