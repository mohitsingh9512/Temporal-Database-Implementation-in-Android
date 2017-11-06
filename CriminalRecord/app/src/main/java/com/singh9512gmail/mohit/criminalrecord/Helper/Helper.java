package com.singh9512gmail.mohit.criminalrecord.Helper;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by tusharaggarwal on 06/11/17.
 */

public class Helper {

    public static String getTodayDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String randomAddress(){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("6649 N Blue Gum St",
                "4 B Blue Ridge Blvd","8 W Cerritos Ave #54","639 Main St","34 Center St","3 Mcauley Dr",
                "7 Eads St","7 W Jackson Blvd","5 Boston Ave #88","228 Runamuck Pl #2808","2371 Jerrold Ave",
                "37275 St  Rt 17m M"));

        Random rand = new Random();
        int  n = rand.nextInt(12);
        return arrayList.get(n);
    }
}
