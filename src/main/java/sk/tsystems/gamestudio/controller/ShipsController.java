package sk.tsystems.gamestudio.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/ships")
public class ShipsController {

	@RequestMapping
	public static String index() {
		return "ships";
	}

	public String getBattleField() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 22; i++) {
			sb.append(i + "<br>");
		}
		return sb.toString();
//		return "<h1>boha</h1>";

	}

}
