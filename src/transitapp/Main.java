// These is a placeholder package and placeholder class
// Feel free to rename or remove these when you add in your own code (just make sure to add/commit/push any changes made,
//		and let your teammates know to pull the changes. Follow the workflow in the a2 instructions)

package transitapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class that runs the main program by getting input from the user aswell as storing
 * all the information
 */
public class Main {
	//GLOBAL VARIABLES
	public static double MAXCOST = 6.00;
	private static int BUS_COST = 2;
	private static double SUB_COST = 0.5;
	public static double INITIAL_BALANCE = 1.0;
	
	//Other instance Variables
	private static Buses changeBus;
	private static ArrayList<CardHolder> cardholders = new ArrayList<CardHolder>();
	private static ArrayList<Buses> bus = new ArrayList<Buses>();
	private static ArrayList<String> stations = new ArrayList<String>();
	private static Subway subway;
	public static Admin admin = new Admin();

	/**
	 * Starts and runs the program and gets user input from the user
	 * @param args
	 */
	public static void main(String[] args) {

		while (true) {
			System.out.println("If you want to use preset city 1, enter 1");
			System.out.println("If you want to use preset city 2, enter 2");
			System.out.println("If you want to create your own city, enter 3\nEnter Choice: ");
			Scanner in_city = new Scanner(System.in);
			String cityType = in_city.next();

			if (cityType.equals("1")) {
				stations.add("a");
				stations.add("b");
				stations.add("c");
				subway = new Subway(stations, SUB_COST);

				for (int x = 0; x < 2; x++) {
					bus.add(new Buses(BUS_COST, x + 1));
				}

				bus.get(0).addBusStop("1");
				bus.get(0).addBusStop("a");
				bus.get(0).addBusStop("2");

				bus.get(1).addBusStop("b");
				bus.get(1).addBusStop("3");

				System.out.println("Welcome to Patel City!");
				break;
			} 
			else if (cityType.equals("2")){
				stations.add("a");
				stations.add("b");
				stations.add("c");
				stations.add("d");
				stations.add("e");
				stations.add("f");
				stations.add("g");
				stations.add("h");
				stations.add("i");
				stations.add("j");
				stations.add("k");
				stations.add("l");
				
				subway = new Subway(stations, SUB_COST);

				for (int x = 0; x < 4; x++) {
					bus.add(new Buses(BUS_COST, x + 1));
				}

				bus.get(0).addBusStop("1");
				bus.get(0).addBusStop("a");
				bus.get(0).addBusStop("2");

				bus.get(1).addBusStop("b");
				bus.get(1).addBusStop("3");
				
				bus.get(2).addBusStop("4");
				bus.get(2).addBusStop("5");
				bus.get(2).addBusStop("c");
				
				bus.get(3).addBusStop("6");
				bus.get(3).addBusStop("j");
				bus.get(3).addBusStop("7");

				System.out.println("Welcome to Patel City!");
				break;
			}
			else if (cityType.equals("3")) {
				while (true) {
					try {
						addSubway();
						break;
					}
					catch (Exception e) {
						System.out.println("Invalid input: Please enter in a number");
					}
				}
				while (true) {
					try {
						addBusStop();
						break;
					}
					catch (Exception e) {
						System.out.println("Invalid input: Please enter in a number");
					}
				}
				System.out.println("Whats the name of your city: ");
				Scanner in_ncity = new Scanner(System.in);
				String ncityType = in_ncity.next();
				System.out.println("Welcome to " + ncityType + " City!");
				break;

			} else {
				System.out.println("--------Error: Please enter a valid option------------");
			}
		}

		while (true) {
			System.out.print("If you are a user, enter 1\n");
			System.out.println("If you are an admin, enter 2\nEnter Choice: ");
			Scanner in = new Scanner(System.in);
			String userType = in.next();

			if (userType.equals("1")) {
				System.out.print("1 - Enter Bus\n2 - Exit Bus\n3 - Enter Subway\n4 - Exit Subway\n5 - Register as card holder\n6 - Card Options \n7 - Get last 3 trips\n8 - Change Name\n9 - Get average transit cost(per month)\n10 - Main Menu\n");
				System.out.println("Enter Choice(1-10): ");
				while (true) {
					Scanner in2 = new Scanner(System.in);
					String input = in2.next();
					try {
						int num = Integer.parseInt(input);
						if (num >= 1 && num <= 10) {
							doTaskUser(input);
							break;
						} else {
							System.out.println("--------Error: Please enter a valid option------------");
							System.out.println("Enter Choice(1-10): ");
						}
					} catch (Exception e) {
						System.out.println("--------Error: Please enter a valid option------------");
						System.out.println("Enter Choice(1-10): ");
					}
				}
			} else if (userType.equals("2")) {
				System.out.println("1 - Print Daily Report\n2 - Get Total Revenue\n3 - Add Subway Station\n4 - Add Bus route");
				System.out.println("Enter Choice(1-4): ");
				while (true) {
					Scanner in3 = new Scanner(System.in);
					String input = in3.next();
					try {
						int num = Integer.parseInt(input);
						if (num >= 1 && num <= 4) {
							doTaskAdmin(input);
							break;
						} else {
							System.out.println("--------Error: Please enter a valid option------------");
							System.out.println("Enter Choice(1-4): ");
						}
					}
					catch (Exception e) {
						System.out.println("--------Error: Please enter a valid option------------");
						System.out.println("Enter Choice(1-4): ");
					}
				}
			} else {
				System.out.println("--------Error: Please enter a valid option------------");
			}
		}
	}

	/**
	 * Calls admin tasks based on the input.
	 * @param userInput String representation of the user input (1-4)
	 */
	private static void doTaskAdmin(String userInput) {
		int input = Integer.parseInt(userInput);
		switch (input) {
			case 1:
				printDailyReport();
				break;
			case 2:
				System.out.println("The total revenue is $" + admin.getTotalRevenue());
				break;
			case 3:
				addSubwayStation();
				break;
			case 4:
				addBusRoute();
				break;
		}
	}
	
	/**
	 * Calls user tasks based on the input.
	 * @param userInput String representation of the user input (1 - 10)
	 */
	public static void doTaskUser(String userInput) {
		int input = Integer.parseInt(userInput);
		// Do the task based on input
		switch (input) {
			case 1:
				enterBus();
				break;
			case 2:
				exitBus();
				break;
			case 3:
				enterTrain();
				break;
			case 4:
				exitTrain();
				break;
			case 5:
				addCardHolder();
				break;
			case 6:
				doCardTask();
				break;
			case 7:
				getLastTrips();
				break;
			case 8:
				changeName();
				break;
			case 9:
				getAverageCost();
				break;
			case 10:
				break;
		}
	}
	
	/**
	 * Does card tasks
	 */
	public static void doCardTask() {
		System.out.println("1 - Add Card \n2 - Add Balance \n3 - Check Balance \n4 - Suspend Card \n5 - Main Menu");
		Scanner in = new Scanner(System.in);
		int cardRequest = in.nextInt();
		if (cardRequest == 1) {
			addCard();
			return;
		}
		if (cardRequest == 2) {
			addBalance();
			return;
		}
		if (cardRequest == 3) {
			getBalance();
			return;
		}
		if (cardRequest == 4) {
			suspendCard();
			return;
		}
		if (cardRequest == 5) {
			return;
		}
		else {
			System.out.println("Invalid input, Please pick a number between 1 and 5");
			return;
		}
	}

	/**
	 * Suspends the given card
	 */
	public static void suspendCard() {
		String name = getString("Enter CardHolder Email:");
		Card currCard = null;

		int currCardHolder = -1;

		currCardHolder = getCurrCardHolder(name, currCardHolder);

		if (currCardHolder == -1) {
			System.out.println("Register as a Cardholder");
		}

		System.out.println("Enter card number to suspend:");
		Scanner inCard = new Scanner(System.in);
		int card = inCard.nextInt();

		for (Card c:cardholders.get(currCardHolder).cards){
			if (c.id == card) {
				currCard = c;
				break;
			}
		}

		if (checkValid(currCard == null, "Invalid Card")) return;
		Card cardSelected = currCard;
		System.out.println("Would You like to make another card to replace the suspended card");
		System.out.println("Enter Yes or No:");
		Scanner question = new Scanner(System.in);
		String q = question.next();
		if (q.equals( "Yes") || q.equals("yes")) {
			cardSelected.balance = 19.0;
			System.out.println("New card has been created with the id " + cardSelected.id);
			return;
		}
		else if(q.equals("No") || q.equals("no")) {
			cardholders.get(currCardHolder).cards.remove(card);
			System.out.println("Card has been susscessfully suspended");
		}
	}

	/**
	 * Finds and returns the current card holder
	 * @param name String representation of name
	 * @param currCardHolder index of the card holder
	 * @return index of the inputed card holder
	 */
	private static int getCurrCardHolder(String name, int currCardHolder) {
		for (int i = 0; i < cardholders.size(); i++) {
			if (cardholders.get(i).getEmail().equals(name)) {
				currCardHolder = i;
				break;
			}
		}
		return currCardHolder;
	}
	
	/**
	 * Calls enter bus method
	 */
	public static void enterBus() {
		CardHolder currCardHolder = null;
		Card currCard = null;
		int busnum = 0;
		int cardNumber = 0;
		int day = 0;
		int time = -1;
		String hours = "";

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;
		busnum = getIntInput("Input bus number:");

		if (busnum <= 0 || busnum > bus.size()) {
			System.out.println("Invalid Input: This Bus does not exist.");
			return;
		}
		String stop = getString("Input Stop entered at:");

		boolean stopExists = isStopExists(stop);

		if (checkValid(!stopExists, "Invalid Input: This Stop does not exist.")) return;

		boolean validStop = false;
		for (String s:bus.get(busnum - 1).stops){
			if (s.equals(stop)) {
				validStop = true;
				break;
			}
		}
		if (!validStop){
			System.out.println("Invalid input: This stop does not exist for this bus");
			return;
		}

		cardNumber = getIntInput("Enter card number:");
		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;
		day = getIntInput("Enter Current day(1 - 365):");

		if (checkValid(day < 1 || day > 365, "Invalid Input: Did not enter number between 1 and 365.")) return;

		while (time == -1) {
			hours = getString("Enter current time in 24 hour format (HH:MM): ");
			time = convertTime(hours);
		}
		if (checkValid(time < 1 || time > 1440, "Invalid Input: Did not enter number between 1 and 1440."))
			return;


		for (Buses b: bus) {
			if (b.busNumber == busnum && b.stops.contains(stop)) {
				if (b.enterBus(currCardHolder, stop, currCard, time, day)) {
					System.out.println("You have successfully entered the bus");
				}
			}
		}
	}

	/**
	 * Checks if the given stop exists
	 * @param stop String representation of a stop
	 * @return True if the stop exists, False otherwise
	 */
	private static boolean isStopExists(String stop) {
		boolean stopExists = false;
		for (Buses b : bus) {
			if (b.stops.contains(stop)) {
				stopExists = true;
			}
		}
		return stopExists;
	}
	
	/**
	 * Asks user for the input
	 * @param s String representation of the input
	 * @return int representation of the input if it exists
	 */
	private static int getIntInput(String s) {
		int num;
		while (true) {
			System.out.print(s);
			try {
				Scanner busIn = new Scanner(System.in);
				num = busIn.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invalid Input: Please enter in a number.");
			}
		}
		return num;
	}

	/**
	 * Helper that checks if the input is true and prints out String s if false
	 * @param b2 boolean 
	 * @param s String
	 * @return True if input is true, False otherwise
	 */
	private static boolean checkValid(boolean b2, String s) {
		if (b2) {
			System.out.println(s);
			return true;
		}
		return false;
	}

	/**
	 * Calls exit bus method based on the inputs
	 */
	public static void exitBus() {
		// Check if cardholder active
		// Change cardholder to notActive
		// Change location
		CardHolder currCardHolder = null;
		Card currCard = null;
		int cardNumber = 0;
		int day = 0;
		int time = -1;

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		String stop = getString("Input Stop exited at:");

		boolean stopExists = false;
		for (Buses b: bus) {
			if (b.stops.contains(stop)) {
				stopExists = true;
			}
		}

		if (checkValid(!stopExists, "Invalid Input: This Stop does not exist.")) return;

		cardNumber = getIntInput("Enter card number:");
		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;
		day = getIntInput("Enter Current day(1 - 365):");

		if (checkValid(day < 1 || day > 365, "Invalid Input: Did not enter number between 1 and 365.")) return;

		String hours;

		while (time == -1) {
			hours = getString("Enter current time in 24 hour format (HH:MM): ");
			time = convertTime(hours);
		}

		if (checkValid(time < 1 || time > 1440, "Invalid Input: Did not enter number between 1 and 1440."))
			return;

		Buses b = currCard.bus;
		if (b.exitBus(currCardHolder, stop, currCard, time, day)) {
			System.out.println("You have successfully exited the bus");
		}

		// EXIT BUS
	}

	/**
	 * Calls enter subway method based on the inputs
	 */
	public static void enterTrain() {
		CardHolder currCardHolder = null;
		Card currCard = null;
		int cardNumber = 0;
		int time = -1;
		int day = 0;
		String hours;

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		String stop = getString("Input Station entered at:");

		if (checkValid(!stations.contains(stop), "Invalid Input: This Station does not exist.")) return;

		//check for valid card input
		cardNumber = getIntInput("Enter card number:");

		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;

		//check for valid day input
		day = getIntInput("Enter Current day(1 - 365):");

		if (checkValid(day < 1 || day > 365, "Invalid Input: Did not enter number between 1 and 365.")) return;

		//check for valid time input
		while (time == -1) {
			hours = getString("Enter current time in 24 hour format (HH:MM): ");
			time = convertTime(hours);
		}

		if (checkValid(time < 1 || time > 1440, "Invalid Input: Did not enter number between 1 and 1440."))
			return;

		if (subway.enterSubway(currCardHolder,stop,currCard,time,day)) {
			System.out.println("You have successfully entered the subway");
		}


	}

	/**
	 * Calls exit subway method based on the inputs
	 */
	public static void exitTrain(){
		CardHolder currCardHolder = null;
		Card currCard = null;
		int time = -1;
		int day = 0;
		int cardNumber = 0;
		String hours;

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		String stop = getString("Input Station exited at:");

		if (checkValid(!stations.contains(stop), "Invalid Input: This Station does not exist.")) return;


		cardNumber = getIntInput("Enter card number:");

		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;


		//check for valid day input

		day = getIntInput("Enter Current day(1 - 365):");

		if (checkValid(day < 1 || day > 365, "Invalid Input: Did not enter number between 1 and 365.")) return;

		//check for valid time input
		while (time == -1) {
			hours = getString("Enter current time in 24 hour format (HH:MM): ");
			time = convertTime(hours);
		}


		if (checkValid(time < 1 || time > 1440, "Invalid Input: Did not enter number between 1 and 1440."))
			return;

		if (subway.exitSubway(currCardHolder,stop,currCard,time,day)) {
			System.out.println("You have successfully exited the subway");
		}

	}

	/**
	 * Adds card to the inputed CardHolder
	 */
	public static void addCard() {
		String name = getString("Enter Card Holder Email:");
		int currCardHolder = -1;
		currCardHolder = getCurrCardHolder(name, currCardHolder);
		if (checkValid(currCardHolder == -1, "This Card Holder does not exist, please register as a Card Holder."))
			return;
		int size = 0;
		if(!(cardholders.get(currCardHolder).cards.size() == 0)) {
			size = cardholders.get(currCardHolder).cards.get(cardholders.get(currCardHolder).cards.size() - 1).id + 1;
		}
		cardholders.get(currCardHolder).addCard(size);
		System.out.println("A card has been created with card number:" + size);
	}
	/**
	 * Takes the inputs of the card holder and card, and adds either
	 * 10, 20 or 50 $ to the users specified card then prints the new balance in the card.
	 * prints invalid input if the user input was invalid
	 */
	public static void addBalance() {
		Card currCard = null;
		CardHolder currCardHolder = null;
		int cardNumber = 0;

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		while (true) {
			System.out.println("Enter card number to add balance too (Card Id start at 0):");
			try {
				Scanner inCard = new Scanner(System.in);
				cardNumber = inCard.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invalid Input: Please enter in a number.");
			}
		}

		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;

		Card cardSelected = currCard;
		while (true) {
			try {
				System.out.println("Enter 1 to add $10");
				System.out.println("Enter 2 to add $20");
				System.out.println("Enter 3 to add $50");
				Scanner inAmount = new Scanner(System.in);
				int amount = inAmount.nextInt();
				if (amount == 1) {
					cardSelected.balance += 10;
					System.out.println("Added $10 to your balance. Your current balance is " + cardSelected.balance);
					break;
				} else if (amount == 2) {
					cardSelected.balance += 20;
					System.out.println("Added $20 to your balance. Your current balance is " + cardSelected.balance);
					break;
				} else if (amount == 3) {
					cardSelected.balance += 50;
					System.out.println("Added $50 to your balance. Your current balance is " + cardSelected.balance);
					break;
				} else {
					System.out.println("--------Error: Please enter a valid option------------");
				}
			} catch (Exception e) {
				System.out.println("--------Error: Please enter a valid option------------");
			}
		}
	}
	
	/**
	 * Given a current card holder and an email it finds the card holder account 
	 * specified by the user
	 * @param currCardHolder an empty input that gets replaced by the found card holder
	 * @param email the email inputed by the user
	 * @return returns the card holder assigned to the email inputed by the user.
	 */
	private static CardHolder getCardHolder(CardHolder currCardHolder, String email) {
		for (CardHolder c : cardholders) {
			if (c.getEmail().equals(email)) {
				currCardHolder = c;
			}
		}
		return currCardHolder;
	}
	
	/**
	 * Given the CardHolder and the card number this method returns the card associated
	 * with the given inputs.
	 * @param currCardHolder the card holder that is specified by the user
	 * @param cardNumber the card number for the card the user wants to use
	 * @return returns the card associated with the card number and the card holder inputed
	 */
	private static Card getCard(CardHolder currCardHolder, int cardNumber) {
		Card currCard = null;
		for (Card c : currCardHolder.cards) {
			if (cardNumber == c.id) {
				currCard = c;
			}
		}
		return currCard;
	}
	/**
	 * Makes a card holder object and adds it to the cardholders Array
	 * prints invalid input if the user information is invalid.
	 */
	public static void addCardHolder() { // TODO CHECK FOR VALID INPUT
		String name = getString("Enter your Name:");

		String email = getString("\nEnter Email:");

		for (CardHolder c: cardholders) {
			if (checkValid(c.getEmail().equals(email), "Invalid input: This email is already in use.\n")) return;
		}

		CardHolder person = new CardHolder(name, email);
		cardholders.add(person);
		System.out.println("You have been registered as a card holder");
	}
	
	/**
	 * Makes user specified number of train stations and adds them to the 
	 * stations Array.
	 */
	public static void addSubway() {
		System.out.print("How many Stations:");
		Scanner stationName = new Scanner(System.in);
		int stop = stationName.nextInt();
		for (int y = 0; y < stop; y++) {
			String name = getString("What is the name of this station:");
			stations.add(name);
		}
		subway = new Subway(stations,SUB_COST);

	}
	/**
	 * Makes a user specified number of bus lines and makes Bus objects and adds stops for 
	 * each of the bus lines specified by the user and adds the bus objects to 
	 * the buses Array.
	 */
	public static void addBusStop() {
		System.out.print("How many buses will there be:");
		Scanner numberOfBuses = new Scanner(System.in);
		int busNum = numberOfBuses.nextInt();
		for (int x = 0; x < busNum; x++) {
			bus.add(new Buses(BUS_COST, x+1));
		}
		for (int i = 0; i < bus.size(); i++) {
			System.out.println("Changing bus line " + (i + 1));
			changeBus = bus.get(i);
			System.out.print("How many bus stops does this route have:");
			Scanner routeIn = new Scanner(System.in);
			int stop = routeIn.nextInt();
			for (int y = 0; y < stop; y++) {
				String name = getString("What is the name of this bus Stop:");
				changeBus.addBusStop(name);
			}
		}
	}

	/**
	 * prints the current balance of the users specified card
	 * and prints invalid input if user information is invalid
	 */
	public static void getBalance() {
		Card currCard = null;
		CardHolder currCardHolder = null;
		int cardNumber = 0;

		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		while (true) {
			System.out.println("Enter card number:");
			try {
				Scanner inCard = new Scanner(System.in);
				cardNumber = inCard.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invalid Input: Please enter in a number.");
			}
		}

		currCard = getCard(currCardHolder, cardNumber);

		if (checkValid(currCard == null, "Invalid Input: This Card does not exist.")) return;

		Card cardSelected = currCard;

		System.out.println("The balance of this card is " + cardSelected.balance);

	}
	/**
	 * Changes the user's name and prints invalid input if the user inputed
	 * invalid information
	 */
	public static void changeName() {
		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");
		CardHolder currCardHolder = null;
		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;
		String newName = getString("Enter the new Name:");
		currCardHolder.setName(newName);
		System.out.println("Successfully changed named to " + newName);
	}

	/**
	 * Prints the latest 3 trips (or less depending on how many trips the user has completed)
	 * prints invalid input if the user information is invalid.
	 */
	public static void getLastTrips() {
		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");
		CardHolder currCardHolder = null;
		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;
		if (currCardHolder.trips.size() == 0) {
			System.out.println("No trips have been recorded");
		}
		else if((currCardHolder.trips.size() == 1)) {
			System.out.println("Trip 1: \n");
			System.out.println(currCardHolder.trips.get(0));
		}
		else if((currCardHolder.trips.size() == 2)) {
			System.out.println("Trip 2: \n");
			System.out.println(currCardHolder.trips.get(1) + "\n");
			System.out.println("Trip 1: \n");
			System.out.println(currCardHolder.trips.get(0) + "\n");
		}
		else {
			int i3 = currCardHolder.trips.size() - 1;
			int i2 = currCardHolder.trips.size() - 2;
			int i1 = currCardHolder.trips.size() - 3;
			System.out.println("Trip" + i3 + ": \n");
			System.out.println(currCardHolder.trips.get(i3) + "\n");
			System.out.println("Trip" + i2 + ": \n");
			System.out.println(currCardHolder.trips.get(i2) + "\n");
			System.out.println("Trip" + i1 + ": \n");
			System.out.println(currCardHolder.trips.get(i1) + "\n");
		}

	}
	/**
	 * Prints the daily admin report and prints invalid input if the day provided
	 * is invalid
	 */
	public static void printDailyReport() {
		int day = 0;
		day = getIntInput("Enter in day(1 - 365):");

		if (checkValid(day < 1 || day > 365, "Invalid Input: Did not enter number between 1 and 365.")) return;

		System.out.println(admin.dailyReport(day));
	}
	/**
	 * Takes the average monthly cost for the given card holder and prints
	 * the cost and prints error if user information is invalid
	 */
	public static void getAverageCost() {
		CardHolder currCardHolder = null;
		if (checkValid(cardholders.isEmpty(), "No Card Holders are registered. Please register as a Card Holder first\n"))
			return;

		String email = getString("Enter CardHolder Email:");

		currCardHolder = getCardHolder(currCardHolder, email);

		if (checkValid(currCardHolder == null, "Invalid Input: This Card Holder does not exist.")) return;

		double sum = 0;
		int counter = 0;
		for (int month: currCardHolder.monthlyCosts.keySet()) {
			sum += currCardHolder.monthlyCosts.get(month);
			counter += 1;
		}

		if (sum == 0) {
			System.out.println("Your average monthly cost is $0.0");
		}
		else {
			double average = sum / counter;
			System.out.println("Your average monthly cost is $" + average);
		}

	}
	/**
	 * Asks for an input from the user and returns the user input as a output
	 * @param s the message to be printed out
	 * @return returns the user input
	 */
	private static String getString(String s) {
		System.out.print(s);
		Scanner in = new Scanner(System.in);
		return in.next();
	}
	
	/**
	 * Converts the given time as a string in the HH:MM format into minutes
	 * also checking for if the time is a valid format
	 * @param time takes a string input of time in the format HH:MM
	 * @return returns the given time in minutes and returning -1 if the time is in a non
	 * valid format
	 */
	private static int convertTime(String time) {
		try {
			if (time.length() == 5 && time.charAt(2) == ':') {
				int hour = Integer.parseInt(time.substring(0, 2));
				int min = Integer.parseInt(time.substring(3, 5));
				if (!(hour >= 0 && hour < 24 && min >= 0 && min < 60)) {
					System.out.println("Invalid time format, please enter valid inputs for hours and minutes");
					return -1;
				}
				return (hour * 60) + min;
			} else {
				System.out.println("Invalid time format, please enter time in format hh:mm");
				return -1;
			}
		}
		catch(Exception e){
			System.out.println("Invalid time format, please enter time in format hh:mm");
			return -1;
		}
	}
	
	/**
	 * Adds a new subway station to the subway line
	 */
	private static void addSubwayStation() {
		String name = getString("What is the name of this station:");
		subway.stations.add(name);
		stations.add(name);
	}
	
	/**
	 * Adds a new bus route with new bus stops
	 */
	private static void addBusRoute() {
		bus.add(new Buses(BUS_COST, bus.size() + 1));
		System.out.print("How many bus stops does this route have:");
		Scanner routeIn = new Scanner(System.in);
		changeBus = bus.get(bus.size() - 1);
		int stop = routeIn.nextInt();
		for (int y = 0; y < stop; y++) {
			String name = getString("What is the name of this bus Stop:");
			changeBus.addBusStop(name);
		}
	}
}