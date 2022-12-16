package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyButton {
	private int x, y, width, height;
	private String text;
	private Rectangle bounds;
	private boolean changeColor;
	
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		initBounds();
	}
	
	
	public boolean isChangeColor() {
		return changeColor;
	}


	public void setChangeColor(boolean changeColor) {
		this.changeColor = changeColor;
	}


	public void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void resetBooleans() {
		this.changeColor = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public void draw(Graphics g) {
		drawImage(g);
		
		drawText(g);
	}
	
	public void drawImage(Graphics g) {
		try {
			BufferedImage Image = ImageIO.read(getClass().getResourceAsStream("/Object/button.png"));
			g.drawImage(Image, x-43, y-21,200, 75, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void drawText(Graphics g) {
		if (changeColor==true) {
			g.setColor(Color.black);}
			else g.setColor(Color.WHITE);
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2-10);

	}
}
