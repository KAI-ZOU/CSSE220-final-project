package finalgame;

import java.awt.Graphics;

public class Platform extends GameObject{

	
	
	
	public Platform(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	
	
	public Platform(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	
	


}
