package projectOOP_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import Object.House;
import Object.Key;
import entity.Boss;
import entity.Boss2;
import entity.Entity;
import entity.Monster_1;
import entity.Monster_2;
import entity.Player;
import projectile.Projectile;
import tile.TileManager;
import ui.MenuDifficult;
import ui.MenuStart;
import ui.MenuRetry;
import ui.UI;
import util.Collision;
import util.KeyHandle;
import util.MyMouseListener;
import util.Setup;
import util.Sound;

public class GamePanel extends JPanel implements Runnable {
	public final int TILE_SIZE = 48;
	public final int COL = 20;
	public final int ROW = 12;
	public final int WIDTH = TILE_SIZE * COL;
	public final int HEIGHT = TILE_SIZE * ROW;
	public final int WORLD_COL = 50;
	public final int WORLD_ROW = 50;
	public final int FWIDTH = TILE_SIZE * WORLD_COL;
	public final int FHEIGHT = TILE_SIZE * WORLD_ROW;
	public final int FPS = 30;
	public final int START = 1;
	public final int PLAY = 2;
	public final int SHOP_WINDOW = 3;
	public final int MENU_DIFFICULT = 4;
	public final int TRANSFER_MAP = 5;
	public final int GAME_RULE = 6;	
	public final int GAME_OVER = 7;
	public final int GUIDE = 8;
	public final int SETTING = 9;
	public final int VICTORY = 10;
	public final int TRANSITION = 11;
	public final int LOSE_STATUS = 12;
	public final int WIN_STATUS = 13;
	public final int TALKTOBOSS = 14;
	public Sound sound = new Sound();
	public Sound soundThame = new Sound();
	
	private int state;
	private Thread gameThread;
	private KeyHandle keyH = new KeyHandle(this);
	private MyMouseListener mouse = new MyMouseListener(this);
	private Player player = new Player(this,keyH);
	private Entity monster[] = new Monster_1[100];	
	private Entity monster2[] = new Monster_2[100];
	private TileManager tileM = new TileManager(this);
	private UI ui = new UI(this);
	private Collision collision = new Collision(this);
	private Setup st = new Setup(this);
    private ArrayList<Projectile> BulletList = new ArrayList<>();
	private ArrayList<Entity> EntityList = new ArrayList<>();
	private Key keys[] = new Key[100];
	private Boss boss[] = new Boss[1];
	private Boss2 boss2[] = new Boss2[1];
	private House house[] = new House[1];
	private MenuStart menu = new MenuStart(this);
	private MenuDifficult df = new MenuDifficult(this);
	private MenuRetry mt = new MenuRetry(this);

	public GamePanel() {
		state = START;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.addMouseListener(mouse);
	}

	public MenuStart getMenu() {
		return menu;
	}


	public MenuDifficult getDf() {
		return df;
	}


	public MenuRetry getMt() {
		return mt;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public House[] getHouse() {
		return house;
	}

	public Boss[] getBoss() {
		return boss;
	}

	public Key[] getKeys() {
		return keys;
	}

	public Setup getSt() {
		return st;
	}

	public Collision getCollision() {
		return collision;
	}
	
	public UI getUi() {
		return ui;
	}

	public TileManager getTileM() {
		return tileM;
	}

	public Entity[] getMonster2() {
		return monster2;
	}

	public Entity[] getMonster() {
		return monster;
	}

	public KeyHandle getKeyH() {
		return keyH;
	}

	public ArrayList<Projectile> getBulletList() {
		return BulletList;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Boss2[] getBoss2() {
		return boss2;
	}

	public void setupGame() {
		st.set();
	}
	
	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (true) {
			update();
			
			repaint();
			
			try {
				double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
				if (remainingTime < 0 ) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (state == PLAY) {
			setupGame();
			keyH.update();
			if (player.isAlive() == true) player.update();
	    	
	    	for (int i =0;i<BulletList.size();i++) {
	    		if (BulletList.get(i)!=null) {
	    				if (BulletList.get(i).isAlive()==true) {
	    					BulletList.get(i).update();
	    				}
	    				if (BulletList.get(i).isAlive()==false) {
	    					BulletList.remove(i);
	    				}
	    		}
	    	}
	    	
	    	for (int i =0;i<monster.length;i++) {
	    		if (monster[i]!=null && monster[i].isAlive()==true) {
	    			monster[i].update();
	    		}
	    		else if (monster[i]!=null && monster[i].isAlive()==false) {
	    			monster[i]=null;
	    		}
	    	}
	    	
	    	for (int i =0;i<monster2.length;i++) {
	    		if (monster2[i]!=null && monster2[i].isAlive()==true) {
	    			monster2[i].update();
	    		}
	    		else if (monster2[i]!=null && monster2[i].isAlive()==false) {
	    			monster2[i]=null;
	    		}
	    	}
	    	
	    	if ( boss[0] != null && boss[0].isAppear() == true && boss[0].isAlive() == true) {
	    	boss[0].update();
	    	}
	    	if (boss[0] != null && boss[0].getCurrentHP() <=0) {
	    		boss[0] = null;
	    	}
	    	
	    	for (int i = 0; i < boss2.length; i++) {
	    	if ( boss2[i] != null && boss2[i].isAppear() == true && boss2[i].isAlive() == true) {
		    	boss2[i].update();
		    	}
		    	if (boss2[i] != null && boss2[i].getCurrentHP() <=0) {
		    		boss2[i] = null;
		    	}
	    	}
		}
		else if (state == GAME_RULE) {
			ui.getScreenM().update();
		}
	}  
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if (state == START ) {
			ui.draw(g2);
			menu.render(g2);

		}
		else if (state == LOSE_STATUS) {
			ui.draw(g2);
			mt.render(g2);
		}
		else if (state == MENU_DIFFICULT) {
			ui.draw(g2);
			df.render(g2);
		}
		else if (state == WIN_STATUS) {
			ui.draw(g2);
			mt.render(g2);
		}
		else {
			tileM.draw(g2);	
			if (house[0] != null) { house[0].draw(g2, this);}
			
			for (int i = 0; i < keys.length; i++) {
				if (keys[i]!=null) {
					keys[i].draw(g2, this);
				}
			}
			
		  
			
		    if (player.isAlive() == true) player.draw(g2);
			for (int i = 0; i < monster.length; i ++) {
				if (monster[i]!=null) {
					EntityList.add(monster[i]);
				}
			}
			
			for (int i = 0; i < monster2.length; i ++) {
				if (monster2[i]!=null) {
					EntityList.add(monster2[i]);
				}
			}
			
			for (int i = 0;i<BulletList.size();i++) {
				if (BulletList.get(i)!=null) {
					BulletList.get(i).draw(g2);
				}
			}
		
		    if (boss[0] != null && boss[0].isAlive() == true) {
		    	EntityList.add(boss[0]);
		    }
		    
		    for (int i = 0; i < boss2.length; i++) {
		    if (boss2[i] != null && boss2[i].isAlive() == true) {
		    	EntityList.add(boss2[i]);
			    }
		    }
			
		    for (int i = 0;i<EntityList.size();i++) {
		    	EntityList.get(i).draw(g2);
		    }
		    for (int i = 0;i<EntityList.size();i++) {
		    	EntityList.remove(i);
		    }
		    player.drawHealth(g2);
		    ui.draw(g2);
		}
		//g2.dispose();
	}
	public void playMusic(int i ) {
		soundThame.setFile(i);
		soundThame.play();
		soundThame.loop();
	}
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
