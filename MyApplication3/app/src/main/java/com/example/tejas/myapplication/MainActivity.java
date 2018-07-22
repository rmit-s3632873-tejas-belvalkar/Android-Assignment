package com.example.tejas.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Trackable> trackableObj;
    TextView textView;
    public static int hour;
    public static int mins;
    public static int secs;
    static Calendar c;


    MainActivity() {
        trackableObj = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View view) {
        textView = findViewById(R.id.textView);
        trackableObj.add(new Trackable(1, "tejas", "student", "indian", "Google.com"));
        trackableObj.add(new Trackable(2, "rishi", "student", "indian", "Google.com"));
        trackableObj.add(new Trackable(3, "babloo", "student", "indian", "Google.com"));
        textView.setText(null);

        for (int i = 0; i < trackableObj.size(); i++) {
            textView.append("\n\n\n\t" + trackableObj.get(i).getName() + "\n\t" + trackableObj.get(i).getType()
                    + "\n\t" + trackableObj.get(i).getCusine() + "\n\t" + trackableObj.get(i).getUrl());
        }

        findViewById(R.id.button).setVisibility(View.INVISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        findViewById(R.id.button4).setVisibility(View.VISIBLE);

        trackableObj.removeAll(trackableObj);


    }

    DialogFragment timeFragment, dateFragment;

    public void showDatePickerDialog(View v) {
        dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void goBack(View view) {
        textView.setText(null);
        setContentView(R.layout.activity_main);
    }

    public void showList(View view) {
        TrackingService trackingService = new TrackingService();


        TextView locs = findViewById(R.id.textView3);
        for (int i=0;i<trackingService.getTrackingInfoForTimeRange(c.getTime(),mins,secs).size();i++){
            System.out.println(trackingService.getTrackingInfoForTimeRange(c.getTime(),mins,secs).get(i));
        }


    }

    public void onButton2Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 2 clicked");

    }

    public void onButton3Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 3 clicked");
    }

    public void onButton4Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 4 clicked");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }

    }
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        Calendar c;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            mins = c.get(Calendar.MINUTE);
            secs = c.get(Calendar.SECOND);
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, mins,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

}










