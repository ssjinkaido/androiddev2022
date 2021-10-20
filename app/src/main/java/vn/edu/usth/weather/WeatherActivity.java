package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private WeatherFragmentAdapter mWeatherFragmentAdapter;
    private static final String MSG_KEY = "yo";

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
                new task().execute();
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
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(WeatherActivity.this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //https://viblo.asia/p/xu-ly-da-tien-trinh-trong-android-bang-asynctask-ZDEeLRMpvJb
    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(WeatherActivity.this, "Start", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(WeatherActivity.this, "something sent from the server", Toast.LENGTH_SHORT).show();
        }
    }
}