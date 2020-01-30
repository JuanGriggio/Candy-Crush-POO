package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;

public class Level1 extends Level {

	private static int REQUIRED_SCORE = 500;
	private static int MAX_MOVES = 20;

	@Override
	protected CandyGeneratorCell generatorCellType() {
		return new CandyGeneratorCell(this);
	}

	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}
	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		return ret;
	}
	
	private class Level1State extends GameState {
		private long requiredScore;
		private long maxMoves;
		
		Level1State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}

		@Override
		public String toString() {
			return super.toString() + ", Moves left: " + (maxMoves - getMoves());
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() > requiredScore;
		}
	}

	@Override
	public Class<?> nextLevel() {
		return Level2.class;
	}

	@Override
	public Class<?> currentLevel() {
		return Level1.class;
	}
}
