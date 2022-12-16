package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import projectOOP_1.GamePanel;

public class ScreenMove {
	private GamePanel gp;
	private int timeRule;
	private int x,y;
	public ScreenMove(GamePanel gp) {
		this.gp = gp; 
		timeRule = 0;
		x = gp.TILE_SIZE * 23;
		y = gp.TILE_SIZE *21;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void update() {
		if ( x < gp.TILE_SIZE*16 && y < gp.TILE_SIZE*11 ) {
			x = x+0;
			y = y+0;
		}
		else {
			x-=10;
			y-=10;
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol < gp.WORLD_COL && worldRow < gp.WORLD_ROW) {
			int tileNum = gp.getTileM().getMapTile()[0][worldCol][worldRow];
			
			
			int worldX = worldCol * gp.TILE_SIZE;
			int worldY = worldRow * gp.TILE_SIZE;
			int screenX = worldX - x + gp.getPlayer().getScreenX();
			int screenY = worldY - y + gp.getPlayer().getScreenY();
			if (worldX + gp.TILE_SIZE > x - gp.getPlayer().getScreenX() &&
				worldX - gp.TILE_SIZE < x + gp.getPlayer().getScreenX() &&
				worldY + gp.TILE_SIZE > y - gp.getPlayer().getScreenY() &&
				worldY - gp.TILE_SIZE < y + gp.getPlayer().getScreenY()) 
				{
				    g2.drawImage(gp.getTileM().getTile()[tileNum].getImage(),screenX,screenY,gp.TILE_SIZE,gp.TILE_SIZE, null);
				}
			worldCol++;
			if (worldCol == gp.WORLD_COL) {
				worldCol = 0;
				worldRow++;
			}
		}
		g2.setColor(new Color(0,0,0,150));
		if (x < gp.TILE_SIZE*16 && y < gp.TILE_SIZE*11) {
			g2.fillRoundRect(10, 30, 400, 100, 35, 35);
			g2.setColor(Color.WHITE);
			g2.drawString("Kill monster and go to there!", 40, 90);
		}
		timeRule ++;
		if (timeRule == 100) {
			timeRule = 0;
			gp.setState(gp.PLAY);
		};
	}
}

