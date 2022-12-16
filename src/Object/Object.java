package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectOOP_1.GamePanel;

public class Object {
	protected String name;
	protected BufferedImage image;
	protected int worldX;
	protected int worldY;
	protected Rectangle solidArea = new Rectangle(0,0,48,48);
	private boolean collision = true;
	
	
	public BufferedImage getImage() {
		return image;
	}

	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isCollision() {
		return collision;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
		if (name == "house") {
		    g2.drawImage(image,screenX,screenY,gp.TILE_SIZE*4,gp.TILE_SIZE*4, null);
		}
		else {
		    g2.drawImage(image,screenX,screenY,gp.TILE_SIZE,gp.TILE_SIZE, null);
		}
	}
}
