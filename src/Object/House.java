package Object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class House extends Object{
	public House() {
		name = "house";
		solidArea = new Rectangle(48,48,48,48);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Object/house.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
