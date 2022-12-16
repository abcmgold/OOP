package ui;

import java.awt.Graphics;

import projectOOP_1.GamePanel;
import util.SceneMethods;

public class MenuRetry  implements SceneMethods {

		private MyButton bRetry, bQuit;
		private GamePanel gp;
		public MenuRetry (GamePanel gp) {
			this.gp = gp;
			initButtons();
		}
		
		public MyButton getbRetry() {
			return bRetry;
		}

		public void setbRetry(MyButton bRetry) {
			this.bRetry = bRetry;
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

			bRetry = new MyButton("Retry", x, y, w, h);
			bQuit = new MyButton("Quit", x, y + yOffset , w, h);

		}

		@Override
		public void render(Graphics g) {

			drawButtons(g);

		}

		private void drawButtons(Graphics g) {
			bRetry.draw(g);
			bQuit.draw(g);

		}

		@Override
		public void mouseClicked(int x, int y) {

			if (bRetry.getBounds().contains(x,y)) {
				gp.setState(gp.MENU_DIFFICULT);
			}
			else if (bQuit.getBounds().contains(x, y))
				System.exit(0);
		}

		@Override
		public void mouseMoved(int x, int y) {
			
		}

		@Override
		public void mousePressed(int x, int y) {

			if (bRetry.getBounds().contains(x, y))
				bRetry.setChangeColor(true);
			else if (bQuit.getBounds().contains(x, y))
				bQuit.setChangeColor(true);

		}

		@Override
		public void mouseReleased(int x, int y) {
			resetButtons();
		}

		private void resetButtons() {
			bRetry.resetBooleans();
			bQuit.resetBooleans();

		}

		@Override
		public void mouseDragged(int x, int y) {
			// TODO Auto-generated method stub

		}
	

	}
