package projectile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class BossBullet extends Projectile{
	public BossBullet(GamePanel gp) {
		super(gp);
		alive = false;
		speed = 10;
		maxLife = 50;
		solidArea = new Rectangle(8,0,32,48);
		getImage();
	}
	
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			N2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			S1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			S2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			W1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			W2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			E1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
//			E2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setRock(int worldX, int worldY, String direction, boolean alive) {
		this.worldX=worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.life=this.maxLife;
		gp.getBulletList().add(this);
	
    }
	
	public void update() {
		
		if(gp.getCollision().checkEntity(this, gp.getPlayer())!=999) {
			gp.playSE(2);
			if (gp.getBoss()[0]!=null) {
			gp.getBoss()[0].attack(gp.getBoss()[0],gp.getPlayer());
			}
			gp.getPlayer().setImmortal(true);
			alive = false;
			int mana = gp.getPlayer().getCurrentMana() + 20;
			if (mana > gp.getPlayer().getFullMana() ) {
				gp.getPlayer().setCurrentMana(gp.getPlayer().getFullMana());
			}
			else {
				gp.getPlayer().setCurrentMana(mana);
			}
		}
		
		switch(direction) {
		case "S": 
			worldY +=speed;
			break;
		case "N": 
			worldY -=speed;
			break;
		case "E":
			worldX -=speed;
			break;
		case "W": 
			worldX +=speed;
			break;
	    case "NE": 
		    worldY -=speed*7/10;
			worldX -=speed*7/10;
		    break;
	    case "SE": 
		    worldY +=speed*7/10;
		    worldX -=speed*7/10;
		    break;
	    case "NW":
		    worldY -=speed*7/10;
		    worldX +=speed*7/10;
		    break;
	    case "SW": 
		    worldX +=speed*7/10;
			worldY +=speed*7/10;
		    break;
	    }
		life--;
		if (life <=0) {
			alive = false;
		}
		
//		 spriteCouter++;
//		    if (spriteCouter>12) {
//			   if (spriteNum == 1) spriteNum = 2;
//			   else if (spriteNum == 2 ) spriteNum = 1;
//			   spriteCouter = 0;	
//		    }
		    
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
		g2.drawImage(image,screenX+20, screenY, gp.TILE_SIZE, gp.TILE_SIZE,null);
	}
	
	
}
