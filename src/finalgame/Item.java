package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Item extends GameObject{
	int scoreGiven;
	// never mind (about below). can delete flag later.
	// might want a boolean has been collected? something like that, then a  check in update to stop drawing it
	
	public Item(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	

	public Item(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded) {
			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null);
		}
	}
}
