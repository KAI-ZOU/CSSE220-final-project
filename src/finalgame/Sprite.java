package finalgame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Sprite {
	public int[] widthes;//?
	public int[] heights;//?
	public int width;
	public int height;
	public String[] filePathes;
	public Rectangle[] boundingBoxes;
	public Rectangle boundingBox;
	public BufferedImage image;
	public BufferedImage[] images;
	public boolean spriteLoaded = false;
	public double animationInt = 0;
	public int modVal = 1;
	public double trueMod;
	boolean keepEnd = false;
  
	/*
	 * This class can be found in all GameObjects. It loads an image with a certain height and width, and may cycle through images if multiple are provided. The rate
	 * of cycling can be changed with the trueMod parameter. This class represents a single state--for example, running left, standing still, or falling. Each GameObject
	 * may have multiple sprites to represent different states.
	 */
	public Sprite(int width, int height, String filePath) {
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
		}
		
		updateImage();
	}
	
	public Sprite(int[] width, int[] height, String[] filePath, double trueMod, boolean keepEnd) {
		this.widthes = width;
		this.heights = height;
		this.filePathes = filePath;
		this.modVal = width.length;
		this.trueMod = trueMod;
		this.images = new BufferedImage[width.length];
		this.boundingBoxes = new Rectangle[width.length];
		this.keepEnd = keepEnd;
		if (width.length == height.length && height.length == filePath.length) {
			for (int i = 0; i < filePath.length; i++) {
				boundingBoxes[i] = new Rectangle(width[i], height[i]);
	try {
		images[i] = ImageIO.read(Sprite.class.getResource(filePathes[i]));
		spriteLoaded= true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		spriteLoaded = false;
	}
			}
		}
		updateImage();
	}
	
	/*
	 * This method cycles between images for the sprite. It also may keep the last image or cycle back to the first depending on the input given in its constructor.
	 */
	public void updateImage() {
		if (!keepEnd) {
			animationInt = animationInt%modVal;
			this.width = widthes[(int)animationInt];
			this.height = heights[(int)animationInt];
			this.boundingBox = boundingBoxes[(int)animationInt];
			this.image = images[(int)animationInt];
			animationInt+=trueMod;
		}
		else {
			if (animationInt < modVal) {
				this.width = widthes[(int)animationInt];
				this.height = heights[(int)animationInt];
				this.boundingBox = boundingBoxes[(int)animationInt];
				this.image = images[(int)animationInt];
				animationInt+=trueMod;
			}
			else {
				this.width = widthes[(int)modVal-1];
				this.height = heights[(int)modVal-1];
				this.boundingBox = boundingBoxes[(int)modVal-1];
				this.image = images[(int)modVal-1];
			}
		}
	}
}
