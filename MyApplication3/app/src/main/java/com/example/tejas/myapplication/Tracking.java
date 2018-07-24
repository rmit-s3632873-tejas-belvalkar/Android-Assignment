package com.example.tejas.myapplication;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tracking {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String trackingID = randomAlphaNumeric(5);

    public static void setDate(Date date) {
        Tracking.date = date;
    }

    static Date date;
    static int hours,minutes;
    String title;

    public List<TrackingService.TrackingInfo> getData() {
        return data;
    }

    static List<TrackingService.TrackingInfo> data = new ArrayList<>();



    Tracking(Date date,int hours,int minutes){
        Tracking.date=date;
        Tracking.hours=hours;
        Tracking.minutes=minutes;
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static void createTracking(Context context){
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        Log.i(trackingID, "Parsed File Contents:");
        trackingService.logAll();

        try
        {
            // 5 mins either side of 05/07/2018 1:05:00 PM
            // PRE: make sure tracking_data.txt contains valid matches
            // PRE: make sure device locale matches provided DateFormat (you can change either device settings or String param)
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            MainActivity m = new MainActivity();
            hours=m.getHour();
            if (hours>12) hours-=12;
            minutes=m.getMins();
            String formattedHours, formattedMins;
            if (hours>9)
                formattedHours= ""+hours;
            else formattedHours= "0"+hours;
            if (minutes>9)
                formattedMins= ""+minutes;
            else formattedMins= "0"+minutes;
            String searchDate = "05/07/2018 "+formattedHours+":"+formattedMins+":00 PM";
            int searchWindow = 5; // +/- 5 mins
            Date date = dateFormat.parse(searchDate);
            List<TrackingService.TrackingInfo> matched = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            Log.i(trackingID, String.format("Matched Query: %s, +/-%d mins", searchDate, searchWindow));
            trackingService.log(matched);
            data.addAll(trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
