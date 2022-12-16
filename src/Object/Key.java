package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends Object {
	public Key() {
		name = "key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Object/key.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
