package transitapp;
import java.util.ArrayList;
/**
* Represents one continuous trip of the user. Keeps track of when the person enters a bus or a train
* while checking if the time given is valid and the given location is valid, also makes a 
* a nested ArrayList which keeps track of the points within the trip.
*/
public class Trips {
	private double spent;
	private int allTime;
	private int lastTime;
	private int lastDay;
	private String lastLoc;
    public ArrayList<ArrayList<String>> taps = new ArrayList<ArrayList<String>>();
    
    /**
     * Initializes the trip with the given location, time, and day
     * and it sets a time variable to 0.0 and a amount spent to 0
     * @param location it is the initial location of the entry when the trip is initialized
     * @param time it is the initial time of the entry when the trip in initialized
     * @param day it is the day if the entry when the trip is initialized
     */
    public Trips(String location, int time, int day){
    	this.lastLoc = location;
    	this.lastTime = time;
    	this.allTime = 0;
    	this.spent = 0.0;
    	this.lastDay = day;
    	
    /**
     * This is an enter trip, it is called when the user enters the bus or enters the subway
     * it checks if the parameters given are valid and checks if the time given is valid.
     * If every input is valid it adds the location to the nested ArrayList and returns true else 
     * returns false.
     * @param location the entry location of the user
     * @param entertime the entry time of the user in minutes
     * @param day the entry day of the user
     * @return a boolean, true if all given inputs are valid else false.
     */
    }
    public Boolean enterTrip(String location, int entertime, int day) {
    	if (!(this.validTime(entertime) && this.validLoc(location))) {
    		return false;
    	}
    	if (entertime < this.lastTime) {
    		this.allTime += (1440 - this.lastTime) + entertime;
    		this.lastDay = day;
    	}
    	else if (this.lastDay + 1 <= day) {
    		return false;
    	}
    	else {
    		this.allTime += entertime-this.lastTime;
    		this.lastDay = day;
    	}
    	this.lastTime = entertime;
    	ArrayList<String> s =  new ArrayList<String>();
    	s.add(location);
    	this.taps.add(s);
    	return true;
    }
    
    /**
     * This is an exit trip, it is called when the user exits the bus or exits the subway
     * it checks if the parameters given are valid and checks if the time given is valid.
     * If every input is valid it adds the location to the nested ArrayList and returns true else 
     * returns false.
     * @param location the exit location of the user
     * @param exittime the exit time of the user in minutes
     * @param day the exit day of the user
     * @return a boolean, true if all given inputs are valid else false.
     */
    public void exitTrip(String location, int exittime, int day) {
    	this.lastLoc = location;
    	if (exittime < this.lastTime) {
    		this.allTime += (1440 - this.lastTime) + exittime;
    	}
    	else {
    		this.allTime += exittime - this.lastTime;
    	}
    	this.lastDay = day;
    	this.lastTime = exittime;
    	this.taps.get(taps.size() - 1).add(location);
    }
    
    /**
     * Checks if the given time is valid returns true if it is and 
     * false otherwise
     * @param time takes a time input in minutes
     * @return returns true if the time is valid and false otherwise
     */
    public Boolean validTime(int time) {
    	int temp = time - this.lastTime;
    	return this.allTime + temp <= 120;
    }
    
    /**
     * Checks if the given location is valid returns true if it is and 
     * false otherwise
     * @param location takes location
     * @return returns true if the location is valid and false otherwise
     */
    public Boolean validLoc(String location) {
    	return this.lastLoc.equals(location);
    }
    
    /**
     * keeps track of how much money is spent during the trip
     * @param money takes how much 1 bus ride or 1 subway ride costs
     */
    public void updateSpent(double money) {
    	this.spent += money;
    }
    
    /**
     * returns the amount of money currently spent during the trip
     * @return returns the amount of money currently spent during the trip
     */
    public double getSpent() {
    	return this.spent;
    }
    
    /**
     * returns the current location of the user in the trip 
     * @return returns the current location of the user in the trip 
     */
    public String getLastLoc() {
    	return this.lastLoc;
    }
    
    /**
     * Returns a string representation for the nested ArrayList to see the location points
     * for the duration of the trip
     * @return Returns a string representation for the nested ArrayList to see the location points
     * for the duration of the trip
     */
    public String toString() {
    	String acc = "";
    	for (ArrayList<String> l: this.taps) {
    		if(l.size() == 1) {
    			acc += l.get(0) + " -> Destination does not exist" + "\n";
    		}
    		else {
    		acc += l.get(0) + " -> " + l.get(1) + "\n";
    		}
    	}
    	return acc;
    }
}
