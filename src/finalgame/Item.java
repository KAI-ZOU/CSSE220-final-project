package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Item extends GameObject{
	int scoreGiven;
	boolean collected = false;
	// never mind (about below). can delete flag later.
	// might want a boolean has been collected? something like that, then a  check in update to stop drawing it
	
	public Item(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		collisionType = "COLLECT";
	}
	

	public Item(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded && !collected) {
			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null);
		}
		else if (collected) {
			g2.drawRect(70, 70, 100, 100); // used as flag
//			g2.dispose(); // wow. that did not work as i hoped. still gotta figure out how to remove the original image. maybe just change the sprite to something blank? won't work for enemy though.
		}
//		usedSprite.paintComponent(g, xPosition, yPosition);
	}
}
