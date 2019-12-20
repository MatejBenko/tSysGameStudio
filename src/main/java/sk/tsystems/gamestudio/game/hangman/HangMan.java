package sk.tsystems.gamestudio.game.hangman;

import java.util.Random;
import java.util.Scanner;

public class HangMan {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random rand;
		rand = new Random();
		String[] namesSK = { "Adam", "Adolf", "Adrian", "Erik" };
		int health = 5;
		int r = rand.nextInt(namesSK.length);
		String name = namesSK[2];

		for (int j = 0; j < name.length(); j++) {
				System.out.print("-");
			}
		System.out.println();
		do {
			String scan = scanner.nextLine();
			// System.out.println(name);
			for (int j = 0; j < name.length(); j++) {
				if (name.charAt(j) == scan.toUpperCase().charAt(0) || name.charAt(j) == scan.toLowerCase().charAt(0)) {
					System.out.print(name.charAt(j));
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		} while (true);
	}

}
