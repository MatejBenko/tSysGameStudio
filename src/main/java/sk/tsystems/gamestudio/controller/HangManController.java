package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.hangman.HangmanGameSession;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class HangManController {

	private String input;
	private HangmanGameSession hgs;
	private boolean isWritten = false;

	@Autowired
	private ScoreService scoreService;

	@RequestMapping("/hangman")
	public String getNumber(String input) {

		if (hgs == null) {
			hgs = new HangmanGameSession();
		}
		if (hgs.isSolved() && !isWritten) {
			isWritten = true;
			scoreService.addScore(new Score("Stevo", "hangman", 5));
		}
		this.input = input;
		return "hangman";
	}

	@RequestMapping("/hangman/new")
	public String getNewNumber() {
		hgs = new HangmanGameSession();
		return "hangman";
	}

	public String getMessage() {
		if (hgs.isSolved()) {
			return "You Win!!" + hgs.getName();
		}
		if (input != null && input.length() == 1) {
			return hgs.guess(input.charAt(0));
		}
		return "wrong input";
	}

	public String getHint() {
		return "Hint " + hgs.getName();
	}

}
