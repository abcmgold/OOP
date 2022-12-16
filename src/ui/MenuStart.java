package ui;

import java.awt.Graphics;

import projectOOP_1.GamePanel;
import util.SceneMethods;

public class MenuStart  implements SceneMethods {

	    private GamePanel gp;
		private MyButton bPlaying, bContinue, bGuide, bQuit;		
		public MenuStart(GamePanel gp) {
			this.gp = gp;
			initButtons();
		}
		

		public MyButton getbPlaying() {
			return bPlaying;
		}


		public void setbPlaying(MyButton bPlaying) {
			this.bPlaying = bPlaying;
		}


		public MyButton getbContinue() {
			return bContinue;
		}


		public void setbContinue(MyButton bContinue) {
			this.bContinue = bContinue;
		}


		public MyButton getbGuide() {
			return bGuide;
		}


		public void setbGuide(MyButton bGuide) {
			this.bGuide = bGuide;
		}


		public MyButton getbQuit() {
			return bQuit;
		}


		public void setbQuit(MyButton bQuit) {
			this.bQuit = bQuit;
		}


		private void initButtons() {

			int w = 122;
			int h = 46;
			int x = 960 / 2 - w / 2;
			int y = 180;
			int yOffset = 100;

			bPlaying = new MyButton("Play", x, y, w, h);
			bContinue = new MyButton("Continue", x, y + yOffset, w, h);
			bGuide = new MyButton("Guide", x, y + yOffset * 2, w, h);
			bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

		}

		@Override
		public void render(Graphics g) {

			drawButtons(g);

		}

		private void drawButtons(Graphics g) {
			bPlaying.draw(g);
			bContinue.draw(g);
			bGuide.draw(g);
			bQuit.draw(g);

		}

		@Override
		public void mouseClicked(int x, int y) {

			if (bPlaying.getBounds().contains(x, y))
				gp.setState(gp.MENU_DIFFICULT);
			else if (bGuide.getBounds().contains(x, y))
				gp.setState(gp.GUIDE);
			else if (bContinue.getBounds().contains(x, y)) {
				if (gp.getUi().getTime()>0) {
					 gp.setState(gp.PLAY);
					 gp.soundThame.play();
				}
			}
			else if (bQuit.getBounds().contains(x, y))
				System.exit(0);
		}

		@Override
		public void mouseMoved(int x, int y) {
		
		}

		@Override
		public void mousePressed(int x, int y) {

			if (bPlaying.getBounds().contains(x, y))
				bPlaying.setChangeColor(true);
			else if (bContinue.getBounds().contains(x, y))
				bContinue.setChangeColor(true);
			else if (bGuide.getBounds().contains(x, y))
				bGuide.setChangeColor(true);
			else if (bQuit.getBounds().contains(x, y))
				bQuit.setChangeColor(true);

		}

		@Override
		public void mouseReleased(int x, int y) {
			resetButtons();
		}

		private void resetButtons() {
			bPlaying.resetBooleans();
			bContinue.resetBooleans();
			bGuide.resetBooleans();
			bQuit.resetBooleans();

		}

		@Override
		public void mouseDragged(int x, int y) {
			// TODO Auto-generated method stub

		}
	

	}
