package com.example.tejas.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodTruckParser {

    private List<Trackable> trackables = new ArrayList<>();
    public List<Trackable> getTrackableList(Context context) {
        try {
            Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.food_truck_data));
            scanner.useDelimiter(",");
            while (scanner.hasNext()){
                //System.out.println("SYSout====>>>"+scanner.next()+" "+scanner.next()+" "+scanner.next()+" "+scanner.next()+" "+scanner.next());
                trackables.add(new Trackable(scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next()));

            }
                return trackables;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
