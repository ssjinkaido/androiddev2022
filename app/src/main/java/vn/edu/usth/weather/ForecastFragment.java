package vn.edu.usth.weather;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastFragment extends Fragment {
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    View rootView;

    public static final String LOG_TAG = "";

    public ForecastFragment() {
        //Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        rootView.setBackgroundColor(Color.parseColor("#20FF0000"));

        return rootView;

    }
}