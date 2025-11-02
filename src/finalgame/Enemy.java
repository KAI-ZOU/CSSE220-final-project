package finalgame;

import java.awt.Graphics;

public class Enemy extends Entity{

//	boolean specialMovement;///???? maybe? something using math random, where it changes based off a timer, but is more likely to go towards the player (or away from them). could also do specialenemy instead
	int movementRangeX;
	int movementRangeY;
	int initialXPosition;
	int initialYPosition;
	
	
	public Enemy(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		initialXPosition = xPosition;
		initialYPosition = yPosition;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.movementRangeX = movementRangeX;
		this.movementRangeY = movementRangeY;
	}
	
	@Override
	public void update() {
		if (xPosition <= initialXPosition) {
			velocityX = -velocityX;
		}
		else if (xPosition >= initialXPosition+movementRangeX) {
			velocityX=-velocityX;
		}
		xPosition += velocityX;
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}


}
