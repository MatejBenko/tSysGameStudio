package sk.tsystems.gamestudio.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.games.ships.GameSession;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/ships")
public class ShipsController {

	private static List<GameSession> shipsSessions = new ArrayList<GameSession>();
	private long thisGameSessionId;
	private long opponentGameSessionId;

	private static final int X = 10, Y = 10;

	@RequestMapping
	public String index() {
		return "ships";
	}

	public String getOpenGameSessions() {
		StringBuilder sb = new StringBuilder();

		if (thisGameSessionId == 0) {
			sb.append("<a href=\"/ships/new\">create</a><br>\n");
			for (GameSession session : shipsSessions) {
				if (session.isOpen()) {
					sb.append("<a href=\"/ships/join?gameID=" + session.getID() + "\">join game:  " + session.getID()
							+ "</a><br>\n");
				}
			}
		} else {
			GameSession temp = getGameSession(thisGameSessionId);

			sb.append("<div class=\"you\">\n");
			sb.append("<a href=\"/ships/end\">end</a><br>\n");
			drawSheep(sb, thisGameSessionId);
			sb.append("</div>\n");

			if (opponentGameSessionId != 0) {
				sb.append("<div class=\"opponent\">\n");
				drawSheep(sb, opponentGameSessionId);
				sb.append("</div>\n");
			}
		}
		return sb.toString();
	}

	@RequestMapping("/new")
	public String createNewGameSession() {
		if (thisGameSessionId == 0) {
			GameSession tmp = new GameSession(X, Y, this);
			thisGameSessionId = tmp.getID();
			shipsSessions.add(tmp);
		}
		return "ships";
	}

	@RequestMapping("/join")
	public String joinGame(String gameID) {
		long temp = Long.valueOf(gameID);
		if (isContainID(temp) && getGameSession(temp).isOpen()) {
			GameSession tmp = new GameSession(X, Y, this);
			thisGameSessionId = tmp.getID();
			shipsSessions.add(tmp);

			opponentGameSessionId = temp;

			getGameSession(thisGameSessionId).setOpen(false);
			getGameSession(opponentGameSessionId).setOpen(false);
			getGameSession(opponentGameSessionId).setOpponentId(thisGameSessionId);

		}
		return "ships";
	}
	
	private void drawSheep(StringBuilder sb,long who) {
		GameSession temp = getGameSession(who);
		for (int i = 0; i < X; i++) {
			sb.append("<div class=\"X\">\n");
			for (int j = 0; j < Y; j++) {
				sb.append("<div class=\"Y\">" + temp.getPeace(i, j) + "</div>\n");
			}
			sb.append("</div>\n");
		}
	}

	private boolean isContainID(long id) {
		for (GameSession gameSession : shipsSessions) {
			if (gameSession.getID() == id) {
				return true;
			}
		}
		return false;
	}

	private GameSession getGameSession(long id) {
		for (GameSession gameSession : shipsSessions) {
			if (gameSession.getID() == id) {
				return gameSession;
			}
		}
		return null;
	}

	@RequestMapping("/end")
	public String endGame() {
		int index = 0;
		boolean found = false;
		for (int i = 0; i < shipsSessions.size(); i++) {
			if (shipsSessions.get(i).getID() == thisGameSessionId) {
				index = i;
				found = true;
				break;
			}
		}
		if (found) {
			if (opponentGameSessionId != 0)
				getGameSession(opponentGameSessionId).setOpponentId(0);
			if (opponentGameSessionId != 0) {
				getGameSession(opponentGameSessionId).setOpen(true);
//				getGameSession(thisGameSessionId).setOpen(true);
			}
			shipsSessions.remove(index);
		}
		this.thisGameSessionId = 0;
		this.opponentGameSessionId = 0;// asi
		return "ships";
	}

	public void setOpponentGameSessionId(long opponentId) {
		this.opponentGameSessionId = opponentId;
	}
}
