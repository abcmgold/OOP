package projectile;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class MonsterBullet extends Projectile{
	public MonsterBullet(GamePanel gp) {
		super(gp);
		name = "mBullet";
		alive = false;
		speed = 16;
		maxLife = 20;
		solidArea = new Rectangle(8,0,32,48);
		getImage();
	}
	
	public void getImage() {
		try {
			N1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			N2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			S1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			S2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			E1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			E2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			W1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			W2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBullet(int worldX, int worldY, String direction, boolean alive) {
			this.worldX=worldX;
			this.worldY = worldY;
			this.direction = direction;
			this.alive = alive;
			this.life=this.maxLife;
			gp.getBulletList().add(this);
	}
	
	public void update() {
		int i = gp.getCollision().checkEntity(this, gp.getPlayer());
		if(i!=999) {
			gp.playSE(2);
			int damage = 200-gp.getPlayer().getDEF();
			if (damage <= 0) damage = 0;
			gp.getPlayer().setCurrentHP(gp.getPlayer().getCurrentHP()-damage);
			
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
		case "N": 
			worldY -=speed;
			break;
		case "S": 
			worldY +=speed;
			break;
		case "E":
			worldX -=speed;
			break;
		case "W": 
			worldX +=speed;
			break;
//		case "NE": 
//			worldY -=speed/2;
//			worldX -=speed/2;
//			break;
//		case "SE": 
//			worldY +=speed/2;
//			worldX -=speed/2;
//			break;
//		case "SW":
//			worldX +=speed/2;
//			worldY +=speed/2;
//			break;
//		case "NW": 
//			worldY -=speed/2;
//			worldX +=speed/2;
//			break;
		}
		life--;
		if (life <=0) {
			alive = false;
		}
	
	    spriteCouter++;
	    if (spriteCouter>12) {
		   if (spriteNum == 1) spriteNum = 2;
		   else if (spriteNum == 2 ) spriteNum = 1;
		   spriteCouter = 0;	
	    }
	}
}
