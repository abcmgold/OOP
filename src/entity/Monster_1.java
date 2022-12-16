package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class Monster_1 extends Entity{
	public Monster_1(GamePanel gp) {
		super(gp);
		alive = true;
		name = "Monster";
		immortal = false;
		if (gp.getUi().getLevelOfDifficult().equals("Easy")) {
			speed = 2;
			fullHP = 500;
			currentHP = fullHP;
		}
		else if (gp.getUi().getLevelOfDifficult()=="Medium") {
			speed = 2;
			fullHP = 1000;
			currentHP = fullHP;
		}
		else {
			speed = 4;
			fullHP = 1500;
			currentHP = fullHP;
		}
		solidArea = new Rectangle(0,0,48,48);
		getImage();
	}
	
	public void getImage() {
		try {
			N1=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
		    N2=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
			S1=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
			S2=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
     		E1=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
			E2=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
			W1=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
			W2=ImageIO.read(getClass().getResourceAsStream("/Img_monster/greenslime_down_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void action() {
		
		if (calculator(worldX, worldY, gp.getPlayer().worldX, gp.getPlayer().worldY)<256) {
			float speedX = (-worldX+gp.getPlayer().worldX);
			float speedY = (-worldY+gp.getPlayer().worldY);
			float maxSpeed = speed*2;
			if (speedX>maxSpeed) speedX = maxSpeed;
			if (speedX<-maxSpeed) speedX = -maxSpeed;
			if (speedY>maxSpeed) speedY = maxSpeed;
			if (speedY<-maxSpeed) speedY = -maxSpeed;
			worldX += speedX;
			worldY += speedY;
		}
		else {
			actionCounter++;
			if (actionCounter==100) {
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
		
	}
	public void update() {
		action();
		gp.getCollision().checkEntity(this, gp.getPlayer());
		if (gp.getCollision().checkEntity(this, gp.getPlayer())!=999 && gp.getPlayer().isImmortal()==false) {
			this.currentHP=-1;
			this.alive=false;
			gp.getPlayer().setCurrentHP(gp.getPlayer().getCurrentHP()-100);
			gp.getPlayer().setImmortal(true);
			gp.playSE(6);
	//		gp.getSt().setCountMonster(gp.getSt().getCountMonster()-1);
			int mana = gp.getPlayer().getCurrentMana() + 20;
			if (mana > gp.getPlayer().fullMana ) {
				gp.getPlayer().setCurrentMana(gp.getPlayer().fullMana);
			}
			else {
				gp.getPlayer().setCurrentMana(mana);
			}
		}
		if (currentHP <=0 ) {
			gp.getSt().setCountMonster(gp.getSt().getCountMonster()-1);
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
		spriteCouter++;
		if (spriteCouter>12) {
			if (spriteNum == 1) spriteNum = 2;
			else if (spriteNum == 2 ) spriteNum = 1;
			spriteCouter = 0;	
		}
		if (immortal == true) {
			immortalCounter ++;
			if (immortalCounter > 20) {
				immortal = false;
				immortalCounter = 0;
			}
		}
	}
}
