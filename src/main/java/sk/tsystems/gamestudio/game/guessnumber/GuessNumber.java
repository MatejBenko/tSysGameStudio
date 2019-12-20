package sk.tsystems.gamestudio.game.guessnumber;
import sk.tsystems.gamestudio.game.guessnumber.consoleui.ConsoleUI;

public class GuessNumber {
	
	public GuessNumber() {
	
	ConsoleUI ui = new ConsoleUI();
	ui.play();
	
	}

	public static void main(String[] args) {
		new GuessNumber();
	}
}