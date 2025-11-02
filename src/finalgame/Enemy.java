package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Entity{

//	boolean specialMovement;///???? maybe? something using math random, where it changes based off a timer, but is more likely to go towards the player (or away from them). could also do specialenemy instead
	int movementRangeX;
	int movementRangeY;
	int initialXPosition;
	int initialYPosition;
	boolean hitPlayer = false; // partially used for testing. maybe we can keep it and make the sprite change to a bloody one.
	
	
	public Enemy(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		initialXPosition = xPosition;
		initialYPosition = yPosition;
		this.velocityX = -velocityX;
		this.velocityY = -velocityY;
		this.movementRangeX = movementRangeX;
		this.movementRangeY = movementRangeY;
	}
	
	@Override
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
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded && !hitPlayer) {
			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null);
		}
		else if (hitPlayer) {
			g2.drawRect(400, 70, 100, 100); // used as flag
		}
//		usedSprite.paintComponent(g, xPosition, yPosition);
	}
	
}
