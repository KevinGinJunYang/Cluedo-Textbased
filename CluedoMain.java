import java.util.ArrayList;
import java.util.List;


public class CluedoMain {
	private static boolean gameOver = false;
	private static ArrayList<String> characters = new ArrayList<>();
	private static ArrayList<String> allCharacters = new ArrayList<>();
	private static ArrayList<String> weapons = new ArrayList<>();
	private static ArrayList<String> rooms = new ArrayList<>();

	/**
	 *Adds Cards to game
	 *
	 */
	public CluedoMain() {
		//adds available characters to play
		characters.add("Miss Scarlett");
		characters.add("Colonel Mustard");
		characters.add("Mrs White");
		characters.add("Mr. Green");
		characters.add("Mrs. Peacock");
		characters.add("Professor Plum");
		//adds all characters to pick from
		allCharacters.add("Miss Scarlett");
		allCharacters.add("Colonel Mustard");
		allCharacters.add("Mrs White");
		allCharacters.add("Mr. Green");
		allCharacters.add("Mrs. Peacock");
		allCharacters.add("Professor Plum");
		// adds all weapons
		weapons.add("Candlestick");
		weapons.add("Dagger");
		weapons.add("Lead pipe");
		weapons.add("Revolver");
		weapons.add("Rope");
		weapons.add("Spanner");
		// adds all rooms
		rooms.add("Conservatory");
		rooms.add("Billiard Room");
		rooms.add("Library");
		rooms.add("Study");
		rooms.add("Hall");
		rooms.add("Lounge");
		rooms.add("Dining Room");
		rooms.add("Kitchen");
		rooms.add("Ball Room");
	}

	/**
	 * Gets players and their characters
	 * @param count
	 * @return
	 */
	private static List<Player> getPlayers(int count) {
		List<Player> players = new ArrayList<Player>();
		System.out.println("Choose a character to play:");
		Output.getList(characters);
		for (int i = 1; i <= count; i++) {
			//gets  valid character for each player
			String chosenCharacter = Output.getString("Player " + i + " character?");
			while (!characters.contains(chosenCharacter)) {
				chosenCharacter = Output.getString("Invalid character, please pick again\n ");
			}
			characters.remove(chosenCharacter);
			//https://stackoverflow.com/questions/17984975/convert-int-to-char-in-java
			char num = Character.forDigit(i, 10); // generates number as its character to print
			players.add(new Player(chosenCharacter, num)); // adds players playing
		}
		return players;
	}

	/**
	 * Gives user the game choices available to them given their circumstances
	 * @param player
	 * @param avaliablePlayers
	 * @param setup
	 * @param roll
	 */
	private static void gameChoices(Player player, List<Player> avaliablePlayers, Setup setup, int roll) {
		if(!gameOver) { // checks game is not over
			if(avaliablePlayers.contains(player)){ // keeps playing while its this player until end turn
				while(true){
					for(int i=roll; i>=0; i--){
						Tile tile = setup.getBoard().position(player.getRow(), player.getCol()); // gets current position
						List<String> choices = new ArrayList<String>();
						setup.drawBoard(avaliablePlayers); // draws board with players
						Board board = setup.getBoard();
						//Set<Tile> visited = new HashSet<Tile>();
						//visted.add(tile);
						System.out.println();
						System.out.println(i +" moves left");
						System.out.println("Choices:");
						// checks if it can move in that direction given a wall found
						if(i != 0) { // only shows these options when there are moves left
							if(player.checkLeftWall(board)){
								System.out.println("L | Move left");
								choices.add("L");
							}
							if(player.checkRightWall(board)){
								System.out.println("R | Move right");
								choices.add("R");
							}
							if(player.checkTopWall(board)){
								System.out.println("U | Move up");
								choices.add("U");
							}
							if(player.checkBotWall(board)){
								System.out.println("D | Move down");
								choices.add("D");
							}
						}


						System.out.println("H | View hand");
						choices.add("H");
						System.out.println("C | View evidence");
						choices.add("C");
						System.out.println("A | Make accusation");
						choices.add("A");
						if(tile.getRoom() != null){ // only shows this option when they are in a room
							System.out.println("S | Make suggestion");
							choices.add("S");
						}
						if(i == 0) { // only shows this option when 0 moves left
							System.out.println("E | End turn");
							choices.add("E");
						}
						String choice = Output.getString("Choice : ");
						while(!choices.contains(choice)){
							choice = Output.getString("Invalid choice, try again.");
						}


						switch(choice){
						case ("R"):
							player.moveRight();
						break;
						case ("D"):
							player.moveDown();
						break;
						case ("U"):
							player.moveUp();
						break;
						case ("L"):
							player.moveLeft();
						break;
						case ("H"):
							showHand(player);
						i++;
						break; // show hand and evidence dont take moves
						case ("C"):
							showEvidence(player);
						i++;
						break;
						case ("S"):
							makeSuggestion(player, setup);
						return; // ends players turn for suggest and accusation
						case ("A"):
							makeAccusation(player, avaliablePlayers, setup);
						return;
						case ("E"):
							return;
						}
					}
				}
			}


		}

	}

	/**
	 * Makes accusation from player selecting from characters, weapons and room type
	 *
	 * @param player
	 * @param currentPlayers
	 * @param setup
	 */
	private static void makeAccusation(Player player, List<Player> currentPlayers,Setup setup) {
		String[] accusation = new String[3];
		System.out.println("Make your accusation");
		//gets users accusation
		accusation[0] = selectCharacter();
		accusation[1] = selectWeapon();
		accusation[2] = selectRoom();
		// checks users accusation against solution in initial setup
		if (setup.checkAccusation(accusation)){
			System.out.println("You are correct!");
			setup.Solution();
			gameOver = true;
		} else {
			System.out.println("Wrong! you have failed");
			System.out.println(player.getName() +" cannot make anymore accusations and is out");
			currentPlayers.remove(player);
		}
	}



	/**
	 * Makes Suggestion from player to check other players hands for cards
	 * @param player
	 * @param setup
	 */
	private static void makeSuggestion(Player player, Setup setup) {
		String room = setup.getBoard().position(player.getRow(), player.getCol()).getRoom();
		System.out.println("Your current hand : ");
		showHand(player);
		System.out.println("Your evidence so far : ");
		showEvidence(player);
		System.out.println("You're in the " + room);
		System.out.println();
		System.out.println("Suggest a character:");
		String character = selectCharacter();
		System.out.println("Suggest a weapon:");
		String weapon = selectWeapon();

		for (Player other : setup.getPlayers()){
			while(other != player){
				// checks other players hands for suggested cards
				for (Card c : other.getHand()){
					String card = c.getName();
					if (card.equals(character) && !player.hasEvidence(c)){
						System.out.println(other.getName() + " has card: " + card);
						player.addEvidence(c);
						return;
					}
					if(card.equals(weapon) && !player.hasEvidence(c)) {
						System.out.println(other.getName() + " has card: " + card);
						player.addEvidence(c);
						return;
					}
					if(card.equals(room) && !player.hasEvidence(c)) {
						System.out.println(other.getName() + " has card: " + card);
						player.addEvidence(c);
						return;
					}


				}

			}
		}
		System.out.println("Card was not found");
	}



	/**
	 * Shows the players hand
	 *
	 * @param player
	 */
	private static void showHand(Player player) {
		List<String> hand = new ArrayList<String>();
		for(Card c : player.getHand()){
			hand.add(c.getName());
		}
		Output.getList(hand);

	}
	/**
	 * Shows all evidence found for player
	 *
	 * @param player
	 */
	private static void showEvidence(Player player) {
		List<String> evidence = new ArrayList<String>();
		for(Card c : player.getEvidence()){
			evidence.add(c.getName());
		}
		Output.getList(evidence);

	}

	/**
	 * Selects the character from user input
	 *
	 * @return
	 */
	private static String selectCharacter(){
		Output.getList(allCharacters);
		String character = Output.getString("Enter the character's name: ");
		while (!allCharacters.contains(character)){
			character = Output.getString("Invalid Character, try again: ");
		}
		return character;
	}

	/**
	 * Selects the weapon of accusation from user input
	 *
	 * @return
	 */
	private static String selectWeapon(){
		Output.getList(weapons);
		String weapon = Output.getString("Enter the weapon name: ");
		while (!weapons.contains(weapon)){
			weapon = Output.getString("Invalid Weapon, try again: ");
		}
		return weapon;
	}

	/**
	 * Selects the room of accusation from user input
	 *
	 * @return
	 */
	private static String selectRoom(){
		Output.getList(rooms);
		String room = Output.getString("Enter the room name : ");
		while (!rooms.contains(room)){
			room = Output.getString("Invalid Room, try again: ");
		}
		return room;

	}

	/**
	 * Returns a dice roll value
	 * @return
	 */
	public static int rollDice() {
		int roll = (int) (Math.random() * 10 + 2);
		return roll;
	}

	/**
	 * Players failed to find murderer
	 *
	 * @param setup
	 */
	private static void failure(Setup setup) {
		System.out.println("You failed to find the murderer\n");
		System.out.println("Solution : ");
		setup.Solution();
		gameOver = true;
		Output.gameOver();
	}


	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String args[]) {
		//load game
		new CluedoMain();
		Setup setup = new Setup();
		System.out.println("Welcome to Cludeo");
		System.out.println("NOTE : THIS GAME IS CASE SENSITIVE");
		//gets input
		int playerCount = Output.getPlayerCount("Enter amount of players (3-6): ");
		while(playerCount > 6 || playerCount < 3){
			playerCount = Output.getPlayerCount("Error found, Enter a number from 3 - 6 :");
		}
		//choose characters
		List<Player> players = getPlayers(playerCount);
		setup.setPlayers(players);
		//gives cards
		setup.dealCards();
		//take turns
		while (!gameOver && players.size() > 0) {
			//gets current player
			Player player = players.get(0);
			System.out.println(player.getName() + " it is your turn ");
			//rolls dice
			int count = rollDice();
			System.out.println(player.getName() + " rolled a " + count);
			//choose choices
			gameChoices(player, players, setup, count);
			//gets next player
			if(players.contains(player)){
				players.remove(player);
				players.add(player);
			}
		}
		// no more players ends game
		if(players.size() == 0){
			failure(setup);
		}

	}

}
