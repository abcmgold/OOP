package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;
import projectile.BossBullet;

public class Boss extends Entity {
	private BossBullet bBullet[] = new BossBullet[8];
	private int rockCounter;
	private boolean appear;
	public Boss(GamePanel gp) {
		super(gp);
		//speed = 2;
		name = "boss";
		solidArea = new Rectangle(0,0,96,96);
		ATK = 300;
		DEF = 100;
		
		if (gp.getUi().getLevelOfDifficult()=="Easy") {
				fullHP = 1500;
				speed = 2;
			}
			else if (gp.getUi().getLevelOfDifficult()=="Medium") {
				fullHP = 2500;
				speed = 3;
			}
			else  {
				fullHP = 4500;
				speed = 4;
			}
		currentHP = fullHP;
		alive = false;
		rockCounter = 0;
		appear = false;
		getImage();
		
		for (int i = 0; i<8;i++) {
			bBullet[i] = new BossBullet(gp);
		}
	}

	public BossBullet[] getbBullet() {
		return bBullet;
	}

	public boolean isAppear() {
		return appear;
	}



	public void setAppear(boolean appear) {
		this.appear = appear;
	}



	public void getImage() {
		try {
			S1=ImageIO.read(getClass().getResourceAsStream("/Boss/down1.png"));
			S2=ImageIO.read(getClass().getResourceAsStream("/Boss/down2.png"));
			E1=ImageIO.read(getClass().getResourceAsStream("/Boss/left1.png"));
			E2=ImageIO.read(getClass().getResourceAsStream("/Boss/left2.png"));
			W1=ImageIO.read(getClass().getResourceAsStream("/Boss/right1.png"));
			W2=ImageIO.read(getClass().getResourceAsStream("/Boss/right2.png"));
			N1=ImageIO.read(getClass().getResourceAsStream("/Boss/up1.png"));
			N2=ImageIO.read(getClass().getResourceAsStream("/Boss/up2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void action() {
		actionCounter++;
		if (actionCounter==50) {
			Random random = new Random();
			int i = random.nextInt(100) +1;
			if (i<25) {
				direction = "N";
			}
			if (i>25 && i<=50) {
				direction = "S";
			}
			if (i>50 && i<=75) {
				direction = "E";
			}
			if (i>75 && i<=100) {
				direction = "W";
			}
			actionCounter=0;
		}
	}
	public void update() {
		action();
		gp.getCollision().checkEntity(this, gp.getPlayer());
		if (gp.getCollision().checkEntity(this, gp.getPlayer())!=999 && gp.getPlayer().isImmortal()==false) {
			gp.getPlayer().setCurrentHP(gp.getPlayer().getCurrentHP()-gp.getPlayer().getFullHP()/3);
			gp.getPlayer().setImmortal(true);
			immortal = true;
			gp.playSE(6);
		}
		gp.getCollision().check(this);
		if (collisionOn == true) {
			collisionOn = false;
		}
		else if (collisionOn == false) {
			if (direction == "N") {
				worldY-=speed;
			}
			if (direction == "S") {
				worldY+=speed;
			}
			if (direction == "E") {
				worldX-=speed;
			}
			if (direction == "W") {
				worldX+=speed;
			}
		}
		
		if (gp.getPlayer().currentHP < 0) {
			for (int i = 0; i < bBullet.length; i++)
			this.bBullet[i].setAlive(false);
		}
		spriteCouter++;
		if (spriteCouter>12) {
			if (spriteNum == 1) spriteNum = 2;
			else if (spriteNum == 2 ) spriteNum = 1;
			spriteCouter = 0;	
		}
		 rockCounter++;
		if (bBullet[1].isAlive()==false &&  rockCounter > 100) {
			speed = 0;
			bBullet[0].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "N", true);
			bBullet[1].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "S", true);
			bBullet[2].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "E", true);
			bBullet[3].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "W", true);
			bBullet[4].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "SE", true);
			bBullet[5].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "NE", true);
			bBullet[6].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "NW", true);
			bBullet[7].setRock(worldX+gp.TILE_SIZE/2, worldY+gp.TILE_SIZE/2, "SW", true);
			rockCounter = 0;
			}
		if (bBullet[1].isAlive() == false) speed = 3;
		if (currentHP <= 0 ) {
			alive = false;
		}
		if (this.alive == false) {
			worldX = 0;
			worldY = 0;
		}
		if (immortal == true) {
			immortalCounter ++;
			if (immortalCounter > 10) {
				immortal = false;
				immortalCounter = 0;
			}
		}
	}
}
