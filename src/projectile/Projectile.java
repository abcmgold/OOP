package projectile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectOOP_1.GamePanel;

public class Projectile {
	protected GamePanel gp;
	protected String name;
	protected int speed;
	protected int worldX,worldY;
	protected BufferedImage E1, E2, E3, W1, W2, W3, 
	S1, S2, S3, N1, N2, N3, NE1, NE2, NE3, NW1,
	NW2, NW3, SE1, SE2, SE3, SW1, SW2, SW3;
	protected BufferedImage image = null;
	protected String direction="N";
	protected int spriteCouter = 0;
	protected int spriteNum = 1;
	protected int life;
	protected int maxLife;
	protected Rectangle solidArea;
	protected boolean alive; 
	Color myRed = new Color(219, 43, 31);
	Color myBlue = new Color(64, 128, 230);
	
	public Projectile(GamePanel gp) {
		this.gp = gp;
	}
	
	
    public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}

	

	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
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



	public int getSpriteCouter() {
		return spriteCouter;
	}



	public void setSpriteCouter(int spriteCouter) {
		this.spriteCouter = spriteCouter;
	}



	public int getSpriteNum() {
		return spriteNum;
	}



	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}



	public int getLife() {
		return life;
	}



	public void setLife(int life) {
		this.life = life;
	}



	public int getMaxLife() {
		return maxLife;
	}



	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}



	public Rectangle getSolidArea() {
		return solidArea;
	}



	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}



	public boolean isAlive() {
		return alive;
	}



	public void setAlive(boolean alive) {
		this.alive = alive;
	}



	public void getImage() {
		
	}
	
    public void update() {
    	
	}
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
		BufferedImage image = null;

		switch(direction) {
		
		case "S": 
			if (spriteNum == 1) {
			    image = S1;
		    }
		    if (spriteNum == 2) {
		    	image = S2;
		    }
			break; 
		case "N": 
			if (spriteNum == 1) {
			    image = N1;
		    }
		    if (spriteNum == 2) {
		    	image = N2;
		    }
			break;
		case "E": 
			if (spriteNum == 1) {
			    image = E1;
		    }
		    if (spriteNum == 2) {
		    	image = E2;
		    }
			break;
		case "W": 
			if (spriteNum == 1) {
			    image = W1;
		    }
		    if (spriteNum == 2) {
		    	image = W2;
		    }
			break;
		}
		if(name == "mBullet") {
			g2.drawImage(image,screenX, screenY+15, gp.TILE_SIZE/2, gp.TILE_SIZE/2,null);
		}
		else if (name == "pBullet" && gp.getPlayer().isOnSkill() == true ) {
			g2.drawImage(image,screenX, screenY, gp.TILE_SIZE*3/2, gp.TILE_SIZE*3/2,null);
		}
		else if (name == "BBullet" && gp.getPlayer().isOnSkill() == true ) {
			g2.drawImage(image,screenX+20, screenY, gp.TILE_SIZE*3/2, gp.TILE_SIZE*3/2,null);
		}
		else g2.drawImage(image,screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE,null);
		
	}

}
