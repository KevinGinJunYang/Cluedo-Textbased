import java.util.*;

public class Setup {

	private List<Card> characters = new ArrayList<>();
	private List<Card> rooms = new ArrayList<>();
	private List<Card> weapons = new ArrayList<>();
	private List<Player> dealCard = new ArrayList<Player>();
	private List<Player> players;
	private Board board = new Board();
	private Card[] solution = new Card[3];



	/**
	 * Sets up Cards Playable 
	 */
	public Setup(){
		setupCards();
		setupSolution();
	}

	/**
	 * Gets all characters, rooms and weapons 
	 */
	private void setupCards(){

		characters.add(new Characters("Miss Scarlett"));
		characters.add(new Characters("Colonel Mustard"));
		characters.add(new Characters("Mrs White"));
		characters.add(new Characters("Mr. Green"));
		characters.add(new Characters("Mrs. Peacock"));
		characters.add(new Characters("Professor Plum"));
		Collections.shuffle(characters);

		rooms.add(new Room("Conservatory"));
		rooms.add(new Room("Billiard Room"));
		rooms.add(new Room("Library"));
		rooms.add(new Room("Study"));
		rooms.add(new Room("Hall"));
		rooms.add(new Room("Lounge"));
		rooms.add(new Room("Dining Room"));
		rooms.add(new Room("Kitchen"));
		rooms.add(new Room("Ball Room"));
		Collections.shuffle(rooms);

		weapons.add(new Weapons("Candlestick"));
		weapons.add(new Weapons("Dagger"));
		weapons.add(new Weapons("Lead Pipe"));
		weapons.add(new Weapons("Revolver"));
		weapons.add(new Weapons("Rope"));
		weapons.add(new Weapons("Spanner"));
		Collections.shuffle(weapons);

	}

	/**
	 * Creates a solution / murderer cards
	 * 
	 */
	private void setupSolution(){

		this.solution[0] = this.characters.remove(0);
		this.solution[1] = this.weapons.remove(0);
		this.solution[2] = this.rooms.remove(0);
	}

	/**
	 * Gets Solution
	 */
	public Card[] getSolution(){
		return solution;
	}

	/**
	 * Draws Board
	 * @param players
	 */
	public void drawBoard(List<Player> players) {
		board.draw(players);
	}

	/**
	 * Gets Board
	 * @return
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * Gets players
	 * @return
	 */
	public List<Player> getPlayers(){
		return this.players;
	}
	

	/**
	 * Sets players
	 * @param players
	 */
	public void setPlayers(List<Player> players){
		this.players = players;
	}

	/*
	 * Prints Solution
	 */
	public void Solution(){
		System.out.println("Character : " + solution[0].getName() + "\nWeapon: " + solution[1].getName() + "\nRoom: " + solution[2].getName());
	}

	/**
	 * Deals cards to all participating players 
	 */
	public void dealCards() {
		// adds all players 
		for(Player p : players) {
			dealCard.add(p);
		}
		// while cards available keep dealing
		while(!characters.isEmpty() && !weapons.isEmpty() && !rooms.isEmpty()){
			Player p = dealCard.remove(0);
			p.addCard(characters.remove(0));
			p.addCard(weapons.remove(0));
			p.addCard(rooms.remove(0));
			dealCard.add(p); 
		}
	}


	/**
	 * Sets up initial column position for characters
	 * @param name
	 * @return
	 */
	public static int initCol(String name){
		switch(name){
		case "Professor Plum" : return 23;
		case "Colonel Mustard" : return 0;
		case "Mrs. Peacock" : return 23;
		case "Mr. Green" : return 14;
		case "Miss Scarlett" : return 7;
		case "Mrs White" : return 9;
		default: return -1;
		}
	}

	/**
	 * Sets up initial row position for characters
	 * @param name
	 * @return
	 */
	public static int initRow(String name){
		switch(name){
		case "Professor Plum" : return 19;
		case "Colonel Mustard" : return 17;
		case "Mrs. Peacock" : return 6;
		case "Mr. Green" : return 0;
		case "Miss Scarlett" : return 24;
		case "Mrs White" : return 0;
		default: return -1;
		}
	}


	/**
	 * checks accusation with players accusation
	 * 
	 * @param cards
	 * @return
	 */
	public boolean checkAccusation(String[] cards){
		for (int i = 0; i < solution.length; i++){
			if (!cards[i].equals(solution[i].getName())){
				return false;
			}
		}
		return true;
	}




}
