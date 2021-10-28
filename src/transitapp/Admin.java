package transitapp;

import java.util.HashMap;

/**
 * An admin class that stores the stats of the transit system, including total revenue, 
 * the revenue on any given day and the number of stops/stations reached by all card holders on
 * any given day
 */
public class Admin {
	
	private double totalRevenue;
	private HashMap<Integer, Double> dailyRevenue = new HashMap<Integer, Double>();
	private HashMap<Integer, Integer> dailyStops = new HashMap<Integer, Integer>();
	
	/**
	 * Creates an Admin(admin object) with total revenue set to 0
	 */
	public Admin() {
		this.totalRevenue = 0.0;
	}

	/**
	 * Updates the total revenue accumulated by this transit system
	 * 
	 * @param amount   the amount of money being added to the total revenue
	 */
	public void addRevenue(double amount) {
		this.totalRevenue += amount;
	}
	
	/**
	 * Returns the total revenue accumulated by this transit system
	 * 
	 * @return the total revenue of the transit system
	 */
	public double getTotalRevenue() {
		return this.totalRevenue;
	}
	
	/**
	 * Returns the total revenue accumulated on any given day
	 * 
	 * @param date   the day(between 1 and 365 inclusive)
	 * @return the revenue of the transit system on this day
	 */
	public double getDailyRevenue(int date) {
		if (dailyRevenue.containsKey(date)) {
			return dailyRevenue.get(date);
		} else {
			return 0.0;
		}
	}
	
	/**
	 * Returns the total number of stops reached by all card holders on any given day
	 * 
	 * @param date   the day(between 1 and 365 inclusive) 
	 * @return the number of stops/stations reached on this day
	 */
	public int getDailyNumStops(int date) {
		if (dailyStops.containsKey(date)) {
			return dailyStops.get(date); 
		} else {
			return 0;
		}
	}
	
	/**
	 * Updates the revenue accumulated on on a specific day
	 * 
	 * @param date   the day(between 1 and 365 inclusive)
	 * @param amount   the amount of money being added to the total revenue of the given day
	 */
	public void updateDailyRevenue(int date, double amount) {
		this.totalRevenue += amount;
		if (dailyRevenue.containsKey(date)) {
			dailyRevenue.replace(date, dailyRevenue.get(date) + amount);
		} else {
			dailyRevenue.put(date, amount);
		}
	}
	
	/**
	 * Updates the number of stops/stations reached by all card holders on on a specific day
	 * 
	 * @param date   the day(between 1 and 365 inclusive)
	 * @param numStops    the number of new stops/stations reached on this day which are being 
	 *        to the day total
	 */
	public void updateDailyStops(int date, int numStops) {

		if (dailyStops.containsKey(date)) {
			dailyStops.replace(date, dailyStops.get(date) + numStops);
		} else {
			dailyStops.put(date, numStops);
		}
	}
	
	/**
	 * Returns an output message(as a string representation) of the daily report for any given
	 * day, showing the number of stops/stations reached by all cardholders and the total revenue
	 * of the given day
	 * 
	 * @param day   the day(between 1 and 365 inclusive)
	 * @return a output message(String representation) of the daily report(stats for this day), 
	 *         which shows the stops/stations reached and revenue 
	 */
	public String dailyReport(int day) {
		String report = "---Day " + day + " Report---\n";
		if (dailyRevenue.containsKey(day)) {
			report += "Revenue: $" + dailyRevenue.get(day) + "\n";
		}
		else {
			report += "Revenue: $0.00\n";
		}
		if (dailyStops.containsKey(day)){
			report += "Number of stops/stations reached: " + dailyStops.get(day) + "\n";
		}
		else {
			report += "Number of stops/stations reached: 0\n";
		}
		return report;
	}
}