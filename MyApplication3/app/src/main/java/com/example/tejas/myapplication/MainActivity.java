package com.example.tejas.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
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
        Spinner spinner = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void buttonOnClick(View view) {
        textView = findViewById(R.id.textView);
        trackableObj.add(new Trackable(1, "Tejas", "student", "Indian", "Google.com"));
        trackableObj.add(new Trackable(2, "Rishi", "student", "German", "Google.com"));
        trackableObj.add(new Trackable(3, "Babloo", "student", "Italian", "Google.com"));
        textView.setText(null);

        for (int i = 0; i < trackableObj.size(); i++) {
            //textView.setText(choice);
            //if (trackableObj.get(i).getCusine().equals("Indian"))
            textView.append("\n\t" + trackableObj.get(i).getName() + "\n\t" + trackableObj.get(i).getType()
                    + "\n\t" + trackableObj.get(i).getCusine() + "\n\t" + trackableObj.get(i).getUrl()+"\n");
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










