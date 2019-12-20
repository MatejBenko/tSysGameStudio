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
		field = new Field(8, 8, 3);
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
		@SuppressWarnings("resource")
		Formatter f = new Formatter();

		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);

					f.format("<a href='/mini/openTile?row=%d&column=%d'>", row, column);
					f.format("<img src='/images/mini/%s.png'></a>", getImageName(tile));

				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();
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
