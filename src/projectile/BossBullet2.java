package projectile;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class BossBullet2 extends Projectile{
	public BossBullet2(GamePanel gp) {
		super(gp);
		name = "bBullet";
		alive = false;
		speed = 7;
		maxLife = 50;
		solidArea = new Rectangle(8,0,32,48);
		getImage();
	}
	
	public void getImage() {
		try {
			N1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			N2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			S1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			S2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			W1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			W2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			E1 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
			E2 = ImageIO.read(getClass().getResourceAsStream("/Bullet/rock_down_1.png"));
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
			float speedX = (-worldX+gp.getPlayer().getWorldX());
			float speedY = (-worldY+gp.getPlayer().getWorldY());
			float maxSpeed = speed*2;
			if (speedX>maxSpeed) speedX = maxSpeed;
			if (speedX<-maxSpeed) speedX = -maxSpeed;
			if (speedY>maxSpeed) speedY = maxSpeed;
			if (speedY<-maxSpeed) speedY = -maxSpeed;
			worldX += speedX;
			worldY += speedY;
		
		if(gp.getCollision().checkEntity(this, gp.getPlayer())!=999) {
			gp.playSE(2);
			if (gp.getBoss2()[0]!=null) {
			gp.getBoss2()[0].attack(gp.getBoss2()[0],gp.getPlayer());
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
		life--;
		if (life <=0) {
			alive = false;
		}
		
		    
	}
	
//	public void draw(Graphics2D g2) {
//		g2.setColor(Color.BLACK);
//		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
//		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
//		g2.drawImage(image,screenX+20, screenY, gp.TILE_SIZE, gp.TILE_SIZE,null);
//	}
//	
}
