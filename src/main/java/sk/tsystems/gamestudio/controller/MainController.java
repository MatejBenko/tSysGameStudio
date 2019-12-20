package sk.tsystems.gamestudio.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	private Player loggedPlayer;
	
	@RequestMapping("/login")
	public String login(Player player) {
		if ("heslo".equals(player.getPass())) {//database connect
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
}
