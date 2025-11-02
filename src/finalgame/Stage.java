package finalgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Stage extends JComponent{
	
//	public static final int WIDTH = 700;
//	public static final int HEIGHT = 300;
	public int WIDTH = 700;
	public int HEIGHT = 300;
//	public final Color testingColor = Color.DARK_GRAY;

	public BufferedImage image;
	private boolean spriteLoaded = false;
	
//	public final int x1 = 0;
 // the background for the level.
	
	public Stage(String filePath) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		try {
			image = ImageIO.read(Sprite.class.getResource(filePath));
			spriteLoaded= true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
//			e.printStackTrace();
		}
		
	}
	
	public Stage(String filePath, int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		this.setPreferredSize(new Dimension(w, h));
		
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