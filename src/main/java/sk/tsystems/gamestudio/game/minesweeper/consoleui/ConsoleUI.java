package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.game.minesweeper.UserInterface;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {

			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		update();
		do {
			if (processInput())
				return;
			update();
//			throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
		} while (field.isSolved());

//		} while (!(field.getState() == GameState.FAILED || field.getState() == GameState.SOLVED));
//		if (field.getState() == GameState.FAILED) {
//			System.err.println("LOSE !");
//		} else if (field.getState() == GameState.SOLVED) {
//			System.err.println("WIN !");
//		}

	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		System.out.println(field.getState());
		System.out.print("  ");
		for (int i = 0; i < field.getColumnCount(); i++) {
			if (i < 10) {
				System.out.print(i);
				System.out.print("  ");
			} else {
				System.out.print(i);
				System.out.print(" ");
			}
		}
		System.out.println();
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.printf("%c ", 'A' + row);
			for (int col = 0; col < field.getColumnCount(); col++) {
				Tile tile = field.getTile(row, col);
				if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Mine) {
						System.out.printf("X  ");
					} else if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
						System.out.print("  ");
					}
				} else if (tile.getState() == Tile.State.MARKED) {
					System.out.printf("M  ");
				} else if (tile.getState() == Tile.State.CLOSED) {
					System.out.printf("-  ");
				}
			}
			System.out.println();
		}

//        throw new UnsupportedOperationException("Method update not yet implemented");
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private boolean processInput() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your selection <X> EXIT, <MA1>, <OA1> OPEN :");
		try {
			Pattern pattern = Pattern.compile("(O|M])([A-I])([0-8])");
			String scan = scanner.nextLine().toUpperCase();
			if (scan.equals("X")) {
				return true;
			}
			Matcher matcher = pattern.matcher(scan.toUpperCase());
			if (matcher.matches()) {
				String markName = matcher.group(1);
				String rowName = matcher.group(2);
				String colName = matcher.group(3);
			}
			char mark = (matcher.group(1)).charAt(0);
			int readX = (matcher.group(2)).charAt(0) - 'A' + 1;
			int readY = Integer.parseInt(matcher.group(3));
			if (mark == 'M') {
				field.markTile(readX - 1, readY - 0);
			} else if (mark == 'O') {
				field.openTile(readX - 1, readY - 0);
			}
		} catch (Exception e) {
			System.err.println("Wrong input !!! \nTry again");
			e.printStackTrace();
		}

		return false;
		// throw new UnsupportedOperationException("Method processInput not yet
		// implemented");
	}

	void handleInput(String input) throws WrongFormatException {
		try {

		} catch (Exception e) {

		}
	}
}
