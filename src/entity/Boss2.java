package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;
import projectile.BossBullet2;

public class Boss2 extends Entity {
	private int rockCounter;
	private BossBullet2 bBullet2;
	private boolean appear;
	private boolean active;
	
	public Boss2(GamePanel gp) {
		super(gp);
		direction = "N";
		bBullet2 = new BossBullet2(gp);
		name = "boss-2";
		solidArea = new Rectangle(0,0,96,96);
		ATK = 300;
		DEF = 100;
		if (gp.getUi().getLevelOfDifficult()=="Easy") {
			fullHP = 2500;
			currentHP = fullHP;
			speed = 4;
		}
		else if (gp.getUi().getLevelOfDifficult()=="Medium") {
			fullHP = 3500;
			currentHP = fullHP;
			speed = 5;
		}
		else {
			fullHP = 5500;
			currentHP = fullHP;
			speed = 6;
		}
		currentHP = fullHP;
		alive = false;
	
		appear = false;
		getImage();
	}

	public boolean isAppear() {
		return appear;
	}

	public void setAppear(boolean appear) {
		this.appear = appear;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void getImage() {
		try {
			N1=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon1.png"));
			N2=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon2.png"));
			N3=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon3.png"));
			E1=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon10.png"));
			E2=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon11.png"));
			E3=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon12.png"));
			W1=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon4.png"));
			W2=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon5.png"));
			W3=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon6.png"));
			S1=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon7.png"));
			S2=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon8.png"));
			S3=ImageIO.read(getClass().getResourceAsStream("/Boss/dragon9.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void action() {
		if (calculator(worldX, worldY, gp.getPlayer().worldX, gp.getPlayer().worldY) < 400) {
			direction = "W";
			active = true;
		}
		if (active == true) {
			rockCounter ++;
			if (rockCounter > 100) {
				bBullet2.setRock(worldX, worldY, direction, true);
				rockCounter = 0;
			}
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
		if (currentHP <=0 || gp.getPlayer().currentHP <=0) {
			this.bBullet2.setAlive(false);
		}
		if (currentHP <=0) {
			gp.playSE(5);
			gp.soundThame.stop();
		}
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
		
		spriteCouter++;
		if (spriteCouter>10 ) {
			if (spriteNum == 1) spriteNum = 2;
			else if (spriteNum == 2 ) spriteNum = 3;
			else spriteNum = 1;
			spriteCouter = 0;	
		}
		
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

		
		if (currentHP <=0) {
			gp.setState(gp.VICTORY) ;
		}
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
		    if (spriteNum == 3) {
			    image = S3;
		    }
			break; 
		case "N": 
			if (spriteNum == 1) {
			    image = N1;
		    }
		    if (spriteNum == 2) {
		    	image = N2;
		    }
		    if (spriteNum == 3) {
			    image = N3;
		    }
			break;
		case "E": 
			if (spriteNum == 1) {
			    image = E1;
		    }
		    if (spriteNum == 2) {
		    	image = E2;
		    }
		    if (spriteNum == 3) {
			    image = E3;
		    }
			break;
		case "W": 
			if (spriteNum == 1) {
			    image = W1;
		    }
		    if (spriteNum == 2) {
		    	image = W2;
		    }
		    if (spriteNum == 3) {
			    image = W3;
		    }
			break;
			
		case "NW": 
			if (spriteNum == 1) {
			    image = NW1;
		    }
		    if (spriteNum == 2) {
		    	image = NW2;
		    }
		    if (spriteNum == 3) {
			    image = NW3;
		    }
			break; 
		case "NE": 
			if (spriteNum == 1) {
			    image = NE1;
		    }
		    if (spriteNum == 2) {
		    	image = NE2;
		    }
		    if (spriteNum == 3) {
			    image = NE3;
		    }
			break;
		case "SE": 
			if (spriteNum == 1) {
			    image = SE1;
		    }
		    if (spriteNum == 2) {
		    	image = SE2;
		    }
		    if (spriteNum == 3) {
			    image = SE3;
		    }
			break;
		case "SW": 
			if (spriteNum == 1) {
			    image = SW1;
		    }
		    if (spriteNum == 2) {
		    	image = SW2;
		    }
		    if (spriteNum == 3) {
			    image = SW3;
		    }
			break;
		}
		g2.drawImage(image,screenX, screenY, gp.TILE_SIZE*4, gp.TILE_SIZE*4,null);
		
		double scale = (double)gp.TILE_SIZE*4/fullHP;
		double hpBar = 	scale*currentHP;
		g2.setColor(myRed);
		g2.fillRect(screenX, screenY-12, (int)hpBar, 10);
	}
}
