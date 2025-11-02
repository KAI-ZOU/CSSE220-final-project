package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite {
	// a way to add ..... maybe use key value and switch statement within the lowest class, and have a method to changestate and 
	public int width;//?
	public int height;//?
	public Rectangle boundingBox;
	public Rectangle[] attackingCollisionBoxes;
	public Rectangle[] selfCollisionBoxes;
	public BufferedImage image;
	private boolean spriteLoaded = false;
    
	
	public Sprite(int width, int height, String filePath) { // add the stuff for the bounding boxes and collisions. eacn rectangle itself needs a x, y, and width and height. to some degree those can be derived from the given x and y.... i guess! yippee.
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(width, height);
		

        try {
			image = ImageIO.read(Sprite.class.getResource(filePath));
			spriteLoaded= true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
//			e.printStackTrace();
		}
		
		
	}
	
	// maybe add paintcomponent (replaces draw
	
	public void paintComponent (Graphics g2, int x, int y) {
		if (spriteLoaded) {
    		g2.drawImage(image, x, y, width, height, null);
    	}
    	else {
//        g2.setColor(Color.BLACK);
//        g2.fillOval(x, drawY, size, size);
//    		 there really just... shouldn't be an else
    	}
	}

	// a bounding box (general)
	// a bounding box (specific)
}