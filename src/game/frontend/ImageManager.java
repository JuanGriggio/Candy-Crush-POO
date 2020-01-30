package game.frontend;

import game.backend.element.*;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

class ImageManager {

	private static final String IMAGE_PATH = "images/";
	private Map<String, Image> images;

	ImageManager() {
		WrappedCandy wc = new WrappedCandy();
		VerticalStripedCandy vc = new VerticalStripedCandy();
		HorizontalStripedCandy hc = new HorizontalStripedCandy();
		Fruit f = new Fruit();
		TimerCandy tc = new TimerCandy();
		images = new HashMap<>();
		images.put(new Nothing().getKey(), new Image(IMAGE_PATH + "nothing.png"));
		images.put(new Bomb().getKey(),  new Image(IMAGE_PATH + "bomb.png"));
		images.put(new Wall().getKey(),  new Image(IMAGE_PATH + "wall.png"));
		for (CandyColor cc: CandyColor.values()) {
			images.put(new Candy(cc).getFullKey(),   new Image(IMAGE_PATH + cc.toString().toLowerCase() + "Candy.png"));
		}
		for (CandyColor cc: CandyColor.values()) {
			wc.setColor(cc);
			images.put(wc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "Wrapped.png"));
		}
		for (CandyColor cc: CandyColor.values()) {
			vc.setColor(cc);
			images.put(vc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "VStripped.png"));
		}
		for (CandyColor cc: CandyColor.values()) {
			hc.setColor(cc);
			images.put(hc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "HStripped.png"));
		}
		for (FruitType ft : FruitType.values()) {
			f.setFruitType(ft);
			images.put(f.getFullKey(), new Image(IMAGE_PATH + ft.toString().toLowerCase() + ".png"));
		}
		for (CandyColor cc: CandyColor.values()) {
			tc.setColor(cc);
			images.put(tc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "Candy.png"));
		}
	}

	Image getImage(Element e) {

		return images.get(e.getFullKey());
	}

}
