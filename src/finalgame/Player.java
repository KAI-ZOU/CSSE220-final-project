package finalgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Entity{

	int[] levelsCleared;
	int movementSpeed;
	boolean onGround = false;
	public boolean isInvincible = false;
	
	/*
	 * The player class is a GameObject with unique movement, as determined by outside modification of its velocity fields. It also has health.
	 */
	public Player(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		this.hp = 100;
	}
	
	
	
	public void takeDamage(int amount)
	{
		hp -= amount;
		if (hp <= 0)
		{
			
		}
	}
	
	
	public void applyAcceleration(int accelX, int accelY) {
	    int accelerationCoefficient = 1;
	    int naturalDecceleration = 1;
	    int maxSpeedX = 8;
	    int maxSpeedY = 20;

	    // accelerate if input exists
	    if (accelX != 0) {
	        velocityX += accelX * accelerationCoefficient;
	    } 
	    else { // naturally decelerate. at some point refactor the variable to spell it right
	        if (velocityX > 0) {
	        	velocityX -= naturalDecceleration;
	        }
	        else if (velocityX < 0) {
	        	velocityX += naturalDecceleration;
	        }
	        else if (Math.abs(velocityX) < naturalDecceleration) {
	        	velocityX = 0;
	        }
	    }
	    // same as above
	    if (accelY != 0) {
	        velocityY += accelY * accelerationCoefficient;
	    } 
	    else {
	        // apply friction
	        if (velocityY > 0) {
	        	velocityY -= naturalDecceleration;
	        }
	        else if (velocityY < 0) {
	        	velocityY += naturalDecceleration;
	        }
	        else if (Math.abs(velocityY) < naturalDecceleration) {
	        	velocityY = 0;
	        }
	    }

	    // clamp speeds
	    velocityX = Math.clamp(velocityX, -maxSpeedX, maxSpeedX);
	    velocityY = Math.clamp(velocityY, -maxSpeedY, maxSpeedY);
	}


	@Override
	public void update() { // take care of it later
		// TODO Auto-generated method stub
		xPosition+=velocityX;
		yPosition+=velocityY;
		if (velocityX>0 && sprites.size()>1) {
			setSprite("RUNNINGRIGHT");
		}
		else {
			setSprite("DEFAULT");
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
        // Only draw the sprite if the player is NOT invincible OR 
        // if they are invincible but the flicker timer allows drawing.
        // The expression (System.currentTimeMillis() % 200 > 100) causes the sprite to be visible for 100ms
        // and hidden for 100ms, creating a visible flicker.
        if (!isInvincible || (System.currentTimeMillis() % 200 > 100)) { 
            super.paintComponent(g); // Draws the sprite via GameObject's logic
        }
	}

}
