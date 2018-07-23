package com.example.tejas.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Trackable> trackableObj;
    TextView textView;
    static int hour;
    static int mins;
    static String choice;
    int h,m;

    public int getHour() {
        h=hour;
        return h;
    }

    public int getMins() {
        m=mins;
        return m;
    }

    static int secs;
    static Calendar c;
    Tracking tracking;
    static int Y;
    static int M;
    static int D;


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
        trackableObj.add(new Trackable("1", "Tejas", "student", "Indian", "Google.com"));
        trackableObj.add(new Trackable("2", "Rishi", "student", "German", "Google.com"));
        trackableObj.add(new Trackable("3", "Babloo", "student", "Italian", "Google.com"));
        textView.setText(null);
        FoodTruckParser foodTruckParser = new FoodTruckParser();
        List<Trackable> foodTruckData;
        foodTruckData=foodTruckParser.getTrackableList(getApplicationContext());
        EditText editText = findViewById(R.id.filter);


        //textView.append("Filter type: "+String.valueOf(editText.getText()));

        for (int i = 0; i < foodTruckData.size(); i++) {
            //textView.setText(choice);
            if (foodTruckData.get(i).getName().equalsIgnoreCase("curry truck"))
            {
                textView.append("NAME:" + foodTruckData.get(i).getName() + "\nTYPE:" + foodTruckData.get(i).getType()
                        + "\nURL:" + foodTruckData.get(i).getCusine() + "\nCUSINE:" + foodTruckData.get(i).getUrl()+"\n");
            }
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
        TestTrackingService.test(this);
        Tracking.createTracking(this);
        try {
            SimpleDateFormat sc = new SimpleDateFormat("dd/MM/yyyy");
            locs.setText("ON "+D+"/"+M+"/"+Y+" at "+hour+":"+mins);
            List<TrackingService.TrackingInfo> trackingInfos = new ArrayList<>();
            trackingInfos.addAll(trackingService.getTrackingInfoForTimeRange(sc.parse(D+"/"+M+"/"+Y),mins,00));
            tracking = new Tracking(sc.parse(D+"/"+M+"/"+Y),hour,mins);
        } catch (Exception e) {
            TextView textView1 = findViewById(R.id.exeptionArea);
            textView1.setVisibility(View.VISIBLE);
            textView1.setText(e.getMessage());
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
            Y=year;
            M=month;
            D=day;
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
            hour=hourOfDay;
            mins=minute;


        }
    }

}










