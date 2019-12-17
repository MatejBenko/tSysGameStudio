package sk.tsystems.gamestudio.games.ships;

import java.util.Random;

import sk.tsystems.gamestudio.controller.ShipsController;

public class GameSession {

	private long ID;
	private boolean open;
//	private long connectedGame;

	private int[][] battlefield;
	private ShipsController sc;

	public GameSession(int x, int y, ShipsController sc) {
		this.sc = sc;
		battlefield = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				battlefield[i][j] = 0;
			}
		}
		do {
			this.ID = new Random().nextLong();
		} while (this.ID == 0);
		open = true;
	}

	public long getID() {
		return ID;
	}

	public boolean isOpen() {
		return open;
	}

	public int getPeace(int x, int y) {
		return battlefield[x][y];
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setOpponentId(long opponentId) {
		sc.setOpponentGameSessionId(opponentId);

	}

}
