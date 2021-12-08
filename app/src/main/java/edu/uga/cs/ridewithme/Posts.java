package edu.uga.cs.ridewithme;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single post. Date, time, departing location, arrival location.
 */
public class Posts{
    private String date, time, departState, departCity, arrivalState, arrivalCity, userType = "rider";
    private boolean isAccepted, isConfirmed;
    private String acceptedBy = "none";
    private int points;

    /**
     * Default Constructor. Posts start out as a rider with all values as null, and accepted by none.
     */
    public Posts(){
        this.date = null;
        this.time = null;
        this.departState = null;
        this.arrivalState = null;
        this.departCity = null;
        this.arrivalCity = null;
        this.userType = "rider";
        isAccepted = false;
        isConfirmed = false;
        acceptedBy = "none";
        points = 0;
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

    /**
     * Create a Map<String, Object> to help with adding the post to the database and displaying info correctly
     * @return
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Date", date);
        result.put("Time", time);
        result.put("Depart State", departState);
        result.put("Depart City", departCity);
        result.put("Arrival State", arrivalState);
        result.put("Arrival City", arrivalCity);
        result.put("User Type", userType);
        result.put("Is Accepted", isAccepted);
        result.put("Is Confirmed", isConfirmed);
        result.put("Accepted By", acceptedBy);
        result.put("Points", points);

        return result;
    }

    /**
     * Returns the post's departure date
     * @return post date
     */
    public String getDate(){return date;}

    /**
     * Sets the post's departure date
     * @param date post date
     */
    public void setDate(String date){this.date = date;}

    /**
     * Returns the departure time
     * @return departure time
     */
    public String getTime(){return time;}

    /**
     * Sets the post's departure time
     * @param time departure time
     */
    public void setTime(String time ){this.time = time;}

    /**
     * Returns the departure state
     * @return departure state
     */
    public String getDepartState(){return departState;}

    /**
     * Sets the departure state
     * @param departs departure state
     */
    public void setDepartState(String departs){this.departState = departs;}

    /**
     * Returns the arrival state
     * @return arrival state
     */
    public String getArrivalState(){return arrivalState;}

    /**
     * Sets the arrival state
     * @param arrivals arrival state
     */
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

    public boolean getIsAccepted(){
        return this.isAccepted;
    }
    public void setAccepted(boolean a){
        this.isAccepted = a;
    }

    public boolean getIsConfirmed(){
        return this.isAccepted;
    }
    public void setConfirmed(boolean a){
        this.isAccepted = a;
    }

    public int getPoints(){
        return this.points;
    }
    public void addPoints(int a){
        this.points += a;
    }

    public String toString() {
        return "" + date + " " + time + " " + departState + " " + departCity + " " + arrivalState + "" + arrivalCity;
    }
}