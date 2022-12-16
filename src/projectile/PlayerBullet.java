package projectile;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Object.Key;
import projectOOP_1.GamePanel;

public class PlayerBullet extends Projectile{
	private int dropKeyCounter;
	public PlayerBullet(GamePanel gp) {
		super(gp);
		alive = false;
		speed = 24;
		maxLife = 20;
		solidArea = new Rectangle(8,0,32,48);
		name = "pBullet";
	}
	
	public void getImage() {
		try {
			if (gp.getPlayer().isOnSkill() == false) {
			N1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_up_1.png"));
			N2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_up_2.png"));
			S1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_down_1.png"));
			S2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_down_2.png"));
			E1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_left_1.png"));
			E2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_left_2.png"));
			W1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_right_1.png"));
			W2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/fireball_right_2.png"));
			}
			else {
				N1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootup.png"));
				N2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootup.png"));
				S1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootdown.png"));
				S2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootdown.png"));
				E1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootleft.png"));
				E2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootleft.png"));
				W1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootright.png"));
				W2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/shootright.png"));
			}
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
		getImage();
		int index=gp.getCollision().checkEntity(this,gp.getMonster());
		if (index != 999 ) {
			gp.playSE(2);
			alive = false;
			gp.getPlayer().attack(gp.getPlayer(),gp.getMonster()[index]);
			if (gp.getMonster()[index].getCurrentHP()<=0) {
				double random =Math.floor( Math.random()*2);
				if (random == 0) {
					gp.getKeys()[dropKeyCounter] = new Key();
					gp.getKeys()[dropKeyCounter].setWorldX(gp.getMonster()[index].getWorldX());
					gp.getKeys()[dropKeyCounter].setWorldY(gp.getMonster()[index].getWorldY());
					dropKeyCounter++;
				}
				gp.getPlayer().setCoin(gp.getPlayer().getCoin()+100);
				gp.getMonster()[index]=null;
				gp.getSt().setCountMonster(gp.getSt().getCountMonster()-1);
			}	
			if (gp.getMonster()[index]!=null)
			gp.getMonster()[index].setImmortal(true);
		}
		int index2 = gp.getCollision().checkEntity(this, gp.getMonster2());
		if(index2 != 999) {
			gp.playSE(2);
			alive = false;
			gp.getPlayer().attack(gp.getPlayer(),gp.getMonster2()[index2]);
			if (gp.getMonster2()[index2].getCurrentHP()<=0) {
				double random = Math.floor( Math.random()*2);
				if (random == 0) {
					gp.getKeys()[dropKeyCounter] = new Key();
					gp.getKeys()[dropKeyCounter].setWorldX(gp.getMonster2()[index2].getWorldX());
					gp.getKeys()[dropKeyCounter].setWorldY(gp.getMonster2()[index2].getWorldY());
					dropKeyCounter++;
				}
				gp.getPlayer().setCoin(gp.getPlayer().getCoin()+100);
				gp.getMonster2()[index2]=null;
				gp.getSt().setCountMonster(gp.getSt().getCountMonster()-1);
			}	
			if (gp.getMonster2()[index2]!=null)
			gp.getMonster2()[index2].setImmortal(true);
		}
		if (gp.getBoss()[0] != null && gp.getCollision().checkEntity(this, gp.getBoss())!=999) {
			gp.playSE(2);
			alive = false;
			gp.getPlayer().attack(gp.getPlayer(),gp.getBoss()[0]);
			if (gp.getBoss()[0]!=null)
				gp.getBoss()[0].setImmortal(true);
		}
		
		if (gp.getBoss2()[0].isActive() == true) {
			if (gp.getBoss2()[0] !=null && gp.getCollision().checkEntity(this, gp.getBoss2()[0])!=999) {
				gp.playSE(2);
				alive = false;
				gp.getPlayer().attack(gp.getPlayer(), gp.getBoss2()[0]);
				if (gp.getBoss2()[0] !=null) {
					gp.getBoss2()[0].setImmortal(true);
				}
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
