package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MiniController {
	private Field field;

	private boolean marking;

	public boolean isMarking() {
		return marking;
	}

	@RequestMapping("/mini")
	public String index() {
		field = new Field(8, 8, 6);
		return "mini";
	}

	@RequestMapping("/mini/openTile")
	public String openTile(int row, int column) {
		if (field.getState() == GameState.PLAYING) {
			if (marking)
				field.markTile(row, column);
			else {
				field.openTile(row, column);
			}

		}
		return "mini";
	}

	@RequestMapping("/mini/change")
	public String change() {
		marking = !marking;
		return "mini";
	}
	public String getHtmlField() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		for (int i = 0; i < field.getRowCount(); i++) {
			sb.append("<div class=\"flexRow\">");
			for (int j = 0; j < field.getColumnCount(); j++) {
				sb.append("<div class=\"flexColumn\">");
				Tile tile = field.getTile(i, j);
				sb.append("<img onclick=\"location.href = '/mini/openTile?row="+i+"&column="+j+"';\" src='/images/mini/"+getImageName(tile)+".png'>");
				sb.append("</div>");
			}
			sb.append("</div>");
		}
		sb.append("</div>");
		return sb.toString();
	}

	public String getImageName(Tile tile) {
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPEN:
			if (tile instanceof Clue)
				return "open" + ((Clue) tile).getValue();
			else
				return "mine";
		default:
			throw new IllegalArgumentException();
		}
	}

	public boolean isSolved() {
		return field.isSolved();
	}

}
