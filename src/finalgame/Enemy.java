package finalgame;

import java.awt.Graphics;

public class Enemy extends Entity{

//	boolean specialMovement;///???? maybe? something using math random, where it changes based off a timer, but is more likely to go towards the player (or away from them). could also do specialenemy instead
	int movementRangeX;
	int movementRangeY;
	
	public Enemy(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}


}
