import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;



public class Board {
	Tile tile = null;
	public static int row = 25;
	public static int col = 24;

	private Tile[][] board = new Tile[row][col]; // create new board with dimensions

	/**
	 * Loads the text file board layout
	 *
	 */
	public Board(){
		try{
			//https://stackoverflow.com/questions/16953897/how-to-read-a-text-file-inside-a-jar
			BufferedReader read = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/board/boardFile.txt")));
			for(int r = 0; r<board.length; r++){
				String reader = read.readLine();
				for(int i=0; i < board[0].length; i++){
					char type = reader.charAt(i);
					Tile sq = setType(type);
					board[r][i] = sq;
				}
			}
			read.close();


		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	



	/**
	 * Gets the current Position of the player / tile
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public Tile position(int row, int col){
		try {
			return board[row][col];
		}catch(ArrayIndexOutOfBoundsException e) {

		}
		return tile;

	}

	/**
	 * Draws the board
	 *
	 * @param players
	 */
	public void draw(List<Player> players){
		//creates temporary board to move character without duplicates
		Tile[][] temp = new Tile[row][col];
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				temp[i][j] = board[i][j];
			}
		}
		// gets player character
		for (Player p : players){
			temp[p.getRow()][p.getCol()] = new Tile(false, null, p.getID());

		}
		//draws board characters
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				Tile sq = temp[i][j];
				System.out.print(sq.drawTile());
				System.out.print(" ");
			}

			System.out.println();
		}
	}
	
	/**
	 * Gets Type in file scanned and changes and creates new Tile
	 *
	 * @param type
	 * @return
	 */
	private Tile setType(char type) {

		switch(type){
		// sets file characters to new characters
		case 'X' : tile = new Tile(false, null, '+');
		break;
		case '_' : tile = new Tile(true, null, ' ');
		break;
		case '*' : tile = new Tile(true, null, '@');
		break;
		case 'K' : tile = new Tile(false, "Kitchen", 'K');
		break;
		case 'B' : tile = new Tile(false, "Ball Room", 'B');
		break;
		case 'C' : tile = new Tile(false, "Conservatory", 'C');
		break;
		case 'I' : tile = new Tile(false, "Billiard Room", 'I');
		break;
		case 'L' : tile = new Tile(false, "Library", 'L');
		break;
		case 'S' : tile = new Tile(false, "Study", 'S');
		break;
		case 'H' : tile = new Tile(false, "Hall", 'H');
		break;
		case 'O' : tile = new Tile(false, "Lounge", 'O');
		break;
		case 'D' : tile = new Tile(false, "Dining Room", 'D');
		break;
		case 'k' : tile = new Tile(true, "Kitchen", '/');
		break;
		case 'b' : tile = new Tile(true, "Ball Room", '/');
		break;
		case 'c' : tile = new Tile(true, "Conservatory", '/');
		break;
		case 'i' : tile = new Tile(true, "Billiard Room", '/');
		break;
		case 'l' : tile = new Tile(true, "Library", '/');
		break;
		case 's' : tile = new Tile(true, "Study", '/');
		break;
		case 'h' : tile = new Tile(true, "Hall", '/');
		break;
		case 'o' : tile = new Tile(true, "Lounge", '/');
		break;
		case 'd' : tile = new Tile(true, "Dining Room", '/');
		break;
		}
		return tile;
	}




}
