package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.guessnumber.consoleui.ConsoleUI;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {

	private Random rand = new Random();
	private int hadaneCislo = rand.nextInt(20) + 1;
	private String input;

	@RequestMapping("/guess")
	public String getNumber(String input) {
		this.input = input;
		return "guess";
	}

	public String getMessage() {
		int scan;
		try {
			scan = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return "Invalid tile number!";
		}
		if (hadaneCislo < scan) {
			return "Guess Number is lower";
		} else if (hadaneCislo > scan) {
			return "Guess Number is higher";
		} else {
			return "WIN !!!";
		}
	}
}
