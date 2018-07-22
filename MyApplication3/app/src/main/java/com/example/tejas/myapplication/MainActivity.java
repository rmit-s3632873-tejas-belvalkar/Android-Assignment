package com.example.tejas.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    DialogFragment timeFragment;

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void goBack(View view){
        textView.setText(null);
        setContentView(R.layout.activity_main);
    }

    public void onButton2Click(View view){
        setContentView(R.layout.tracking_screen);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Button 2 clicked");

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
}










