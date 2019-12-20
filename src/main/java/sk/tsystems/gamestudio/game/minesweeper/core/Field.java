package sk.tsystems.gamestudio.game.minesweeper.core;

import java.util.Random;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}
//			if (isSolved()) {
//				System.out.println("no zle");
//				state = GameState.SOLVED;
//				return;
//			}
		}
		openAdjacentTiles(row, column);
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */

//	 Implementujte metÃ³du void markTile(int row, int column) v triede Field.
//	 MetÃ³da slÃºÅ¾i na oznaÄ�enie/odznaÄ�enie dlaÅ¾dice Å¡pecifikovanej riadkom a stÄºpcom.
//	 V prÃ­pade, Å¾e je dlaÅ¾dica neodkrytÃ¡ (CLOSED) bude jej stav zmenenÃ½ na oznaÄ�enÃ¡ (MARKED).
//	 Ak je dlaÅ¾dica oznaÄ�enÃ¡ (MARKED) bude jej stav zmenenÃ½ na neodkrytÃ¡ (CLOSED).
//	 Riadky a stÄºpce sÃº Ä�Ã­slovanÃ© od 0.

	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	/**
	 * Generates playing field.
	 */

//	Ãšloha: Implementujte metÃ³du void generate() v triede Field tak, 
//	aby v hernom poli tiles nÃ¡hodne rozloÅ¾ila mÃ­ny, 
//	priÄ�om poÄ�et mÃ­n, ktorÃ© majÃº byÅ¥ rozloÅ¾enÃ© je danÃ½ premennou mineCount.

//	PoznÃ¡mka: Pre nÃ¡hodnÃ© generovanie Ä�Ã­sel 
//	vytvorte objekt triedy java.util.Random (pouÅ¾ite metÃ³du objektu int nextInt(int n)).

//	PoznÃ¡mka: Na nÃ¡hodne zvolenÃ© sÃºradnice v hernom poli vloÅ¾te dlaÅ¾dicu typu Mine ak pole na danÃ½ch 
//	sÃºradniciach uÅ¾ neobsahuje mÃ­nu (tiles[row][column] == null). 
//	Postup opakujte pokiaÄ¾ nebude uloÅ¾enÃ½ poÅ¾adovanÃ½ poÄ�et mÃ­n. 
//	Po realizÃ¡cii tejto Ãºlohy bude hracia plocha obsahovaÅ¥ iba dlaÅ¾dice typu mÃ­na.

	private void generate() {
		Random random = new Random();
		int sm = 0;
		if (mineCount >= rowCount * columnCount) {
			throw new UnsupportedOperationException("prilis vela min");
		}

		while (sm < mineCount) {
			int row = random.nextInt(rowCount);
			int col = random.nextInt(columnCount);
			if (tiles[row][col] == null) {
				tiles[row][col] = new Mine();
				sm++;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Clue(countAdjacentMines(i, j));
				}
			}

		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */

//	Pri kaÅ¾dom odkrytÃ­ hernÃ©ho poÄ¾a pouÅ¾Ã­vateÄ¾om sa testuje moÅ¾nosÅ¥ ukonÄ�enia hry (ÃºspeÅ¡ne alebo neÃºspeÅ¡ne).
//	Ãšloha: Implementujte metÃ³du boolean isSolved() definovanÃº v triede Field, ktorÃ¡ urÄ�uje ÃºspeÅ¡nÃ© odkrytie hracieho poÄ¾a.
//	Hra je ÃºspeÅ¡ne ukonÄ�enÃ¡ ak platÃ­ poÄ�et vÅ¡etkÃ½ch dlaÅ¾dÃ­c - poÄ�et odokrytÃ½ch dlaÅ¾dÃ­c = poÄ�et mÃ­n. 
//	Z uvedenÃ©ho vyplÃ½va, Å¾e hra bude ukonÄ�enÃ¡ vtedy, ak budÃº odokrytÃ© vÅ¡etky dlaÅ¾dice bez mÃ­n. 
//	Pre urÄ�enie poÄ�tu odkrytÃ½ch dlaÅ¾dÃ­c definujte sÃºkromnÃº metÃ³du int getNumberOf(Tile.State state), 
//	ktorÃ¡ vrÃ¡ti poÄ�et dlaÅ¾dÃ­c v danom stave.

	public boolean isSolved() {

		if (state == GameState.FAILED) {
			System.out.println("LOSE !");
			return false;
		} else if ((rowCount * columnCount) - getNumberOf(State.OPEN) == mineCount) {
			state = GameState.SOLVED;
			System.out.println("WIN !");
			return false;
		}
		return true;

//		throw new UnsupportedOperationException("Method isSolved not yet implemented");
	}

	public int getNumberOf(Tile.State state) {// kolko je akych dlazdic
		int temp = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getState() == state) {
					temp++;
				}
			}
		}
		return temp;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	private void openAdjacentTiles(int row, int column) {
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						Clue clue = (Clue) tiles[row][column];
						Tile tile = (Tile) tiles[actRow][actColumn];
						if (tile instanceof Clue && tile.getState() == State.CLOSED && (clue.getValue() == 0)) {
							tile.setState(State.OPEN);
							if (clue.getValue() == 0) {
								openAdjacentTiles(actRow, actColumn);
							} else if (clue.getValue() != 0) {
								openAdjacentTiles(row, column);
							}
						}
					}
				}
			}
		}

	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public GameState getState() {
		return state;
	}

//Pridajte metÃ³du Tile getTile(int row, int column) do triedy Field, ktorÃ¡ vrÃ¡ti dlaÅ¾dicu podÄ¾a 
//	zadanÃ©ho riadku a stÄºpca. Riadky a stÄºpce sÃº Ä�Ã­slovanÃ© od 0.

	public Tile getTile(int row, int column) { // <--
		return tiles[row][column];
	}
}
