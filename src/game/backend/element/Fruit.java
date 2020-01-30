package game.backend.element;

public class Fruit extends Element {

    private FruitType fruitType;

    public Fruit(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public Fruit() {

    }

    public void setFruitType(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public String getKey() {
        return "FRUIT";
    }

    @Override
    public String getFullKey() {
        return getKey() + fruitType;
    }

    @Override
    public boolean canExplode() {
        return false;
    }
}
