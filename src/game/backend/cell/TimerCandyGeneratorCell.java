package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.TimerCandy;

public class TimerCandyGeneratorCell extends CandyGeneratorCell {
    private static final int MAX_START_TIME_OF_TIMERS = 15;
    private static final int MIN_START_TIME_OF_TIMERS = 10;

    public TimerCandyGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getContent() {
        int color = (int)(Math.random() * CandyColor.values().length);
        boolean isTimer = (Math.random() > 0.8);
        int time = (int) Math.floor(Math.random() * (MAX_START_TIME_OF_TIMERS - MIN_START_TIME_OF_TIMERS + 1) + MIN_START_TIME_OF_TIMERS);

        if(isTimer) {
            return new TimerCandy(CandyColor.values()[color], time);
        }
        return new Candy(CandyColor.values()[color]);
    }

    @Override
    public Element getAndClearContent() {
        return this.getContent();
    }

    public static int getMaxStartTimeOfTimers() {
        return MAX_START_TIME_OF_TIMERS;
    }

    public static int getMinStartTimeOfTimers() {
        return MIN_START_TIME_OF_TIMERS;
    }
}
