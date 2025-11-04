package finalgame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Sprite {
	// a way to add ..... maybe use key value and switch statement within the lowest class, and have a method to changestate and
	public int[] widthes;//?
	public int[] heights;//?
	public int width;
	public int height;
	public String[] filePathes;
	public Rectangle[] boundingBoxes;
	public Rectangle boundingBox;
//	public Rectangle[] attackingCollisionBoxes; // this and the one below might not be happening
//	public Rectangle[] selfCollisionBoxes;
	public BufferedImage image;
	public BufferedImage[] images;
	public boolean spriteLoaded = false;
	public double animationInt = 0;
	public int modVal = 1;
	public double trueMod;
  
	
	public Sprite(int width, int height, String filePath) { // add the stuff for the bounding boxes and collisions. eacn rectangle itself needs a x, y, and width and height. to some degree those can be derived from the given x and y.... i guess! yippee.
		this.widthes = new int[] {width};
		this.heights = new int[] {height};
		this.boundingBoxes = new Rectangle[] {new Rectangle(width, height)};
		this.filePathes = new String[] {filePath, "playerTest2"};
		this.images = new BufferedImage[1];
		
		try {
			images[0] = ImageIO.read(Sprite.class.getResource(filePath));
			spriteLoaded= true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteLoaded = false;
//			e.printStackTrace();
		}
		
		updateImage();
	}
	
	public Sprite(int[] width, int[] height, String[] filePath, double trueMod) { // add the stuff for the bounding boxes and collisions. eacn rectangle itself needs a x, y, and width and height. to some degree those can be derived from the given x and y.... i guess! yippee.
		this.widthes = width;
		this.heights = height;
		this.filePathes = filePath;
		this.modVal = width.length;
		this.trueMod = trueMod;
		this.images = new BufferedImage[width.length];
		this.boundingBoxes = new Rectangle[width.length];
		if (width.length == height.length && height.length == filePath.length) {
			for (int i = 0; i < filePath.length; i++) {
				boundingBoxes[i] = new Rectangle(width[i], height[i]);
	try {
		images[i] = ImageIO.read(Sprite.class.getResource(filePathes[i]));
		spriteLoaded= true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		spriteLoaded = false;
//		e.printStackTrace();
	}
			}
		}
		updateImage();
	}
	
	public void updateImage() {
		animationInt = animationInt%modVal;
		this.width = widthes[(int)animationInt];
		this.height = heights[(int)animationInt];
		this.boundingBox = boundingBoxes[(int)animationInt];
		this.image = images[(int)animationInt];
      
       animationInt+=trueMod;
	}
}
