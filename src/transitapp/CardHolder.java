package transitapp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a CardHoldes that can add and store cards and monthly cost.
 */
class CardHolder {
	private String name;
	private String email;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public ArrayList<Trips> trips = new ArrayList<Trips>();
	public Trips currentTrip;
	public String location;
	public HashMap<Integer, Double> monthlyCosts = new HashMap<Integer, Double>();
	
	/**
	 * Creates a new CardHolder with the given name and email (email must be unique for every instance of the class)	
	 * @param name The name of the CardHolder
	 * @param email The unique email of the CardHolder
	 */
	public CardHolder(String name, String email) {
		this.name = name;
		this.email = email;
		this.location = null;
		this.currentTrip = null;
	}
	
	/**
	 * Getter method for attribute Name
	 * @return String representation of Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter method for attribute Email
	 * @return String representation of Email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter method for attribute Name
	 */
	public void setName(String s) {
		this.name = s;
	}
	
	/**
	 * Creates and adds a new Card to the list of cards
	 * @param id an unique id
	 * @return True if a new card has been created, False otherwise
	 */
	public boolean addCard(int id) {
		for (Card c: this.cards) {
			if (c.id == id) {
				return false;
			}
		}
		this.cards.add(new Card(id, Main.INITIAL_BALANCE));
		return true;
	}
	
	/**
	 * Adds the amount to the balance of the card c
	 * @param c Card
	 * @param amount double representing the amount to be added
	 * @return True if the card it found and the amount is valid, False otherwise
	 */
	public boolean addBalance(Card c, int amount) {
		if (!cards.contains(c) || amount < 0) {
			return false;
		}
		c.balance += amount;
		return true;
	}
	
	/**
	 * Updates the monthly cost with the provided date and amount
	 * @param date int representation of date in days (1 - 365)
	 * @param amount double representing the amount to be added
	 */
	public void updateMonthlyCosts(int date, double amount) {
		if (date >= 1 && date <= 31) {
			if (monthlyCosts.containsKey(1)) {
				monthlyCosts.replace(1, monthlyCosts.get(1) + amount);
			} else {
				monthlyCosts.put(1, amount);
			}
		}
		else if (date > 31 && date <= 59) {
			if (monthlyCosts.containsKey(2)) {
				monthlyCosts.replace(2, monthlyCosts.get(2) + amount);
			} else {
				monthlyCosts.put(2, amount);
			}
		}
		else if (date > 59 && date <= 90) {
			if (monthlyCosts.containsKey(3)) {
				monthlyCosts.replace(3, monthlyCosts.get(3) + amount);
			} else {
				monthlyCosts.put(3, amount);
			}
		}
		else if (date > 90 && date <= 120) {
			if (monthlyCosts.containsKey(4)) {
				monthlyCosts.replace(4, monthlyCosts.get(4) + amount);
			} else {
				monthlyCosts.put(4, amount);
			}
		}
		else if (date > 120 && date <= 151) {
			if (monthlyCosts.containsKey(5)) {
				monthlyCosts.replace(5, monthlyCosts.get(5) + amount);
			} else {
				monthlyCosts.put(5, amount);
			}
		}
		else if (date > 151 && date <= 181) {
			if (monthlyCosts.containsKey(6)) {
				monthlyCosts.replace(6, monthlyCosts.get(6) + amount);
			} else {
				monthlyCosts.put(6, amount);
			}
		}
		else if (date > 181 && date <= 212) {
			if (monthlyCosts.containsKey(7)) {
				monthlyCosts.replace(7, monthlyCosts.get(7) + amount);
			} else {
				monthlyCosts.put(7, amount);
			}
		}
		else if (date > 212 && date <= 243) {
			if (monthlyCosts.containsKey(8)) {
				monthlyCosts.replace(8, monthlyCosts.get(8) + amount);
			} else {
				monthlyCosts.put(8, amount);
			}
		}
		else if (date > 243 && date <= 273) {
			if (monthlyCosts.containsKey(9)) {
				monthlyCosts.replace(9, monthlyCosts.get(9) + amount);
			} else {
				monthlyCosts.put(9, amount);
			}
		}
		else if (date > 273 && date <= 304) {
			if (monthlyCosts.containsKey(10)) {
				monthlyCosts.replace(10, monthlyCosts.get(10) + amount);
			} else {
				monthlyCosts.put(10, amount);
			}
		}
		else if (date > 304 && date <= 334) {
			if (monthlyCosts.containsKey(11)) {
				monthlyCosts.replace(11, monthlyCosts.get(11) + amount);
			} else {
				monthlyCosts.put(11, amount);
			}
		}
		else if (date > 334 && date <= 365) {
			if (monthlyCosts.containsKey(12)) {
				monthlyCosts.replace(12, monthlyCosts.get(12) + amount);
			} else {
				monthlyCosts.put(12, amount);
			}
		}
	}
}