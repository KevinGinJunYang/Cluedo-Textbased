public class Tile {
	
	protected boolean blocked; 
	private String room;
	private char drawTile; 
	
	/**
	 * The Tile of the gameboard 
	 * 
	 * @param blocked
	 * @param room
	 * @param drawTile
	 */
	public Tile(boolean blocked, String room, char drawTile){
		this.blocked = blocked;
		this.room = room;
		this.drawTile = drawTile;
	}
	
	/*
	 * Gets room of Tile
	 */
	public String getRoom(){
		return room;
	}
	
	/*
	 * Checks if Tile is blocked / cannot be moved on 
	 */
	public boolean blocked(){
		return blocked;
	}
	
	/*
	 * Draws the characters of the Tile
	 */
	public char drawTile() {
		return drawTile;
	}

	
	}
