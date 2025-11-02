package finalgame;

import java.awt.Graphics;

public class Player extends Entity{

	// skills and other perm
	int[] levelsCleared;
	int movementSpeed;
	// something for iframes when hitting a damaging platform
	
	boolean featherFall;
	boolean jumpHold;
	boolean dash;
	boolean collectPowersUp;
	
	
	public Player(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
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
	// score

}
