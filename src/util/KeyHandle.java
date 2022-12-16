package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import projectOOP_1.GamePanel;

public class KeyHandle implements KeyListener {
	private GamePanel gp;
	private boolean[] keys;
	private boolean N, W, E, S;
	
	public KeyHandle(GamePanel gp) {
		keys = new boolean[1000];
		this.gp = gp;
	}
	
	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	public boolean isN() {
		return N;
	}

	public boolean isW() {
		return W;
	}

	public boolean isE() {
		return E;
	}

	public boolean isS() {
		return S;
	}

	public void update() {
		N = keys[KeyEvent.VK_W];
		S = keys[KeyEvent.VK_S];
		E = keys[KeyEvent.VK_A];
		W = keys[KeyEvent.VK_D];
		
		if (N) {
			gp.getPlayer().setDirection("N");
		}
		if (S) {
			gp.getPlayer().setDirection("S");
		}
		if (E) {
			gp.getPlayer().setDirection("E");
		}
		if (W) {
			gp.getPlayer().setDirection("W");
		}
		if (N && W) {
			gp.getPlayer().setDirection("NW");
		}
		if (S && W) {
			gp.getPlayer().setDirection("SW");
		}
		if (E && S) {
			gp.getPlayer().setDirection("SE");
		}
		if (N && E) {
			gp.getPlayer().setDirection("NE");
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		keys[e.getKeyCode()]=true;
		
//		if (code == KeyEvent.VK_DOWN) {
//			gp.zoomInOut(-1);
//		}
//		
//		if (code == KeyEvent.VK_UP) {
//			gp.zoomInOut(1);
//		}
		
		if (code == KeyEvent.VK_SPACE) {
			gp.getPlayer().setShoot(true);
		} 
		
		if (code == KeyEvent.VK_E && gp.getPlayer().isUpSpeed() == false && gp.getPlayer().getCurrentMana() >= 20) {
			gp.getPlayer().setUpSpeed(true);
			gp.getPlayer().setSpeed(gp.getPlayer().getSpeed()+3);
			gp.getPlayer().getpBullet().setSpeed(gp.getPlayer().getpBullet().getSpeed()+2);
			gp.getPlayer().setCurrentMana(gp.getPlayer().getCurrentMana()-20); 
		}
		
		if (code == KeyEvent.VK_P) {
			gp.playSE(7);
			if (gp.getState()==gp.PLAY) {
				gp.setState(gp.SHOP_WINDOW);
			}
			else if (gp.getState()==gp.SHOP_WINDOW) {
				gp.setState(gp.PLAY);
			}
		}
		
		if (gp.getState()==gp.SHOP_WINDOW) {
			if (code == KeyEvent.VK_LEFT) {
				gp.playSE(4);
				gp.getUi().setIndexX(1);
				if (gp.getUi().getIndexX()==-1) gp.getUi().setIndexX(-3);
			}
			if (code == KeyEvent.VK_RIGHT) {
				gp.playSE(4);
				gp.getUi().setIndexX(-1);
				if (gp.getUi().getIndexX()==3) gp.getUi().setIndexX(3);
			}
			if (code == KeyEvent.VK_UP) {
				gp.playSE(4);
				gp.getUi().setIndexY(1);
				if (gp.getUi().getIndexY()==-1) gp.getUi().setIndexY(-3);
			}
			if (code == KeyEvent.VK_DOWN) {
				gp.playSE(4);
				gp.getUi().setIndexY(-1);
				if (gp.getUi().getIndexY()==3) gp.getUi().setIndexY(3);
			}
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gp.playSE(7);
			if(gp.getState() == gp.SETTING) {
				gp.setState(gp.PLAY);
			}
			else  if (gp.getState() == gp.PLAY) {
				gp.setState(gp.SETTING);
			}
		}
		if (gp.getState() == gp.SETTING && code == KeyEvent.VK_DOWN) {
			gp.getUi().setPointNum(gp.getUi().getPointNum()+1);
			if (gp.getUi().getPointNum()==4) {
				gp.getUi().setPointNum(0);
			}
		}
		if (gp.getState() == gp.SETTING && code == KeyEvent.VK_UP) {
			gp.getUi().setPointNum(gp.getUi().getPointNum()-1);
			if (gp.getUi().getPointNum()==-1) {
				gp.getUi().setPointNum(3);
			}
		}
		
		if (gp.getState() == gp.PLAY && code == KeyEvent.VK_R) {
			if (gp.getPlayer().isOnSkill() == true) {
				gp.getPlayer().setOnSkill(false);
			}
			else if (gp.getPlayer().isOnSkill() == false){
				gp.getPlayer().setOnSkill(true);
			}
			
			if (gp.getPlayer().isOnSkill() == true) {
			gp.getPlayer().setATK(gp.getPlayer().getATK()+150);
			}
			else {
				gp.getPlayer().setATK(gp.getPlayer().getATK()-150);
			}
		}
		
		if (gp.getState() == gp.SETTING && code == KeyEvent.VK_ENTER) {
			switch (gp.getUi().getPointNum()) {
			case 0: 
				break;
			case 1: 
				if (gp.sound.getOnSound() == 1) {
					gp.sound.setOnSound(0);
					gp.sound.checkVolume();
					gp.soundThame.setOnSound(0);
					gp.soundThame.checkVolume();
				}
				else {
					gp.sound.setOnSound(1);
					gp.sound.checkVolume();
     				gp.soundThame.setOnSound(1);
					gp.soundThame.checkVolume();
					
				}
				break;
			case 2: gp.setState(gp.START); 
			        gp.soundThame.stop();
			    break; 
			case 3: gp.setState(gp.PLAY);
			break;
			}
		}
		if (gp.getState()==gp.TRANSFER_MAP && code == KeyEvent.VK_N) {
			gp.setState(gp.TRANSITION);
			gp.getPlayer().setWorldX(gp.TILE_SIZE*23);
			gp.getPlayer().setWorldY(gp.TILE_SIZE*23);

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		keys[e.getKeyCode()]=false;
		if (code == KeyEvent.VK_SPACE) {
			gp.getPlayer().setShoot(false);
		} 
		if (code == KeyEvent.VK_ENTER && gp.getState()==gp.SHOP_WINDOW) {
			if(gp.getPlayer().getCoin()>=gp.getUi().getCost()[gp.getUi().getIndexY()][gp.getUi().getIndexX()]) {
				gp.getPlayer().setCoin(gp.getPlayer().getCoin()-gp.getUi().getCost()[gp.getUi().getIndexY()][gp.getUi().getIndexX()]);
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("50 ATK")) {
					gp.getPlayer().setATK(gp.getPlayer().getATK()+50);
				}
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("DEF")) {
					gp.getPlayer().setDEF(gp.getPlayer().getDEF()+50);
				}
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("+1 speed")) {
					gp.getPlayer().setSpeed(gp.getPlayer().getSpeed()+1);
				}
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("HP")) {
					if (gp.getPlayer().getCurrentHP()<gp.getPlayer().getFullHP() * 4/5) {
						gp.getPlayer().setCurrentHP(gp.getPlayer().getCurrentHP()+100);
					}
					else gp.getPlayer().setCoin(gp.getPlayer().getCoin()+gp.getUi().getCost()[gp.getUi().getIndexY()][gp.getUi().getIndexX()]);
				}
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("kiem")) {
					gp.getPlayer().setATK(gp.getPlayer().getATK()+50);
					gp.getPlayer().setDEF(gp.getPlayer().getDEF()+50);
					gp.getPlayer().setSpeed(gp.getPlayer().getSpeed()+1);
				}
				if (gp.getUi().getDescription()[gp.getUi().getIndexY()][gp.getUi().getIndexX()].contains("ATK's speed")) {
					
					gp.getPlayer().getpBullet().setSpeed(gp.getPlayer().getpBullet().getSpeed()+1);
				}
			}
		}
	}
}

