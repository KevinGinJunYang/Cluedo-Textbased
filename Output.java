import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Output {

	/**
	 * Gets the user input as a string
	 * @param s
	 * @return
	 */
	public static String getString(String s) {
		System.out.print(s + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				return input.readLine();
			} catch (IOException e) {
				System.out.println("Error found, Enter a String : ");
			}
		}
	}
	/**
	 * Gets the user input as a integer
	 * @param s
	 * @return
	 */
	public static int getPlayerCount(String s) {
		System.out.print(s + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				return Integer.parseInt(input.readLine());
			} catch (IOException | NumberFormatException e) {
				System.out.println("Error found, Enter a number : ");
			}

		}
	}

	/**
	 * Prints GameOver
	 */
	public static void gameOver(){
		System.out.println("GAME OVER");

	}

	/**
	 * Prints a list
	 * @param list
	 */
	public static void getList(List<String> list){
		for (String s : list) {
			System.out.print("| " + s + "  | ");
		}
		System.out.println();
		System.out.println();
	}


}
