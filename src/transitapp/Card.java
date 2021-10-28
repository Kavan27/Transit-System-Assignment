package transitapp;

import java.util.ArrayList;

/**
 * The Card class that creates a card and keeps track of the card info, including balance, status,
 * the current trip the card is on, and the current bus the card is on
 */
class Card{
	public double balance;
	public int id;
	public Buses bus;
	public ArrayList<Trips> trips = new ArrayList<Trips>();
	public Trips currentTrip;
	public String status;
	
	/**
	 * Creates a card(card object) with the given id and balance, with its status set as idle(null)
	 * the current trip set as none, and the current bus set as none
	 * 
	 * @param id   the id of this card
	 * @param balance   the starting balance on the card
	 */
	public Card(int id, double balance) {
		this.id = id;
		this.balance = balance;
		this.bus = null;
		this.currentTrip = null;
		this.status = null;
	}
	
}
