package sk.tsystems.gamestudio;

import java.util.Scanner;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.npuzzle.Puzzle;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class Main {

//	public static void main(String[] args) {
//		menu();
//		ScoreService scoreService = new ScoreServiceJDBC();
//
//		for (Score score : scoreService.getTopScores("npuzzle"))
//			System.out.println(score.getUsername() + "    \t" + score.getValue());
//	}
//
//	public static void menu() {
//		Scanner scanner = new Scanner(System.in);
//		int choice;
//		do {			
//			showMenu();
//			System.out.println();
//			choice = Integer.parseInt(scanner.nextLine());
//			switch (choice) {
//			case 1:
//				System.out.println("1: Minesweeper");
//				new Minesweeper();
//				break;
//			case 2:
//				System.out.println("2: Puzzle");
//				new Puzzle();
//				break;
//			case 3:
//				System.out.println("3: GuessNumber");
//				new ConsoleUI();
//				break;
//			case 4:
//				System.out.println("4: game");
//				break;
//			case 0:
//				System.exit(0);
//			default:
//				System.err.println("Wrong input");
//			}
//		} while (choice != 0);
//	}
//
//	private static void showMenu() {
//		System.out.println("____________________________");
//		System.out.println("MENU:");
//		System.out.println("Command Options: ");
//		System.out.println("1: Minesweeper");
//		System.out.println("2: Puzzle");
//		System.out.println("3: GuessNumber");
//		System.out.println("4: Obesenec");
//		System.out.println("0: Quit");
//		System.out.println("____________________________");
//	}
}
