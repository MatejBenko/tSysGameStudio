package sk.tsystems.gamestudio.game.guessnumber.consoleui;

import java.util.Random;
import java.util.Scanner;

public class ConsoleUI {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void play() {
		Random rand;
		rand = new Random();
		int max = 20;
		int scan = 0;
		String t = "Try again";
		char scan2 = ' ';
		
		do {
			int hadaneCislo = rand.nextInt(max) + 1;
			System.out.println("Guess number from 1 to " + max + ": ");
			System.out.print("Please enter your selection <X> EXIT or some NUMBER :");
			do {
				try {
					String scanf = scanner.nextLine().toUpperCase();
					if(scanf.equals("X"))
						return;
					scan = Integer.parseInt(scanf);
				} catch (NumberFormatException e) {
					System.err.println("Invalid tile number!");
					System.err.println(t);
					continue;
				}
				if (hadaneCislo < scan) {
					System.out.println("Number is lower");
					System.err.println(t);
				} else if (hadaneCislo > scan) {
					System.out.println("Number is higher");
					System.err.println(t);
				} else if (hadaneCislo == scan) {
					System.err.println("WIN !!!");
					System.out.println("------------------------------");
					System.out.println();
				}
			} while (scan != hadaneCislo);
			do {
				System.out.println("Play again?");
				System.out.println("Y or N");
				System.out.println();
				try {
					scan2 = scanner.nextLine().toUpperCase().charAt(0);
				} catch (Exception e) {
					System.err.println("Invalid input!");
					System.out.println(scan2);
					System.err.println(t);
					e.printStackTrace();
				}
			} while (!(scan2 == 'Y' || scan2 == 'N'));
		} while (scan2 != 'N');
		System.err.println("Thanks for playing :) ");

	}
	private void processInput() {
		
		
	}
}
