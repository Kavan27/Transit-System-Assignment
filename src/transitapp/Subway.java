package transitapp;

import java.util.ArrayList;


/**
 * Represents a Subway which has a ArrayList of stations this subway stops at,
 * the stations are in order of which they appear, so the first stop is at index
 * 0 and the last stop is at the last index of the ArrayList.
 *
 */
public class Subway extends TransitSystem{
    //Cost of the subway *Might need to be global*
    private double SUBWAY_COST;
    public ArrayList<String> stations = new ArrayList<String>();
    private String location;
    //stations should be in order of what comes first when riding the train
    /**
     * Creates a new subway with stations and the cost
     * 
     * @param stations An ArrayList of stations in order of which they appear when riding a subway
     * @param cost The cost of the subway for each station
     */
    public Subway(ArrayList<String> stations, double cost){
        this.stations = stations;
        this.SUBWAY_COST = cost;
    }
    //Adds a stop at the given index *I DONT THINK THIS IS NEEDED*
    /**
     * Adds a station at the index given
     * 
     * @param stop The stop location name that needs to be added 
     * @param i The index at which the stops needs to be added at
     */
    public void addStop(String stop,int i){
        this.stops.add(i,stop);
    }

    /**
     * 
     * Called when the cardholder attempts to tap to get on the subway. If the the tap on is valid it will create a trips object
     * and add it to the cardholders trips and updates the cards trips.Then returns True if it is a valid entry, if not it returns False.
     * 
     * @param person The cardholder that is trying to enter the subway
     * @param location The location at which the person is trying to enter the subway 
     * @param card The card that the person is using to tap in the subway
     * @param time The time that the user is trying to enter the subway at
     * @param day The day that the cardholder is trying to enter the subway at
     * @return True if the cardholder enters the subway successfully and false if it doesnt enter the subway successfully
     */
    public boolean enterSubway(CardHolder person, String location, Card card, int time,int day) {
		if (card.status != null) {
			System.out.println("Invalid Input: Please tap out before tapping in");
			return false;
		}
    	if (card.balance >= 0){
	    	if (card.currentTrip == null) {
	    		Trips trip = new Trips(location, time,day);
	    		trip.enterTrip(location, time, day);
	    		card.currentTrip = trip;
	    		person.currentTrip = trip;

	    	}
	    	else {
	    		if (!card.currentTrip.enterTrip(location, time,day)) {
	    			person.trips.add(person.currentTrip);
					card.trips.add(card.currentTrip); //add in FeaTURES  or fix
	    			Trips trip = new Trips(location, time,day);
	    			trip.enterTrip(location, time, day);


	        		card.currentTrip = trip;
	        		person.currentTrip = trip;
	    		}
	    	}
    	}
    	else {
    		System.out.println("Balance is not sufficent!");
    	}
    	this.location = location;
    	Main.admin.updateDailyStops(day, 1);
		card.status = "S";
		return true;
    }
    /**
     * Called when the cardholder attempts to tap to get off the subway, and deducts the balance if the tap off 
     * is valid. Then returns True if it is a valid exit, if not it returns False.
     * 
     * @param person
     * @param location
     * @param card
     * @param time
     * @param day
     * @return
     */
    public boolean exitSubway(CardHolder person, String location, Card card, int time,int day) {
		if (!card.status.equals("S")) {
			System.out.println("Invalid Input");
			return false;
		}
    	if (this.stations.indexOf(location) < this.stations.indexOf(this.location)) {
    		System.out.println("Invalid Input: Not a valid route");
    		return false;
    	}
		String last_loc = card.currentTrip.getLastLoc();
    	int index_last = this.stations.indexOf(last_loc);
    	int index_curr = this.stations.indexOf(location);
    	Main.admin.updateDailyStops(day, index_curr - index_last);
    	card.currentTrip.exitTrip(location, time, day);
    	int startIndex = stations.indexOf(card.currentTrip.taps.get(card.currentTrip.taps.size() - 1).get(0));
		int endIndex = stations.indexOf(location);
		double tripCost = this.SUBWAY_COST * (endIndex - startIndex);
    	if (card.currentTrip.getSpent() + tripCost < Main.MAXCOST){
    		card.currentTrip.updateSpent(tripCost);
    		card.balance -= tripCost;
    		Main.admin.updateDailyRevenue(day, tripCost);
    		person.updateMonthlyCosts(day, tripCost);
    	}
    	else if(card.currentTrip.getSpent() < Main.MAXCOST) {
    		card.balance -= Main.MAXCOST - card.currentTrip.getSpent();
    		card.currentTrip.updateSpent(Main.MAXCOST - card.currentTrip.getSpent());
    		Main.admin.updateDailyRevenue(day, Main.MAXCOST - card.currentTrip.getSpent());
    		person.updateMonthlyCosts(day, Main.MAXCOST - card.currentTrip.getSpent());
    	}
    	this.location = null;
		card.status = null;
		return true;
    }
}
