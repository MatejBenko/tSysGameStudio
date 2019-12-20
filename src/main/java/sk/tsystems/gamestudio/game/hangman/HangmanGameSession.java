package sk.tsystems.gamestudio.game.hangman;

import java.util.Random;

public class HangmanGameSession {
	private static String[] namesSK = { "Adam", "Adolf", "Adrián", "Alan", "Albert", "Albín", "Aleš", "Alexander",
			"Alexej", "Alfonz", "Alfréd", "Alojz", "Ambróz", "Andrej", "Anton", "Arnold", "Arpád", "Augustín", "Aurel",
			"Bartolomej", "Belo", "Beňadik", "Benjamín", "Bernard", "Blahoslav", "Blažej", "Bohdan", "Bohumil",
			"Bohumír", "Bohuš", "Bohuslav", "Boleslav", "Bonifác", "Boris", "Branislav", "Bruno", "Bystrík", "Ctibor",
			"Cyprián", "Cyril", "Dalibor", "Daniel", "Dávid", "Demeter", "Denis", "Dezider", "Dionýz", "Dobroslav",
			"Dominik", "Drahomír", "Drahoslav", "Dušan", "Edmund", "Eduard", "Eliáš", "Emanuel", "Emil", "Erik",
			"Ernest", "Ervín", "Eugen", "Fedor", "Félix", "Ferdinand", "Filip", "Florián", "František", "Frederik",
			"Fridrich", "Gabriel", "Gašpar", "Gejza", "Gregor", "Gustáv", "Henrich", "Hubert", "Hugo", "Ignác", "Igor",
			"Iľja", "Imrich", "Ivan", "Izidor", "Jakub", "Ján", "Jarolím", "Jaromír", "Jaroslav", "Jerguš", "Jozef",
			"Július", "Juraj", "Kamil", "Karol", "Kazimír", "Klaudius", "Klement", "Koloman", "Konštantín", "Kornel",
			"Kristián", "Krištof", "Ladislav", "Leonard", "Leopold", "Levoslav", "Ľubomír", "Ľubor", "Ľuboš",
			"Ľuboslav", "Ľudomil", "Ľudovít", "Lukáš", "Marcel", "Marek", "Marián", "Mário", "Martin", "Matej", "Matúš",
			"Maximilián", "Medard", "Metod", "Michal", "Mikuláš", "Milan", "Miloš", "Miloslav", "Miroslav", "Mojmír",
			"Móric", "Nikolaj", "Norbert", "Oldrich", "Oleg", "Oliver", "Ondrej", "Oskár", "Oto", "Pankrác", "Patrik",
			"Pavol", "Peter", "Pravoslav", "Prokop", "Radomír", "Radoslav", "Radovan", "Radúz", "Rastislav", "René",
			"Richard", "Róbert", "Roland", "Roman", "Rudolf", "Samuel", "Sergej", "Servác", "Severín", "Silvester",
			"Šimon", "Slavomír", "Stanislav", "Štefan", "Svätopluk", "Svetozár", "Tadeáš", "Teodor", "Tibor",
			"Tichomír", "Timotej", "Tomáš", "Urban", "Václav", "Valentín", "Valér", "Vasil", "Vavrinec", "Vendelín",
			"Viktor", "Viliam", "Vincent", "Vít", "Víťazoslav", "Vladimír", "Vladislav", "Vlastimil", "Vojtech",
			"Vratislav", "Vratko", "Zdenko", "Žigmund", "Zlatko", "Zoltán" };
	private Random rand;
	private char[] arrayGuess;
	private char[] arrayName;
	
	public HangmanGameSession() {
		rand = new Random();
		genereteName();
	}

	public String guess(char c) {
		for (int i = 0; i < arrayGuess.length; i++) {
			if (arrayName[i] == c) {
				arrayGuess[i] = c;
			}
		}
		return new String(arrayGuess);
	}

	public void genereteName() {
		String hadanyString = namesSK[rand.nextInt(namesSK.length)];
		arrayGuess = new char[hadanyString.length()];
		arrayName = new char[hadanyString.length()];
		for (int j = 0; j < hadanyString.length(); j++) {
			arrayName[j] = hadanyString.charAt(j);
		}
		for (int j = 0; j < hadanyString.length(); j++) {
			arrayGuess[j] = '-';
		}
	}

	public boolean isSolved() {
		for (int i = 0; i < arrayGuess.length; i++) {
			if (arrayGuess[i] == '-') {
				return false;
			}
		}
		return true;
	}
	public String getName() {
		return new String(arrayName);
	}
	public String getGuessName() {
		return new String(arrayGuess);
	}
}
