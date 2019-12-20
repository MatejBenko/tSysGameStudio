package sk.tsystems.gamestudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CommentsController {

	@Autowired
	private ScoreService scoreService;
	
	public String getScoreForGame(String arg) {
//		System.out.println(arg);
		List<Score> tmp = scoreService.getTopScores(arg);
		StringBuilder sb = new StringBuilder();
		for (Score s : tmp) {
			sb.append("user: "+s.getUsername()+" with score: "+s.getScore()+"<br>");
		}
		return sb.toString();
	}
}
