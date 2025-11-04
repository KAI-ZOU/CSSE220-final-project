package finalgame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {
	
	// has a jframe (window)
	private final JFrame frame = new JFrame("God. I hate doing work.");
	Level level = new Level();// actual game will have multiple (array, list, or hm)
	// dimensions. for the whole frame. also do a thing so th egame is always in the center even if it doesnt resize.
	
	
	public Game() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(level); 
        frame.pack();                  // Fit to preferred component sizes
        frame.setLocationRelativeTo(null);
	}
	
	void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> new Game().show());
	}
	
}