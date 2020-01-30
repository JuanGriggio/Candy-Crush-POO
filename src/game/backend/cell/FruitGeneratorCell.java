package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

public class FruitGeneratorCell extends CandyGeneratorCell {
    public FruitGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getContent() {
        int fruitType = (int)(Math.random() * FruitType.values().length);
        boolean isFruit = (Math.random() > 0.8);

        if(isFruit) {
            return new Fruit(FruitType.values()[fruitType]);
        }

        int color = (int)(Math.random() * CandyColor.values().length);
        return new Candy(CandyColor.values()[color]);
    }

    @Override
    public Element getAndClearContent() {
        return this.getContent();
    }
}
