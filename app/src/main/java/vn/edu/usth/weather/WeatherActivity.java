package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
//                new task().execute(downloadUrl);
//                // https://alvinalexander.com/source-code/android-how-send-message-from-thread-to-handler/
//                Handler handler = new Handler(Looper.getMainLooper()) {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        // This method is executed in main thread
//                        Bundle bundle = msg.getData();
//                        String string = bundle.getString(MSG_KEY);
//                        Toast.makeText(WeatherActivity.this, string, Toast.LENGTH_SHORT).show();
//                    }
//                };
//
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            // wait for 2 seconds to simulate a long network access
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        Bundle bundle = new Bundle();
//                        bundle.putString(MSG_KEY, "something sent from the server");
//                        // notify main thread
//                        Message msg = new Message();
//                        msg.setData(bundle);
//                        handler.sendMessage(msg);
//                    }
//                });
//                thread.start();
////                recreate();
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
//
//    //https://www.youtube.com/watch?v=6FMqgAzKuOg
//    private class task extends AsyncTask<String, Void, Bitmap> {
//        HttpURLConnection connection;
//        Bitmap temp;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(WeatherActivity.this, "Start", Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... param) {
//            try {
//                URL url = new URL(param[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
//                temp = BitmapFactory.decodeStream(inputStream);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                connection.disconnect();
//            }
//            return temp;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            if (result != null) {
//                Toast.makeText(WeatherActivity.this, "Download successful", Toast.LENGTH_SHORT).show();
//                imageLogo = (ImageView) findViewById(R.id.logo);
//                imageLogo.setImageBitmap(result);
//
//            } else {
//                Toast.makeText(WeatherActivity.this, "Download failed", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//
//    }
}