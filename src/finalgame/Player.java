package finalgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Entity{

	// skills and other perm
	int[] levelsCleared;
	int movementSpeed;
	// something for iframes when hitting a damaging platform
	
//	boolean featherFall;
//	boolean jumpHold;
//	boolean dash;
//	boolean collectPowersUp; // this and the above 3 were intended to be implemented as permanentupgrades for completing levels. probably will not do any. if one, feather fall. simple if space is pressed and you'd otherwise fall, decrease downward acceleration
	boolean onGround = false;
	public boolean isInvincible = false;
	
	// make a matrix (4x3) for player movement (up, left up, etc)
	// make a corresponding matrix for sprite names
	
	
	public Player(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		this.hp = 100;
	}
	
	
	
	public void takeDamage(int amount)
	{
//		double now = System.currentTimeMillis(); Will use system time to determine invincibility and damage all in one
		//probably should add an if statement to determine whether or not the character is invincible or not
		hp -= amount;
		if (hp <= 0)
		{
			//Something here
		}
	}
	
	
	public void applyAcceleration(int accelX, int accelY) { // CONSIDER APPLYING ACCELERATION TO AUTOMATIC ENEMY MOVEMENT? IT WOULD BE HARD SINCE YOU'D HAVE TO EITHER KNOW WHEN TO SLOW DOWN BEFORE REACHING THE BOUND OR ACCOUNT FOR GOING PAST THE BOUND DUE TO ACCELERATION
	    int accelerationCoefficient = 1; // will be used for special platforms? (ice, slime, water, etc)
	    int naturalDecceleration = 1; // same as above?
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
//		if () {
//			
//		}
//		else if () {
//			
//		}
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
