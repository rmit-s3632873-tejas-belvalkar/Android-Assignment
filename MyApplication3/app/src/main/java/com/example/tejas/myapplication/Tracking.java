package com.example.tejas.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tracking {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String trackingID = randomAlphaNumeric(5);
    int trackableID;
    SimpleDateFormat targetStartTime;
    SimpleDateFormat targetEndTime;
    String title;
    SimpleDateFormat targetMeetTime;
    String currentLocation;
    String meetLocation;
    Date date;
    int periodMin;
    int peridSec;


    TrackingService trackingService = new TrackingService();
    public void intialize(){
        trackingService.getTrackingInfoForTimeRange(date,periodMin,peridSec);
    }
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}