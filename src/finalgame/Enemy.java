package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Entity{

//	boolean specialMovement;///???? maybe? something using math random, where it changes based off a timer, but is more likely to go towards the player (or away from them). could also do specialenemy instead
	
	boolean hitPlayer = false; // partially used for testing. maybe we can keep it and make the sprite change to a bloody one.
	
	
	public Enemy(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	public Enemy(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded && !hitPlayer) {
//			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null); // note: commenting this out doesn't make the enemy stop updating its position. 
		}
	}
	
}
