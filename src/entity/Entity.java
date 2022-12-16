package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectOOP_1.GamePanel;


public class Entity {
	protected GamePanel gp;
	protected String name;
	protected int speed;
	protected int worldX,worldY;
	protected int ATK, DEF;
	protected BufferedImage E1, E2, E3, W1, W2, W3, 
	S1, S2, S3, N1, N2, N3, NE1, NE2, NE3, NW1,
	NW2, NW3, SE1, SE2, SE3, SW1, SW2, SW3;
	protected BufferedImage image = null;
	protected String direction="N";
	protected int spriteCouter = 0;
	protected int spriteNum = 1;
	protected int life;
	protected int maxLife;
	protected int fullHP;
	protected int fullMana;
	protected int currentHP;
	protected int currentMana;
	protected int immortalCounter;
	protected boolean immortal;
	
	protected Rectangle solidArea;
	protected boolean collisionOn;
	protected int actionCounter;
	protected boolean alive; 
	Color myRed = new Color(219, 43, 31);
	Color myBlue = new Color(64, 128, 230);
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}

	public int getImmortalCounter() {
		return immortalCounter;
	}

	public void setImmortalCounter(int immortalCounter) {
		this.immortalCounter = immortalCounter;
	}

	public boolean isImmortal() {
		return immortal;
	}

	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getFullHP() {
		return fullHP;
	}
	
	public void setFullHP(int fullHP) {
		this.fullHP = fullHP;
	}
	
	public int getFullMana() {
		return fullMana;
	}
	
	public void setFullMana(int fullMana) {
		this.fullMana = fullMana;
	}
	
	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int aTK) {
		ATK = aTK;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int dEF) {
		DEF = dEF;
	}

	public int getCurrentHP() {
		return currentHP;
	}
	
	public int getCurrentMana() {
		return currentMana;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Rectangle getSolidArea() {
		return solidArea;
	}
	
	public void getImage() {
		
	}
	
	public void action() {
		
	}
	
	public void update() {
		action();
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
		if (immortal == true) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
	    }
		if (name == "boss") {
			g2.drawImage(image,screenX, screenY, gp.TILE_SIZE*2, gp.TILE_SIZE*2,null);
		}
		
		else g2.drawImage(image,screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE,null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		if (name == "Monster" ) {
			double scale = (double)gp.TILE_SIZE/fullHP;
			double hpBar = 	scale*currentHP;
			g2.setColor(myRed);
			g2.fillRect(screenX, screenY-12, (int)hpBar, 10);

		}
		if (name == "boss" ) {
			double scale = (double)gp.TILE_SIZE*2/fullHP;
			double hpBar = 	scale*currentHP;
			g2.setColor(myRed);
			g2.fillRect(screenX, screenY-12, (int)hpBar, 10);
		}
	}
	public void attack(Entity entity1, Entity entity2) {
		if (entity1!=null && entity2!=null) {
		int damage = entity1.getATK()-entity2.getDEF();
		if (damage <= 0) damage = 0;
		entity2.currentHP-=damage;
		}
	}
	public double calculator(int x, int y, int x1, int y1) {
		int x2= (x-x1);
		int y2 = (y-y1);
		return Math.sqrt(x2*x2+y2*y2);
	}
}
