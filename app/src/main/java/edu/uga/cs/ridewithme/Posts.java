package edu.uga.cs.ridewithme;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single post. Date, time, departing location, arrival location.
 */
public class Posts{
    private String date, time, departState, departCity, arrivalState, arrivalCity, userType = "rider";

    public Posts(){
        this.date = null;
        this.time = null;
        this.departState = null;
        this.arrivalState = null;
        this.departCity = null;
        this.arrivalCity = null;
        this.userType = "rider";
    }

    public Posts(String date, String time, String departc, String departs,
                 String arrivalc, String arrivals){
        this.date = date;
        this.time = time;
        this.departState = departs;
        this.arrivalState = arrivals;
        this.departCity = departc;
        this.arrivalCity = arrivalc;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Date", date);
        result.put("Time", time);
        result.put("Depart State", departState);
        result.put("Depart City", departCity);
        result.put("Arrival State", arrivalState);
        result.put("Arrival City", arrivalCity);
        result.put("User Type", userType);

        return result;
    }

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getTime(){return time;}
    public void setTime(String time ){this.time = time;}

    public String getDepartState(){return departState;}
    public void setDepartState(String departs){this.departState = departs;}

    public String getArrivalState(){return arrivalState;}
    public void setArrivalState(String arrivals){this.arrivalState = arrivals;}

    public String getDepartCity(){return departCity;}
    public void setDepartCity(String departc){this.departCity = departc;}

    public String getArrivalCity(){return arrivalCity;}
    public void setArrivalCity(String arrivalc){this.arrivalCity = arrivalc;}

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String toString() {
        return "" + date + " " + time + " " + departState + " " + departCity + " " + arrivalState + "" + arrivalCity;
    }
}