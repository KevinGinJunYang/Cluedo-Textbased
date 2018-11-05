import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tests {

	@Test
	public void testAddPlayer() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mr. Green", '0'));
		setup.setPlayers(players);
		assertTrue(setup.getPlayers().equals(players));
	}
	
	@Test
	public void testRemovePlayer() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mr. Green", '0'));
		players.add(new Player("Mr. Greenn", '1'));
		setup.setPlayers(players);
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(0);
			players.remove(p);
		}
		assertTrue(setup.getPlayers().size() == 1);
	}
	
	@Test
	public void testroom1() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(4, 4);
		assertTrue(t.getRoom() == "Kitchen");
	}
	
	@Test
	public void testroom2() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(6, 13);
		assertTrue(t.getRoom() == "Ball Room");
	}
	
	@Test
	public void testroom3() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(4, 20);
		assertTrue(t.getRoom() == "Conservatory");
	}
	
	@Test
	public void testroom4() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(9, 20);
		assertTrue(t.getRoom() == "Billiard Room");
	}
	
	@Test
	public void testroom5() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(16, 20);
		assertTrue(t.getRoom() == "Library");
	}
	
	@Test
	public void testroom7() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(20, 20);
		assertTrue(t.getRoom() == "Study");
	}
	
	@Test
	public void testroom8() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(20, 13);
		assertTrue(t.getRoom() == "Hall");
	}
	
	@Test
	public void testroom9() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(20, 3);
		assertTrue(t.getRoom() == "Lounge");
	}
	
	@Test
	public void testroom10() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(15, 3);
		assertTrue(t.getRoom() == "Dining Room");
	}
	
	@Test
	public void testMoveup() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player player = setup.getPlayers().get(0);
		player.moveUp();
		assertTrue(player.getRow() == 23);
	}
	
	@Test
	public void testMoveRight() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player player = setup.getPlayers().get(0);
		player.moveRight();
		assertTrue(player.getCol() == 8);
	}
	
	@Test
	public void testMoveDown() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player player = setup.getPlayers().get(0);
		player.moveDown();
		assertTrue(player.getRow() == 25);
	}
	
	@Test
	public void testMoveLeft() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player player = setup.getPlayers().get(0);
		player.moveLeft();
		assertTrue(player.getCol() == 6);
	}
	
	@Test
	public void testCheckRightWall() {	
		Setup setup = new Setup();
		Board board = setup.getBoard();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player p = setup.getPlayers().get(0);
		assertFalse(p.checkRightWall(board));
	}
	
	@Test
	public void testCheckLeftWall() {	
		Setup setup = new Setup();
		Board board = setup.getBoard();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Miss Scarlett", '0'));
		setup.setPlayers(players);
		Player p = setup.getPlayers().get(0);
		assertFalse(p.checkLeftWall(board));
	}
	
	@Test
	public void testCheckTopWall() {	
		Setup setup = new Setup();
		Board board = setup.getBoard();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mrs. Peacock", '0'));
		setup.setPlayers(players);
		Player p = setup.getPlayers().get(0);
		assertFalse(p.checkTopWall(board));
	}
	
	@Test
	public void testCheckBotWall() {	
		Setup setup = new Setup();
		Board board = setup.getBoard();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mrs. Peacock", '0'));
		setup.setPlayers(players);
		Player p = setup.getPlayers().get(0);
		assertFalse(p.checkBotWall(board));
	}
	
	
	@Test
	public void testNotInRoom() {
		Setup setup = new Setup();
		Tile t = setup.getBoard().position(22, 7);
		assertTrue(t.getRoom() == null);
	}
	
	@Test
	public void testDice() {
		for (int i = 0; i < 10000; i++) {
			int roll = CluedoMain.rollDice();
			assertFalse(roll > 12);
			assertFalse(roll < 2);
		}

	}
	
	@Test
	public void testGameOver() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mr. Green", '0'));
		setup.setPlayers(players);
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(0);
			players.remove(p);
		}
		assertTrue(players.size() == 0);

	}
	
	@Test
	public void testDealCards() {
		Setup setup = new Setup();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Mr. Green", '0'));
		setup.setPlayers(players);
		setup.dealCards();
		Player p = setup.getPlayers().get(0);
		assertTrue(!p.getHand().isEmpty());

	}
	
	@Test
	public void testSolution() {
		List<Card> testCards = new ArrayList<>();
		testCards.add(new Characters("Miss Scarlett"));
		testCards.add(new Room("Conservatory"));
		testCards.add(new Weapons("Candlestick"));
		Card[] solution = new Card[3];
		solution[0] = testCards.get(0);
		solution[1] = testCards.get(1);
		solution[2] = testCards.get(2);
		assertTrue(solution[0].getName() == "Miss Scarlett");
		assertTrue(solution[1].getName() == "Conservatory");
		assertTrue(solution[2].getName() == "Candlestick");


	}
	
	
}
