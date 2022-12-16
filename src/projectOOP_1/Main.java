package projectOOP_1;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		GamePanel gp = new GamePanel();
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Game");
		
		window.add(gp);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gp.startThread();
	}
}
