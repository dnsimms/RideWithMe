package edu.uga.cs.ridewithme;

import android.content.Context;

public class Track_Points {

    private int points = 300;
    private static Track_Points pastInstance;

    public Track_Points(){
        this.points = 100;
    }

    public void setPoints(int a){
        this.points = a;
    }

    public int getPoints(){
        return this.points;
    }

    private Track_Points(Context context){

    }

    public static synchronized Track_Points getInstance(Context context){
        if(pastInstance == null){
            pastInstance = new Track_Points(context.getApplicationContext());
        }
        return pastInstance;
    }

}
