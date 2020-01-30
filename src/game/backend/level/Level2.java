package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.*;

public class Level2 extends Level {
    private static int MAX_MOVES = 30;
    private static final int NUMBER_OF_FRUITS = 2;


    @Override
    protected CandyGeneratorCell generatorCellType() {
        return new FruitGeneratorCell(this);
    }

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            while (checkBottomRow());
        }
        return ret;
    }

    private boolean checkBottomRow() {
        for(int i = 0; i < SIZE; i++) {
            Element e = g[SIZE - 1][i].getContent();
            if(e.isSpecial()) {
                removedSpecial(e);
                g[SIZE - 1][i].setContent(new Nothing());
                fallElements();
                return true;
            }
        }
        return false;
    }

    @Override
    protected void setLevelCharacteristics() {
        setSpecialsAmount(NUMBER_OF_FRUITS);
    }

    private int movesLeft() {
        return MAX_MOVES - state.getMoves();
    }

    private class Level2State extends GameState {
        private long maxMoves;

        Level2State(int maxMoves) {
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getSpecialsLeft() <= 0;
        }

        @Override
        public String toString() {
            return super.toString() + ", Moves left: " + movesLeft() + ", Fruits left to emit: " + getSpecialsLeftToEmit() + ", Fruits left to pop: " + getSpecialsLeft();
        }

    }

    @Override
    public Class<?> nextLevel() {
        return Level3.class;
    }

    @Override
    public Class<?> currentLevel() {
        return Level2.class;
    }

}
