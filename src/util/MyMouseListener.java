package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import projectOOP_1.GamePanel;

public class MyMouseListener implements MouseListener,  MouseMotionListener {
	private GamePanel gp;
	
	public MyMouseListener(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (gp.getState() == gp.START ) {
			gp.getMenu().mouseClicked(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.MENU_DIFFICULT) {
		gp.getDf().mouseClicked(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.LOSE_STATUS || gp.getState() == gp.WIN_STATUS) {
			gp.getMt().mouseClicked	(e.getX(), e.getY());

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (gp.getState() == gp.START) {
			gp.getMenu().mousePressed(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.MENU_DIFFICULT) {
			gp.getDf().mousePressed(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.LOSE_STATUS || gp.getState() == gp.WIN_STATUS) {
			gp.getMt().mousePressed(e.getX(), e.getY());

		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (gp.getState() == gp.START) {
			gp.getMenu().mouseReleased(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.MENU_DIFFICULT) {
			gp.getDf().mouseReleased(e.getX(), e.getY());
		}
		else if (gp.getState() == gp.LOSE_STATUS || gp.getState() == gp.WIN_STATUS) {
			gp.getMt().mouseReleased(e.getX(), e.getY());

		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
