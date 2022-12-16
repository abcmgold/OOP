package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;
import projectile.PlayerBullet;
import util.KeyHandle;

public class Player extends Entity{
	private KeyHandle keyH;
	private final int screenX;
	private final int screenY;
	private int countKey;
	private int coin;
	private boolean isShoot;
	private int afterTimeCanShoot;
	private int dieCounter;
	private PlayerBullet pBullet;
	private int lifeOfUpSpeed = 180;
	private boolean upSpeed = false;
	private boolean onSkill = false;
	
	
	public Player(GamePanel gp, KeyHandle keyH) {
		super(gp);
		name = "player";
		myRed = new Color(121,8,2);
		this.keyH = keyH;
		screenX = gp.WIDTH/2-gp.TILE_SIZE/2;
		screenY = gp.HEIGHT/2-gp.TILE_SIZE/2;
		speed = 8;
		immortalCounter = 0;
		countKey = 5;
		ATK = 250;
		DEF = 100;
		solidArea = new Rectangle(10,16,28,32);
		fullHP = 500;
		fullMana = 200;
		currentHP = fullHP;
		currentMana = fullMana;
		immortal = false;
		alive = true;
		dieCounter=0;
		pBullet = new PlayerBullet(gp);
		setDefaultValue();
		getImage();
	}
	
	public int getLifeOfUpSpeed() {
		return lifeOfUpSpeed;
	}



	public void setLifeOfUpSpeed(int lifeOfUpSpeed) {
		this.lifeOfUpSpeed = lifeOfUpSpeed;
	}



	public boolean isUpSpeed() {
		return upSpeed;
	}



	public void setUpSpeed(boolean upSpeed) {
		this.upSpeed = upSpeed;
	}



	public boolean isOnSkill() {
		return onSkill;
	}



	public void setOnSkill(boolean onSkill) {
		this.onSkill = onSkill;
	}



	public PlayerBullet getpBullet() {
		return pBullet;
	}

	public void setShoot(boolean isShoot) {
		this.isShoot = isShoot;
	}

	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	public int getCountKey() {
		return countKey;
	}

	public void setCountKey(int countKey) {
		this.countKey = countKey;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}
	
	public boolean isImmortal() {
		return immortal;
	}
	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}
	public void setDefaultValue() {
		setWorldX(gp.TILE_SIZE*23) ;
		setWorldY(gp.TILE_SIZE*21) ;
	}	

	public void update() {
		if (keyH.isN()==true||keyH.isS()==true||keyH.isE()==true||keyH.isW()==true) {
			collisionOn = false;
			gp.getCollision().check(this);
			
			if (gp.getCollision().checkObject(this, gp.getHouse()[0])!=999 ) {
				gp.setState(gp.TRANSFER_MAP);
			}
			
			if (gp.getCollision().checkObject(this, gp.getKeys())!=999) {
				gp.getKeys()[gp.getCollision().checkObject(this, gp.getKeys())]=null;
				countKey++;
				gp.playSE(1);
			}
						
			if (gp.getCollision().checkEntity(this, gp.getMonster())!=999 && immortal == false) {
				int i = gp.getCollision().checkEntity(this, gp.getMonster());
				gp.playSE(6);
				gp.getMonster()[i].alive = false;
				gp.getMonster()[i].setCurrentHP(-1);
				currentHP-=100;
				immortal = true;
				gp.getSt().setCountMonster(gp.getSt().getCountMonster()-1);
			}
			
		    if (collisionOn == false) {
				if (gp.getKeyH().isN()) {
					worldY -=speed;
				}
				if (gp.getKeyH().isS()) {
					worldY +=speed;
				}
				if (gp.getKeyH().isE()) {
					worldX -=speed;
				}
				if (gp.getKeyH().isW()) {
					worldX +=speed;
				}
			}
			
			spriteCouter++;
			if (spriteCouter>10 ) {
				if (spriteNum == 1) spriteNum = 2;
				else if (spriteNum == 2 ) spriteNum = 3;
				else spriteNum = 1;
				spriteCouter = 0;	
			}
	    }
		if (immortal == true) {
			immortalCounter ++;
			if (immortalCounter > 10) {
				immortal = false;
				immortalCounter = 0;
			}
		}
		if (upSpeed == true) {
			this.lifeOfUpSpeed --;
			if (lifeOfUpSpeed == 0) {
				speed -=3;
				pBullet.setSpeed(pBullet.getSpeed() - 3);
				lifeOfUpSpeed = 120;
				upSpeed = false;
			}
		}
		
		if (isShoot==true && pBullet.isAlive()==false && afterTimeCanShoot==30) {
		pBullet.setBullet(worldX, worldY, direction, true);
		afterTimeCanShoot = 0;
		if (onSkill == true) {
			currentMana -= 10;
			if (currentMana < 0) onSkill = false;
		}
		}
		if(afterTimeCanShoot < 30) {
			afterTimeCanShoot++;
		}
    }
	public void draw(Graphics2D g2) {		
		g2.setColor(Color.BLACK);
		int screenX = worldX - getWorldX() + getScreenX();
		int screenY = worldY - getWorldY() + getScreenY();
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
		if (immortal == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		if (currentHP <= 0 ) {
			dieCounter++;
			if (dieCounter < 5) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			}
			if (dieCounter >= 5 && dieCounter < 10) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			if (dieCounter >= 10 && dieCounter < 15) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			}
			if (dieCounter >= 15 && dieCounter < 20) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			if (dieCounter >= 20 && dieCounter < 25) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			}
			if (dieCounter >= 25 && dieCounter < 30) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			if (dieCounter >= 30 && dieCounter < 35) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			}
			if (dieCounter >= 35 && dieCounter < 40) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			if (dieCounter >= 40 && dieCounter < 45) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			}
			if (dieCounter >= 45 && dieCounter < 50) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			if (dieCounter >= 50 && dieCounter < 55) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
			for (int i = 0; i < gp.getKeys().length; i++) {
				if (gp.getKeys()[i] != null) {
					gp.getKeys()[i] = null;
				}
			}
			gp.soundThame.stop();
			if (dieCounter>=55) {
				gp.playSE(3);
				alive = false;
				gp.setState(gp.GAME_OVER);
				dieCounter = 0;
				gp.getUi().setTime(0);
			}
		}
		
		g2.drawImage(image,screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		if (upSpeed == true) {
			g2.drawString("Speed up", screenX, screenY);
		}
	}
            
	public void getImage() {
		try {
			N1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/N1.png"));
			N2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/N2.png"));
			N3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/N3.png"));
			S1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/S1.png"));
			S2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/S2.png"));
			S3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/S3.png"));
			E1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/E1.png"));
			E2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/E2.png"));
			E3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/E3.png"));
			W1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/W1.png"));
			W2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/W2.png"));
			W3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/W3.png"));
			
			NW1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NW1.png"));
			NW2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NW2.png"));
			NW3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NW3.png"));
			SW1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SW1.png"));
			SW2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SW2.png"));
			SW3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SW3.png"));
			SE1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SE1.png"));
			SE2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SE2.png"));
			SE3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/SE3.png"));
			NE1 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NE1.png"));
			NE2 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NE2.png"));
			NE3 = ImageIO.read(getClass().getResourceAsStream("/Img_player/NE3.png"));		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setOriginalState() {
		gp.getTileM().setCurrentMap(0);;
		currentHP = fullHP;
		currentMana = fullMana;
		coin = 0;
		countKey = 0;
		alive = true;
		setWorldX(gp.TILE_SIZE*23);
		setWorldY(gp.TILE_SIZE*21);
		direction = "N";
		gp.getUi().setTime(0);
		gp.getSt().setTimeMonster1(0);
		gp.getSt().setCountMonster1(0);
		gp.getSt().setCountMonster2(0);
		gp.getSt().setTimeMonster2(0);
		gp.getSt().setCountMonster(0);
		if (gp.getBoss()[0] != null) gp.getBoss()[0].alive = false;
		for (int i = 0; i < 100;i++) {
			if (gp.getMonster()[i]!=null) {
				gp.getMonster()[i] = null;
			}
		}
		for (int i = 0; i < 100;i++) {
			if (gp.getMonster2()[i]!=null) {
				gp.getMonster2()[i] = null;
			}
		}
		
		if (gp.getBoss()[0] != null) {
		for (int i = 0; i < 8; i++)
		gp.getBoss()[0].getbBullet()[i].setAlive(false);
		gp.getBoss()[0].setAppear(false);
		}
		gp.getUi().getScreenM().setX(gp.TILE_SIZE*23);
		gp.getUi().getScreenM().setY(gp.TILE_SIZE*21);
		gp.getPlayer().setOnSkill(false);
		gp.getPlayer().setATK(250);
	}

	public void drawHealth(Graphics2D g2) {    
			double scale = (double)gp.TILE_SIZE*4/fullHP;
			double hpBar = 	scale*currentHP;
			g2.setColor(myRed);
			g2.fillRect(20, 20, (int)hpBar, 20);
			g2.setColor(Color.black);
			g2.drawRect(20, 20, gp.TILE_SIZE*4, 20);
			g2.setColor(Color.black);
			g2.drawRect(20, 60, gp.TILE_SIZE*4+1, 20+1);			
			
			scale = (double)gp.TILE_SIZE*4/fullMana;
			double manaBar = scale*currentMana;
			g2.setColor(myBlue);
			g2.fillRect(20, 60, (int)manaBar, 20);
			g2.setColor(Color.black);
			g2.drawRect(20, 60, gp.TILE_SIZE*4, 20);
			
		try {
			BufferedImage img =ImageIO.read(getClass().getResourceAsStream("/Item/hearth.png"));
			g2.drawImage(img, 0, 13, 38, 38, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedImage img =ImageIO.read(getClass().getResourceAsStream("/Item/mana.png"));
			g2.drawImage(img, 0, 53, 38, 38, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
