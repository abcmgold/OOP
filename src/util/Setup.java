package util;

import entity.Monster_1;
import entity.Monster_2;
import projectOOP_1.GamePanel;

public class Setup {
	private GamePanel gp;
	private int countMonster1;
	private double timeMonster1;
	private double timeMonster2;
	private int countMonster2;
	private int countMonster;
	public Setup(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public int getCountMonster() {
		return countMonster;
	}


	public void setCountMonster(int countMonster) {
		this.countMonster = countMonster;
	}


	public int getCountMonster1() {
		return countMonster1;
	}

	public void setCountMonster1(int countMonster1) {
		this.countMonster1 = countMonster1;
	}

	public int getCountMonster2() {
		return countMonster2;
	}

	public void setCountMonster2(int countMonster2) {
		this.countMonster2 = countMonster2;
	}
	

	public double getTimeMonster2() {
		return timeMonster2;
	}

	public void setTimeMonster2(double timeMonster2) {
		this.timeMonster2 = timeMonster2;
	}

	public double getTimeMonster1() {
		return timeMonster1;
	}

	public void setTimeMonster1(double timeMonster1) {
		this.timeMonster1 = timeMonster1;
	}

	public void set() {
	//&& countMonster == 0 && gp.getBoss()[0] == null
		if (gp.getTileM().getCurrentMap() == 0 && countMonster == 0 && gp.getBoss()[0] == null ) {
		gp.getHouse()[0].setWorldX(gp.TILE_SIZE*11);
		gp.getHouse()[0].setWorldY(gp.TILE_SIZE*9);
		}

		if (gp.getUi().getTime()<60) {
			timeMonster1+=(double)1/30;
			timeMonster2+=(double)1/30;
			if (Math.floor(timeMonster1) >= 8 && countMonster<5)  {
				int min = 23;
				int max = 27;
				double x;
				double y;
				gp.getMonster()[countMonster1] = new Monster_1(gp);
				x = Math.random()*(max-min+1) + min;
				y = Math.random()*(max-min+1) + min;
				x=Math.floor(x);
				y=Math.floor(y);
				gp.getMonster()[countMonster1].setWorldX((int)x*gp.TILE_SIZE);
				gp.getMonster()[countMonster1].setWorldY((int)y*gp.TILE_SIZE);
				timeMonster1 = 0;
				countMonster1++;	
				countMonster++;
		    }
			
			if (Math.floor(timeMonster2) >= 12 && countMonster<5) {
				int min = 23;
				int max = 27;
				double x;
				double y;
				gp.getMonster2()[countMonster2] = new Monster_2(gp);
				x = Math.random()*(max-min+1) + min;
				y = Math.random()*(max-min+1) + min;
				x=Math.floor(x);
				y=Math.floor(y);
				gp.getMonster2()[countMonster2].setWorldX((int)x*gp.TILE_SIZE);
				gp.getMonster2()[countMonster2].setWorldY((int)y*gp.TILE_SIZE);
				timeMonster2 = 0;
				countMonster2++;
				countMonster++;
			}
		}
		
		if (gp.getBoss()[0]!=null && gp.getUi().getTime() >= 60 && gp.getBoss()[0].isAppear() == false ) {
			gp.getBoss()[0].setAlive(true);
			gp.getBoss()[0].setWorldX(gp.TILE_SIZE*23);
			gp.getBoss()[0].setWorldY(gp.TILE_SIZE*23);
			gp.getBoss()[0].setAppear(true);
		}
		
		if (gp.getBoss2()[0]!=null && gp.getUi().getTime() >= 2 && gp.getBoss2()[0].isAppear() == false && gp.getTileM().getCurrentMap()==1) {
			gp.getBoss2()[0].setAlive(true);
			gp.getBoss2()[0].setWorldX(gp.TILE_SIZE*10);
			gp.getBoss2()[0].setWorldY(gp.TILE_SIZE*12);
			gp.getBoss2()[0].setAppear(true);
		}
    }
}

