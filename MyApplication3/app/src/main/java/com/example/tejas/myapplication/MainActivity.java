package com.example.tejas.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    int index;
    TextView textView;
    static int hour;
    static int mins;
    int h,m;
    List<Button> buttons = new ArrayList<>();
    List<Trackable> temp = new ArrayList<>();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View view) {
        buttons.add(findViewById(R.id.button4));
        buttons.add(findViewById(R.id.button3));
        buttons.add(findViewById(R.id.button2));
        textView = findViewById(R.id.textView);
        buttons.forEach(c->c.setVisibility(View.INVISIBLE));
        textView.setText(null);
        FoodTruckParser foodTruckParser = new FoodTruckParser();
        List<Trackable> foodTruckData;
        foodTruckData=foodTruckParser.getTrackableList(getApplicationContext());
        EditText editText = findViewById(R.id.plain_text_input);


        textView.append("Filter type: "+String.valueOf(editText.getText()));
        textView.setVisibility(View.VISIBLE);

        AtomicInteger i= new AtomicInteger();
        foodTruckData.forEach((c)->{ if(c.getName().equalsIgnoreCase(String.valueOf(editText.getText()))){
            textView.append("\nNAME:" + c.getName() + "\nTYPE:" + c.getType()
                    + "\nCUSINE:" + c.getCusine() + "URL:" + c.getUrl());
            temp.add(c);
            buttons.get(i.get()).setVisibility(View.VISIBLE);
            i.getAndIncrement();
        }
        });
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


        TextView locs = findViewById(R.id.textView3);
        //
        // TestTrackingService.test(this);
        Tracking.createTracking(this);
        try {
            SimpleDateFormat sc = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            SimpleDateFormat hours = new SimpleDateFormat("mm");
            M++;
            locs.setText("ON "+D+"/"+M+"/"+Y+" at "+hour+":"+mins+" PM");
            M--;

            tracking = new Tracking(sc.parse(D+"/"+M+"/"+Y),hour,mins);
            //locs.append("\nINDEX=>"+Integer.parseInt(hours.format(t.date)+"\nFood truck co-ordinates:");
            List <TrackingService.TrackingInfo> trackingInfos = new ArrayList<>();

            for (TrackingService.TrackingInfo t:tracking.getData()
                 ) {
                if ((t.trackableId==index)&&(Integer.parseInt(hours.format(t.date))>=mins-5&&Integer.parseInt(hours.format(t.date))<=mins+5))
                {
                    if (!trackingInfos.contains(t))
                        trackingInfos.add(t);
                }
            }

            Set<TrackingService.TrackingInfo> hs = new HashSet<>();
            hs.addAll(trackingInfos);
            trackingInfos.clear();
            trackingInfos.addAll(hs);

            for (int i=0;i<trackingInfos.size();i++)
            locs.append("\nTrackable ID = "+trackingInfos.get(i).trackableId+" SIZE= "+trackingInfos.size()+"\nCo-ordinates: "+trackingInfos.get(i).latitude+" , "
                    +trackingInfos.get(i).longitude+" at "+time.format(trackingInfos.get(i).date)+" Stop Time: "+trackingInfos.get(i).stopTime);

        } catch (Exception e) {
            TextView textView1 = findViewById(R.id.exeptionArea);
            textView1.setVisibility(View.VISIBLE);
            textView1.setText(e.getMessage());
        }


    }

    public void onButton2Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(temp.get(2).getName()+" was selected!!!");
        index=3;

    }

    public void onButton3Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(temp.get(1).getName()+" was selected!!!");
        index=2;
    }

    public void onButton4Click(View view) {
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(temp.get(0).getName()+" was selected!!!");
        index=1;

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










