package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.TimerCandyGeneratorCell;
import game.backend.element.Element;
import game.backend.element.TimerCandy;

public class Level3 extends Level {
    private static final int NUMBER_OF_TIMERS = 3;
    private int minTimer = TimerCandyGeneratorCell.getMaxStartTimeOfTimers();

    @Override
    protected CandyGeneratorCell generatorCellType() {
        return new TimerCandyGeneratorCell(this);
    }

    @Override
    protected GameState newState() {
        return new Level3State();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            updateTimers();
            state().addMove();
        }
        return ret;
    }

    private void updateTimers() {
        int aux = TimerCandyGeneratorCell.getMaxStartTimeOfTimers();

        for (Element e : specials) {
            e.update();
            TimerCandy tcaux = (TimerCandy) e;
            if( tcaux.getTimeLeft() < aux )
                aux = tcaux.getTimeLeft();
        }

        minTimer = aux;
    }

    @Override
    protected void setLevelCharacteristics() {
        setSpecialsAmount(NUMBER_OF_TIMERS);
    }

    private class Level3State extends GameState {

        Level3State() {

        }

        public boolean gameOver() {
            return playerWon() || 0 == minTimer;
        }

        public boolean playerWon() {
            return getSpecialsLeft() <= 0;
        }

        @Override
        public String toString() {
            return super.toString() + ", Moves left: " + minTimer + ", Timers left to emit: " + getSpecialsLeftToEmit() + ", Timers left to pop: " + getSpecialsLeft();
        }
    }

    @Override
    public Class<?> nextLevel() {
        return null;
    }

    @Override
    public Class<?> currentLevel() {
        return Level3.class;
    }
}
