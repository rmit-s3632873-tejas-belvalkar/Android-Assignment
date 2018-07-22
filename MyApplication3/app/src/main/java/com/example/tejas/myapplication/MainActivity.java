package com.example.tejas.myapplication;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DialogFragment newFragment1,newFragment2;

    public static ArrayList<Trackable> trackableObj;
    TextView textView;


    MainActivity(){
        trackableObj = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View view) {
        textView = findViewById(R.id.textView);
        trackableObj.add(new Trackable(1,"tejas","student","indian","Google.com"));
        trackableObj.add(new Trackable(2,"rishi","student","indian","Google.com"));
        trackableObj.add(new Trackable(3,"babloo","student","indian","Google.com"));
        textView.setText(null);

        for (int i=0;i<trackableObj.size();i++){
            textView.append("\n\n\n\t"+trackableObj.get(i).getName()+"\n\t"+trackableObj.get(i).getType()
            +"\n\t"+trackableObj.get(i).getCusine()+"\n\t"+trackableObj.get(i).getUrl());
        }

        findViewById(R.id.button).setVisibility(View.INVISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.button3).setVisibility(View.VISIBLE);
        findViewById(R.id.button4).setVisibility(View.VISIBLE);

        trackableObj.removeAll(trackableObj);


    }

    public void goBack(View view){
        textView.setText(null);
        setContentView(R.layout.activity_main);
    }



    public void onButton3Click(View view){
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 3 clicked");
    }

    public void onButton4Click(View view){
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 4 clicked");
    }
    public void showTimePickerDialog(View v) {
        newFragment1= new TimePickerFragment();
        newFragment1.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        newFragment2 = new DatePickerFragment();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
    }
    public void onButton2Click(View view){
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);

        TrackingService trackingService = new TrackingService();
        //trackingService.getTrackingInfoForTimeRange();

        textView.setText("Button 2 clicked");

    }
}
