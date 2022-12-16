package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class UI{
	private GamePanel gp;
	private String[] lines = new String[100];
	private int textCounter;
	private String LevelOfDifficult;
	private ScreenMove screenM;
	private Font font1, font2;
	private BufferedImage  image_key, image_coin,background;
	private BufferedImage[][] items = new BufferedImage[3][3]; 
	private double time;
	private int indexX, indexY;
	private String description[][] = new String[3][3];
	private int cost[][] = new int[3][3];
	private int count;
	private int pointNum;
	private boolean talked;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		screenM = new ScreenMove(gp);
		indexX = 0;
		indexY = 0;
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/CoverPhoto/123.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InputStream is = getClass().getResourceAsStream("/Font/iCiel Alina.otf");
			font1 = Font.createFont(Font.TRUETYPE_FONT, is);
			
			InputStream is1 = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
			font2 = Font.createFont(Font.TRUETYPE_FONT, is1);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setTalked(boolean talked) {
		this.talked = talked;
	}


	public ScreenMove getScreenM() {
		return screenM;
	}


	public void setTextCounter(int textCounter) {
		this.textCounter = textCounter;
	}

	public String getLevelOfDifficult() {
		return LevelOfDifficult;
	}

	public void setLevelOfDifficult(String levelOfDifficult) {
		LevelOfDifficult = levelOfDifficult;
	}
	
	public String[][] getDescription() {
		return description;
	}

	public void setDescription(String[][] description) {
		this.description = description;
	}

	public int[][] getCost() {
		return cost;
	}

	public int getIndexX() {
		return indexX;
	}


	public void setIndexX(int indexX) {
		this.indexX -= indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	public void setIndexY(int indexY) {
		this.indexY -= indexY;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	public int getPointNum() {
		return pointNum;
	}

	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}

	public void draw(Graphics2D g) {
		g.setFont(font1);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,45F));
		g.setColor(Color.WHITE);
		if (gp.getState()==gp.START) {
			g.drawImage(background, 0, 0, null);

			g.setFont(font1);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,45F));
			g.drawString("SENA - META - GAME", gp.TILE_SIZE*6, gp.TILE_SIZE*3);

		}
		if (gp.getState()==gp.MENU_DIFFICULT) {
			g.drawImage(background, 0, 0, null);
			g.drawString("Choose level of difficult", gp.TILE_SIZE*15/2, gp.TILE_SIZE*3);
		}
		
		if(gp.getState() == gp.SHOP_WINDOW) {
			drawSubWindow(g, gp.TILE_SIZE*10, gp.TILE_SIZE, gp.TILE_SIZE*5, gp.TILE_SIZE*8+30);
			Color c = new Color(255,255,255);
			g.setColor(c);
			
			g.setFont(g.getFont().deriveFont(Font.PLAIN,25));
			g.drawString("Attack",gp.TILE_SIZE*11-20 , gp.TILE_SIZE*5/2);
			g.drawString(String.valueOf(gp.getPlayer().getATK()), gp.TILE_SIZE*13+20, gp.TILE_SIZE*5/2);
			
			g.drawString("Defense",gp.TILE_SIZE*11-20 , gp.TILE_SIZE*7/2);
			g.drawString(String.valueOf(gp.getPlayer().getDEF()), gp.TILE_SIZE*13+20 , gp.TILE_SIZE*7/2);
						
			g.drawString("Health",gp.TILE_SIZE*11-20 , gp.TILE_SIZE*9/2);
			String health = String.valueOf(gp.getPlayer().getCurrentHP() + "/" +  gp.getPlayer().getFullHP());
			g.drawString(health,gp.TILE_SIZE*13+20 , gp.TILE_SIZE*9/2);
			
			g.drawString("Mana",gp.TILE_SIZE*11-20 , gp.TILE_SIZE*11/2);
			String mana = String.valueOf(gp.getPlayer().getCurrentMana() + "/" +  gp.getPlayer().getFullMana());
			g.drawString(mana,gp.TILE_SIZE*13+20 , gp.TILE_SIZE*11/2);
			
			g.drawString("Speed",gp.TILE_SIZE*11-20 , gp.TILE_SIZE*13/2);
			String speed = String.valueOf(gp.getPlayer().getSpeed());
			g.drawString(speed,gp.TILE_SIZE*13+20 , gp.TILE_SIZE*13/2);
			
			try {
				 image_key = ImageIO.read(getClass().getResourceAsStream("/Object/key.png"));
				g.drawImage(image_key, gp.TILE_SIZE*11-20, gp.TILE_SIZE*15/2-20, gp.TILE_SIZE, gp.TILE_SIZE, null);
				g.drawString(String.valueOf(gp.getPlayer().getCountKey()), gp.TILE_SIZE*13+20, gp.TILE_SIZE*16/2-10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				 image_coin = ImageIO.read(getClass().getResourceAsStream("/Object/bitcoin.png"));
				
				g.drawImage(image_coin, gp.TILE_SIZE*11-20, gp.TILE_SIZE*17/2-5, gp.TILE_SIZE, gp.TILE_SIZE, null);
				g.drawString(String.valueOf(gp.getPlayer().getCoin()), gp.TILE_SIZE*13+20, gp.TILE_SIZE*18/2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			drawTradeArea(g);
			
			
			for (int i = 0; i<3;i++) 
			for (int k = 0; k<3;k++){
			    loadItem(i,k);
			}
			
		    getDescriptionAndCost();
			
			for (int i = 0;i<2;i++) {
				for (int k = 0;k<3;k++) {
					if (items[i][k]!=null) {
						drawItem(g,i,k);
						drawDescriptionAndCost(g,i,k);
					}
				}				
			}
			
		}
		if (gp.getState()==gp.PLAY) {
			
			time += (double)1/30;
			g.setFont(font2);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,30F));
			g.setColor(Color.BLACK);
			g.drawString("Time: ", gp.WIDTH/2, 30);
			g.drawString( String.valueOf(Math.round(time*10)/10), gp.WIDTH/2+60, 30);
		}
		
		if (gp.getState() == gp.GAME_OVER) {
			count++;
			g.setColor(Color.BLACK);
			g.setFont(font1);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,80));
			if(count >= 15 && count < 30) { g.drawString("You lost!!!", gp.WIDTH/2-gp.TILE_SIZE*2, gp.HEIGHT/2-24);}
			if(count >= 30 && count < 45) { g.drawString("", gp.WIDTH/2-gp.TILE_SIZE*2, gp.HEIGHT/2-24);}
			if(count >= 45 && count < 60) { g.drawString("You lost!!!", gp.WIDTH/2-gp.TILE_SIZE*2, gp.HEIGHT/2-24);}
			if(count >= 60 && count < 75) { g.drawString("", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
			if(count >= 75 && count < 90) { g.drawString("You lost!!!", gp.WIDTH/2-gp.TILE_SIZE*2, gp.HEIGHT/2-24);}
			if(count >= 90 && count < 105) { g.drawString("", gp.WIDTH/2-gp.TILE_SIZE*2, gp.HEIGHT/2-24);}
			if (count > 115) {
				gp.setState(gp.LOSE_STATUS);
				count = 0;	
			}
		}
		
		if (gp.getState() == gp.LOSE_STATUS) {
			try {
				BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/CoverPhoto/123.jpg"));
				g.drawImage(img, 0,0,960,576,null);
				g.drawString("You Lost, Try Again", gp.TILE_SIZE*7, gp.TILE_SIZE*3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gp.getState() == gp.WIN_STATUS) {
			try {
				BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/CoverPhoto/123.jpg"));
				g.drawImage(img, 0,0,960,576,null);
				g.drawString("You Win, Try again to improve experience!", gp.TILE_SIZE*5, gp.TILE_SIZE*3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (gp.getState() == gp.PLAY && gp.getTileM().getCurrentMap() == 1 && talked == false) {
			if (gp.getPlayer().calculator(gp.getPlayer().getWorldX(),
				gp.getPlayer().getWorldY(), gp.getBoss2()[0].getWorldX(), gp.getBoss2()[0].getWorldY()) < 400) {
				gp.setState(gp.TALKTOBOSS);
				talked = true;
			}
		}
		if (gp.getState() == gp.TALKTOBOSS) {
			count ++;
			drawSubWindow(g, 10, 40, 550, 70);
			g.setColor(Color.WHITE);
			g.drawString("Dam choc tuc ta, hon lao, ta se danh....", 40, 90);
			if (count > 150) {
				gp.setState(gp.PLAY);
				count = 0;
			}
		}
		
		if (gp.getState() == gp.VICTORY) {
			gp.soundThame.stop();
			gp.soundThame.reset();
			count++;
			if (count>15) {
				g.setFont(font1);
				g.setFont(g.getFont().deriveFont(Font.PLAIN,80));
				if(count >= 15 && count < 30) { g.drawString("Victoria!!!", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
				if(count >= 30 && count < 45) { g.drawString("", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
				if(count >= 45 && count < 60) { g.drawString("Victoria!!!", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
				if(count >= 60 && count < 75) { g.drawString("", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
				if(count >= 75 && count < 90) { g.drawString("Victoria!!!", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
				if(count >= 90 && count < 105) { g.drawString("", gp.WIDTH/2-24, gp.HEIGHT/2-24);}
			}
			if (count>125) {
				gp.getPlayer().setOriginalState();
				gp.setState(gp.WIN_STATUS);
				count = 0;
			}
		}
		
		if(gp.getState()==gp.TRANSFER_MAP) {
			drawSubWindow(g,gp.TILE_SIZE*2,gp.TILE_SIZE*2, gp.TILE_SIZE*7,gp.TILE_SIZE*3/2);
			g.setColor(Color.WHITE);
			g.drawString("Press N to continue", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		}
		if (gp.getState() == gp.TRANSITION) {
			count ++;
			try {
				BufferedImage img2;
				img2 = ImageIO.read(getClass().getResourceAsStream("/CoverPhoto/123.jpg"));
				g.drawImage(img2, 0,0,gp.WIDTH,gp.HEIGHT,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(Color.white);
			g.drawString("Loading...", gp.WIDTH/2-70, gp.HEIGHT/2-30);
			g.drawRoundRect(gp.WIDTH/2-250, gp.HEIGHT/2, 500, 40,30,30);
			g.fillRoundRect(gp.WIDTH/2-250,  gp.HEIGHT/2, count*10, 40,30,30);
			
			if (count == 50) {
				gp.setState(gp.PLAY);
				gp.getTileM().setCurrentMap(1);
				gp.getHouse()[0] = null;
			}
		}
		if (gp.getState() == gp.GAME_RULE) {
			screenM.draw(g);
		}
		if (gp.getState() == gp.GUIDE) {
			g.setFont(font2);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,30F));
			g.drawImage(background, 0, 0, null);
			g.drawString("A simple game with a simple play!", 20, 50);
			textCounter++;
			String line;
			int i=0;

			try {
				InputStream is = getClass().getResourceAsStream("/maps/guide.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(is));	
				while ((line = br.readLine())!=null) {
					lines[i] = line;
					i++;
				}
				br.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			if (textCounter >=50) {
			g.drawString(lines[0], 100, 100);
			}
			if (textCounter >=100) {
				g.drawString(lines[1], 100, 170);
			}
			if (textCounter >=150) {
				g.drawString(lines[2], 100, 240);
			}
			if (textCounter >=200) {
				g.drawString(lines[3], 100, 310);
			}
			if (textCounter >=250) {
				g.drawString(lines[4], 100, 380);
			}
			if (textCounter >=300) {
				g.drawString(lines[5], 100, 450);
			}
			if (textCounter >=350) {
				gp.setState(gp.START);
				textCounter = 0;
			}
		}
		
		
		if (gp.getState() == gp.SETTING) {
			drawSubWindow(g, gp.WIDTH/2-140, 100, 280, 400);
			g.setColor(Color.WHITE);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,30));
			g.drawString("SETTING", gp.WIDTH/2-50, 140);
			g.drawString("Full Screen", gp.WIDTH/2-100, 230);
			if (pointNum==0) {
				g.drawString(">", gp.WIDTH/2-125, 230);
			}
			g.drawString("Off", gp.WIDTH/2+50, 230);

			g.drawString("Music", gp.WIDTH/2-100, 280);
			if (pointNum==1) {
				g.drawString(">", gp.WIDTH/2-125, 280);
			}
			if (gp.sound.getOnSound() == 0) {
			g.drawString("Off", gp.WIDTH/2+50, 280);
			}
			else if (gp.sound.getOnSound() == 1) {
				g.drawString("On", gp.WIDTH/2+50, 280);
			}
			g.drawString("Exit game", gp.WIDTH/2-100, 330);
			if (pointNum==2) {
				g.drawString(">", gp.WIDTH/2-125, 330);
			}
			g.drawString("Continue", gp.WIDTH/2-100, 420);
			if (pointNum==3) {
				g.drawString(">", gp.WIDTH/2-125, 420);
			}
		}
	}
	
	public void drawSubWindow(Graphics g, int x, int y, int width, int height) {
		Color c1 = new Color(0,0,0,150);
		g.setColor(c1);
		g.fillRoundRect(x, y, width, height, 35, 35);
	}
	
	public void drawTradeArea(Graphics g) {
		drawSubWindow(g, gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE*8, gp.TILE_SIZE*10);
		int distanX = gp.TILE_SIZE*2 + 24;
		int distanY = gp.TILE_SIZE*2 + 56;

		int width = gp.TILE_SIZE*2;
		int height = gp.TILE_SIZE*2;
		int x = gp.TILE_SIZE + 24 + distanX* indexX;
		int y = gp.TILE_SIZE +24 + distanY*indexY;
		g.setColor(Color.WHITE);
		g.drawRoundRect( x, y, width, height, 10 , 10);	
	}
	
	public void loadItem(int i, int k) {
		try {
			items[i][k] = ImageIO.read(getClass().getResourceAsStream("/Item/item"+ i + k +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void drawItem(Graphics g, int i, int k) {
		int indexY = i;
		int indexX = k;
		int distanX = gp.TILE_SIZE*2 + 24;
		int distanY = gp.TILE_SIZE*2 + 56;

		int width = gp.TILE_SIZE*2;
		int height = gp.TILE_SIZE*2;
		int x = gp.TILE_SIZE + 24 + distanX* indexX;
		int y = gp.TILE_SIZE +24 + distanY*indexY;
		
		g.drawImage(items[i][k], x+5, y+5, width-10, height-10, null );
	}
	
	public void getDescriptionAndCost() {
		description[0][0] = "Gain 50 ATK";
		description[0][1] = "Gain 50 DEF";
		description[0][2] = "Gain 20% HP";
		description[1][0] = "Gain ATK's speed";
		description[1][1] = "Gain +1 speed";
		description[1][2] = "3 hop kiem";
		description[2][0] = "13promax";
		description[2][1] = "Gain +1 speed";
		description[2][2] = "3 hop kiem";
		
		cost[0][0] = 100;
		cost[0][1] = 100;
		cost[0][2] = 100;
		cost[1][0] = 100;
		cost[1][1] = 100;
		cost[1][2] = 250;
		cost[2][0] = 100;
		cost[2][1] = 100;
		cost[2][2] = 100;
	}
	public void drawDescriptionAndCost(Graphics g, int i, int k) {
		int indexY = i;
		int indexX = k;
		int distanX = gp.TILE_SIZE*2 + 24;
		int distanY = gp.TILE_SIZE*2 + 56;

		int x = gp.TILE_SIZE + 24 + distanX* indexX;
		int y = gp.TILE_SIZE +24 + distanY*indexY;
		g.setFont(font1);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,23));
		g.setColor(Color.WHITE);
		g.drawString(description[i][k], x, y+48*2 + 15);
		
		try {
			BufferedImage cost_icon = ImageIO.read(getClass().getResourceAsStream("/Object/bitcoin.png"));
			g.drawImage(cost_icon, x+20, y+ 48*2+23,20, 20, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawString(String.valueOf(cost[i][k]), x+40, y+48*2+40);
	}
}

