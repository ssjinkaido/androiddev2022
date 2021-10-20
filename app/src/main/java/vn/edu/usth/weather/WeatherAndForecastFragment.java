package vn.edu.usth.weather;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class WeatherAndForecastFragment extends Fragment {
    private TextView weatherInfo;
    private String weatherToday;
    private TextView weatherWind;

    public WeatherAndForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherInfo = view.findViewById(R.id.weather_info);
        weatherWind = view.findViewById(R.id.weather_wind);
        fetchData();

    }

    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=e3fc7cd1499bf87b25c7829f2ff41639";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String name = response.getString("name");
                            double temp = response.getJSONObject("main").getDouble("temp");
                            double windSpeed = response.getJSONObject("wind").getDouble("speed");
                            weatherToday = name + "\n" + String.valueOf(temp) + " F degrees";
                            String speed = "Wind speed: "+ windSpeed + "km/h";
                            weatherInfo.setText(weatherToday);
                            weatherWind.setText(speed);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Fail to get data..", Toast.LENGTH_SHORT).show();

                    }
                });
        queue.add(jsonObjectRequest);
    }
}