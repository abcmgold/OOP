package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import projectOOP_1.GamePanel;

public class TileManager {
	private int currentMap = 0;
	public final int maxMap = 10;
	private Tile[] tile;
	private GamePanel gp;
	private int mapTile[][][];
	
	public TileManager(GamePanel gp) {
		this.gp=gp;
		setTile(new Tile[100]);
		mapTile = new int[maxMap][gp.WORLD_COL][gp.WORLD_ROW];
		getTileImage();
		loadMap("map",1);
		loadMap("map2",0);
	}
	
	public void getTileImage() {
		setup(0,"grass00",false);
		setup(1,"grass00",false);
		setup(2,"grass00",false);
		setup(3,"grass00",false);
		setup(4,"grass00",false);
		setup(5,"grass00",false);
		setup(6,"grass00",false);
		setup(7,"grass00",false);
		setup(8,"grass00",false);
		setup(9,"grass00",false);
		
		setup(10,"tile",false);
		setup(11,"road",false);
		setup(12,"brick",true);
		setup(13,"car2",true);
		setup(14,"torch",true);
		setup(15,"tomp",true);
		setup(16,"flower",true);
		setup(17,"tree1",true);
		setup(18,"tree2",false);
		setup(19,"wall",true);
		setup(20,"car1",false);
		setup(21,"road2",true);
		setup(22,"water",true);
		setup(23,"grass",false);
		setup(24,"sand1",true);
		setup(25,"water1",true);
		setup(26,"sand2",true);
		setup(27,"sand3",true);
		setup(28,"sand4",true);
		setup(29,"sand5",true);
		setup(30,"sand6",true);
		setup(31,"sand7",true);
		setup(32,"sand8",true);
		setup(33,"water2",false);
		setup(34,"water3",false);
		setup(35,"water4",false);
		setup(36,"water5",false);
		setup(37,"water6",false);
		setup(38,"water7",false);
		setup(39,"water8",false);
		setup(40,"wall",true);
		setup(41,"tree3",true);
		setup(42,"tree4",true);
		setup(43,"tree5",true);
		setup(44,"tree6",true);
		setup(45,"tree7",true);
		setup(46,"tree8",true);
		setup(47,"tree9",true);
		setup(48,"tree10",true);
		setup(49,"flower1",false);
		setup(50,"water9",true);
		setup(51,"water10",true);
		setup(52,"water11",true);
		setup(53,"bridge",false);
		setup(54,"sand9",false);
		setup(55,"sand10",false);
		setup(56,"sand11",false);
		setup(57,"sand12",false);
		setup(58,"sand13",false);
		setup(59,"sand14",false);
		setup(60,"sand15",false);
		setup(61,"sand16",false);
		setup(62,"sand17",false);
		setup(63,"sand18",false);
		setup(64,"sand19",false);
		setup(65,"rock1",false);
		setup(66,"letter",false);
	}
	
	
	
	public int[][][] getMapTile() {
		return mapTile;
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}

	public void setup(int index, String name, boolean collision) {		
		try {
			tile[index] = new Tile();
			tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/Img_maps/" + name + ".png")));
			tile[index].setCollision(collision);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path, int mapNum) {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/" + path + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));	
			int col = 0;
			int row = 0;
			while (col<gp.WORLD_COL && row < gp.WORLD_ROW) {
				String line = br.readLine();
					while (col < gp.WORLD_COL) {
						String number[] = line.split(" ");
						mapTile[mapNum][col][row] = Integer.parseInt(number[col]);
						col++;
					}	
					if (col ==gp.WORLD_COL) {
						col = 0;
						row++;
					}
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol < gp.WORLD_COL && worldRow < gp.WORLD_ROW) {
			int tileNum = mapTile[currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.TILE_SIZE;
			int worldY = worldRow * gp.TILE_SIZE;
			int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
			int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
			
		
			
			if (worldX + gp.TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
				worldX - gp.TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
				worldY + gp.TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
				worldY - gp.TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() ) 
			{
				g2.drawImage(tile[tileNum].getImage(),screenX,screenY,gp.TILE_SIZE,gp.TILE_SIZE, null);	
			}
			worldCol++;
			
			if (worldCol == gp.WORLD_COL) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

	public Tile[] getTile() {
		return tile;
	}

	public void setTile(Tile[] tile) {
		this.tile = tile;
	}
}
