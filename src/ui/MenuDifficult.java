package ui;

import java.awt.Graphics;

import Object.House;
import entity.Boss;
import entity.Boss2;
import projectOOP_1.GamePanel;
import util.SceneMethods;

public class MenuDifficult  implements SceneMethods {
	    private GamePanel gp;
		private MyButton bHard, bEasy, bMedium;
		public MenuDifficult(GamePanel gp) {
			this.gp = gp;
			initButtons();
		}
		


		private void initButtons() {

			int w = 122;
			int h = 46;
			int x = 960 / 2 - w / 2;
			int y = 180;
			int yOffset = 100;

			bEasy = new MyButton("Easy", x, y, w, h);
			bMedium = new MyButton("Medium", x, y + yOffset, w, h);
			bHard = new MyButton("Hard", x, y + yOffset * 2, w, h);

		}

		@Override
		public void render(Graphics g) {

			drawButtons(g);

		}

		private void drawButtons(Graphics g) {
			bHard.draw(g);
			bMedium.draw(g);
			bEasy.draw(g);

		}

		@Override
		public void mouseClicked(int x, int y) {

			if (bEasy.getBounds().contains(x, y)) {
				gp.setState(gp.GAME_RULE);
				gp.getUi().setLevelOfDifficult("Easy");
				gp.playMusic(0);
				gp.getBoss()[0] = new Boss(gp);
				gp.getBoss2()[0] = new Boss2(gp);
				gp.getBoss2()[0].setActive(false);
				gp.getUi().setTalked(false);
				gp.getPlayer().setOriginalState();
				gp.getHouse()[0] = new House();
			}
			else if (bMedium.getBounds().contains(x, y)) {
				gp.playMusic(0);
				gp.setState(gp.GAME_RULE);
				gp.getUi().setLevelOfDifficult("Medium");
				gp.playMusic(0);
				gp.getBoss()[0] = new Boss(gp);
				gp.getBoss2()[0] = new Boss2(gp);
				gp.getPlayer().setOriginalState();
				gp.getHouse()[0] = new House();

			}
			else if (bHard.getBounds().contains(x, y)) {
				gp.playMusic(0);
				gp.setState(gp.GAME_RULE);
				gp.getUi().setLevelOfDifficult("Hard");
				gp.playMusic(0);
				gp.getBoss()[0] = new Boss(gp);
				gp.getBoss2()[0] = new Boss2(gp);
				gp.getPlayer().setOriginalState();
				gp.getHouse()[0] = new House();

			}
		}

		@Override
		public void mouseMoved(int x, int y) {
		}

		@Override
		public void mousePressed(int x, int y) {

			if (bEasy.getBounds().contains(x, y))
				bEasy.setChangeColor(true);
			else if (bMedium.getBounds().contains(x, y))
				bMedium.setChangeColor(true);
			else if (bHard.getBounds().contains(x, y))
				bHard.setChangeColor(true);

		}

		@Override
		public void mouseReleased(int x, int y) {
			resetButtons();
		}

		private void resetButtons() {
			bEasy.resetBooleans();
			bMedium.resetBooleans();
			bHard.resetBooleans();

		}

		@Override
		public void mouseDragged(int x, int y) {
			// TODO Auto-generated method stub

		}
	

	}
