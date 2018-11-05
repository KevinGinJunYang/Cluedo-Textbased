import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name; 
	private char id; 
	private int row, col; 
	List<Card> hand = new ArrayList<Card>();
	List<Card> evidence = new ArrayList<Card>();

	/**
	 * Creates Players hand and position movements 
	 * @param name
	 * @param id
	 */
	public Player(String name, char id) {
		super();
		this.name = name;
		this.id = id;
		this.row = Setup.initRow(name);
		this.col = Setup.initCol(name);
	}


	/**
	 * Returns col
	 * @return
	 */
	public int getCol(){
		return col;
	}

	/**
	 * Returns row
	 * @return
	 */
	public int getRow(){
		return row;
	}

	/**
	 * Returns id
	 * @return
	 */
	public char getID(){
		return id;
	}

	/**
	 * Returns name
	 * @return
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns hand
	 * @return
	 */
	public List<Card> getHand(){
		return hand;
	}

	public List<Card> getEvidence(){
		return evidence;
	}

	/**
	 * Adds card to hand
	 * @param c
	 */
	public void addCard(Card c){
		hand.add(c);
		evidence.add(c);
	}

	/**
	 * adds to total evidence found 
	 * @param c
	 */
	public void addEvidence(Card c){
		evidence.add(c);
	}

	/**
	 * Returns current evidence found 
	 * @param c
	 * @return
	 */
	public boolean hasEvidence(Card c){
		return evidence.contains(c);
	}

	/**
	 * Checks if there is a left wall given current Position
	 * @param board
	 * @param game
	 * @return
	 */
	public boolean checkLeftWall(Board board){
		Tile leftWall = board.position(row, col-1);
		return leftWall.blocked();
	}

	/**
	 * Checks if there is a top wall given current Position
	 * @param board
	 * @param game
	 * @return
	 */
	public boolean checkTopWall(Board board){
		Tile topWall = board.position(row-1, col);
		return topWall.blocked();
	}

	/**
	 * Checks if there is a right wall given current Position
	 * @param board
	 * @param game
	 * @return
	 */
	public boolean checkRightWall(Board board){
		Tile rightWall = board.position(row, col+1);
		return rightWall.blocked();
	}

	/**
	 * Checks if there is a top wall given current Position
	 * @param board
	 * @param game
	 * @return
	 */
	public boolean checkBotWall(Board board){
		Tile botWall = board.position(row+1, col);
		return botWall.blocked();
	}


	/**
	 * Moves player Left
	 */
	public void moveLeft() {
		this.col -= 1;

	}

	/**
	 * Moves player right
	 */
	public void moveRight() {
		this.col += 1;

	}

	/**
	 * Moves player up
	 */
	public void moveUp() {
		this.row -= 1;

	}

	/**
	 * Moves player down
	 */
	public void moveDown() {
		this.row += 1;

	}



}
