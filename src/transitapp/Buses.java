package transitapp;

import java.util.ArrayList;

/**
 * 
 * A bus class that has the cost of each bus ride and the stops that the bus route has
 * as well as the busses number.
 *
 */
public class Buses extends TransitSystem{
    //Cost of bus **might need to be global**
    private double BUS_COST;
    private String location; //MIGHT NOT NEED
    //public ArrayList<String> stops = new ArrayList<String>(); REMOVE THIS - new change
    public ArrayList<String> stops;
    public int busNumber;
    
    /**
     * Creates a new Bus which has a cost for each bus ride and a bus number,
     * the current stops of the bus route is empty
     * 
     * @param cost The cost 
     * @param bn
     */
    public Buses(double cost,int bn){
        this.BUS_COST = cost;
        this.busNumber = bn;
        this.stops = new ArrayList<String>(); //new change
    }
    
    /**
     * Gets and returns the bus number of this bus
     * 
     * @return the bus number of the bus
     */
    public int getBusNumber() {
    	return this.busNumber;
    }
    
    /**
     * Adds the stop to the end of the bus route, so the new stop is now the last stop of the bus
     * 
     * @param stop The name of the stop location
     */
    public void addBusStop(String stop) {
    	stops.add(stop);
    }
    
    /**
     * Attempts to let the cardholder enter the bus, and if it is a valid tap on it will 
     * deduct the balance from the card that is used,it will also update the trips of the person and card has
     *  and will return True, if it is not a valid tap in, it will return False.
     * 
     * @param person The cardholder person that is trying to tap into a bus
     * @param location The location at which the person is trying to tap on the bus at
     * @param card The card that the person is using to tap on the bus with
     * @param time The time that the person is tap in on the bus at
     * @param day The day at which the person is trying to tap on the bus
     * @return true if it is a valid tap on, if its not then return false
     */
    public boolean enterBus(CardHolder person, String location, Card card, int time, int day) {
    	if (card.status != null) {
    		System.out.println("Invalid Input: Please tap out before tapping in");
    		return false;
    	}
    	if (card.balance >= 0){
	    	if (card.currentTrip == null) {
	    		Trips trip = new Trips(location, time, day);
	    		trip.enterTrip(location, time, day);
	    		card.currentTrip = trip;
	    		person.currentTrip = trip;
	    		
	    	}
	    	else {
	    		if (!card.currentTrip.enterTrip(location, time, day)) {

					person.trips.add(person.currentTrip);
					card.trips.add(card.currentTrip);

	    			Trips trip = new Trips(location, time, day);
	    			trip.enterTrip(location, time, day);

	        		card.currentTrip = trip;
	        		person.currentTrip = trip;
	    		}
	    	}
	    	if (card.currentTrip.getSpent() + BUS_COST < Main.MAXCOST){
	    		card.currentTrip.updateSpent(BUS_COST);
	    		card.balance -= BUS_COST;
	    		Main.admin.updateDailyRevenue(day, BUS_COST);
	    		person.updateMonthlyCosts(day, BUS_COST);
	    	}
	    	else if(card.currentTrip.getSpent() < Main.MAXCOST) {
	    		card.balance -= Main.MAXCOST - card.currentTrip.getSpent();
	    		card.currentTrip.updateSpent(Main.MAXCOST - card.currentTrip.getSpent());
	    		Main.admin.updateDailyRevenue(day, Main.MAXCOST - card.currentTrip.getSpent());
	    		person.updateMonthlyCosts(day, Main.MAXCOST - card.currentTrip.getSpent());
	    	}
    	}
    	else {
    		System.out.println("Balance insufficient :(");
    		return false;
    	}
    	this.location = location;
    	Main.admin.updateDailyStops(day, 1);
    	card.bus = this;
    	card.status = "B";
    	return true;
    }
    /**
     * Attempts to let the cardholder person exit the bus, and if it is a valid tap on it will 
     * change the location of the person,it will also update the trips of the person and card has,
     * aswell as updating the total daily stops of the city.
     * This will return True, if it is a valid tap in, and if it isn't it will return False.
     * 
     * @param person The cardholder that is attempting to exit the bus
     * @param location The stop location when the person is trying to exit the bus at
     * @param card The card that the person is using to attempt to tap off the bus
     * @param time The time at which the user is trying to tap off the bus
     * @param day The day at which the user is trying to tap off the bus at
     * @return Returns true if it is a successful tap off, if not it will return false
     */
    public boolean exitBus(CardHolder person, String location, Card card, int time, int day) {
    	if (card.status != "B") {
    		System.out.println("Invalid Input");
    		return false;
    	}
    	if (this.stops.indexOf(location) < this.stops.indexOf(this.location)) {
    		System.out.println("Invalid Input: Not a valid route");
    		return false;
    	}
    	String last_loc = card.currentTrip.getLastLoc();
    	int index_last = this.stops.indexOf(last_loc);
    	int index_curr = this.stops.indexOf(location);
    	Main.admin.updateDailyStops(day, index_curr - index_last);
    	card.currentTrip.exitTrip(location, time, day);
    	card.status = null;
    	card.bus = null;
    	this.location = null;
    	return true;
    }
}
