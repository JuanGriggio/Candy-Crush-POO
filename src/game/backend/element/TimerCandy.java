package game.backend.element;

public class TimerCandy extends Candy {
    private int timeLeft;

    public TimerCandy(CandyColor color, int startTime) {
        super(color);
        this.timeLeft = startTime;
    }

    public TimerCandy() {
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void decrementTimeLeft() {
        timeLeft--;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(timeLeft);
    }

    @Override
    public String getKey(){
        return "TIMER-" + super.getKey();
    }

    @Override
    public String getFullKey(){
        return "TIMER-" + super.getFullKey();
    }

    @Override
    public void update() {
        decrementTimeLeft();
    }
}
