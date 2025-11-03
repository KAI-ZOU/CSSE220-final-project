package finalgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

// pretty much just the background, should have no actual effect on the player or other entities or objects
// DO SOMETHING TO CENTER EVERYTHING EVEN WHEN WINDOW IS EXPANDED
public class Stage extends JComponent{
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	public BufferedImage image;
	private boolean spriteLoaded = false;
	
	public Stage(String filePath) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		this.setLayout(new BorderLayout());
		
		try {
			image = ImageIO.read(Sprite.class.getResource(filePath));
			spriteLoaded= true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
//			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (spriteLoaded) {
    		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
    	}
	}
}