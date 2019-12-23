package sk.tsystems.gamestudio.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	private Player loggedPlayer;

	@RequestMapping("/login")
	public String login(Player player) {
		if ("heslo".equals(player.getPass())) {// database connect
			loggedPlayer = player;
		}
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "redirect:/";
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

	@Autowired
	private ScoreService scoreService;

	public String getScoreForGame(String arg) {
//		System.out.println(arg);
		List<Score> tmp = scoreService.getTopScores(arg);
		StringBuilder sb = new StringBuilder();
		for (Score s : tmp) {
			sb.append("user: " + s.getUsername() + " with score: " + s.getScore() + "<br>");
		}
		return sb.toString();
	}

	@Autowired
	private sk.tsystems.gamestudio.service.CommentService commentService;

	public String getCommentsForGame(String game) {

		List<Comment> tmp = commentService.getComments(game);
		Collections.reverse(tmp);
		StringBuilder sb = new StringBuilder();
		for (Comment c : tmp) {
			sb.append(c.getUsername() + " >> " + c.getContent() + "<br>");
		}
		return sb.toString();
	}

	@RequestMapping("/comment")
	public String addComment(String game, String comment) {
		System.out.println(game + comment);
		commentService.addComment(new Comment(game, loggedPlayer.getName(), comment));
//		scoreService.addScore(new Score("Stevo", "hangman", 5));
		return "redirect:/" + game;
	}
}
