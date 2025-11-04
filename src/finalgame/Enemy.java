package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Entity{

	boolean hitPlayer = false;
	
	public Enemy(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	public Enemy(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	
//	@Override
//	public void update() {
//	    xPosition += velocityX;
//	    yPosition += velocityY;
//
//	    int enemyWidth = usedSprite.width;
//	    int enemyHeight = usedSprite.height;
//
//	    if (xPosition < 0) {
//	        xPosition = 0;
//	        velocityX = -velocityX; 
//	    } else if (xPosition + enemyWidth > Stage.WIDTH) {
//	        xPosition = Stage.WIDTH - enemyWidth;
//	        velocityX = -velocityX; 
//	    }
//
//	    if (yPosition < 0) {
//	        yPosition = 0;
//	        velocityY = -velocityY; 
//	    } else if (yPosition + enemyHeight > Stage.HEIGHT) {
//	    	yPosition = Stage.HEIGHT - enemyHeight; 
//	    	velocityY = -velocityY; 
//	    }
//	    
//	    if (velocityY > 110) {
//	    	velocityY = 110;
//	    }
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded && !hitPlayer) {
		}
	}
}
