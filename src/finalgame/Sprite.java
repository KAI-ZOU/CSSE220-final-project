package finalgame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite {
	// a way to add ..... maybe use key value and switch statement within the lowest class, and have a method to changestate and 
	public int width;//?
	public int height;//?
	public Rectangle boundingBox;
	public Rectangle[] attackingCollisionBoxes;
	public Rectangle[] selfCollisionBoxes;
	public BufferedImage image;
	
	public Sprite(int width, int height, String filePath) { // add the stuff for the bounding boxes and collisions. eacn rectangle itself needs a x, y, and width and height. to some degree those can be derived from the given x and y.... i guess! yippee.
		
	}

	// a bounding box (general)
	// a bounding box (specific)
}