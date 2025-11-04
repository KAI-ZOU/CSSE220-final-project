package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JComponent;

public abstract class GameObject extends JComponent{
	public HashMap<String, Sprite> sprites = new HashMap<>();
	public Sprite usedSprite;
	public int xPosition, yPosition;
	public int id; // don't really know that we need this? seems like we might not
	public String spriteName = "DEFAULT";int initialXPosition;
	int initialYPosition;
	int velocityX;
	int velocityY;
	int movementRangeX;
	int movementRangeY;
	
	
	public GameObject(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		this(id, xPosition, yPosition, spriteNames, sprites);
		initialXPosition = xPosition;
		initialYPosition = yPosition;
		this.velocityX = -velocityX;
		this.velocityY = -velocityY;
		this.movementRangeX = movementRangeX;
		this.movementRangeY = movementRangeY;
	}
	
	public GameObject(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) { // REWRITE CONSTRUCTOR TO USE FILE 
		this.id = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		velocityX = 0;
		velocityY = 0;
		
		usedSprite = sprites[0];
//		for (int i = 0; i < spriteNames) { // TECHNICALLY SHOULD CHECK FOR NAMES AND SPRITES BEING SAME LENGTH
//			
//		}
	}
	
	public void setSprite(String spriteName) {
		this.spriteName = spriteName;
		this.usedSprite = sprites.get(spriteName);
	}
	
	public void update() {
		// logic for automatic movement based off the given movement range. be careful of inputting velocity values in the constructor with the wrong signs.
		if (xPosition <= initialXPosition) {
			velocityX = -velocityX;
		}
		else if (xPosition >= initialXPosition+movementRangeX) {
			velocityX=-velocityX;
		}
		if (yPosition <= initialYPosition) {
			velocityY = -velocityY;
		}
		else 
		if (yPosition >= initialYPosition+movementRangeY) {
			velocityY=-velocityY;
		}
		xPosition += velocityX;
		yPosition += velocityY;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded) {
			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null);
			 // note: commenting this out doesn't make the enemy stop updating its position. 
		}
	}
}
