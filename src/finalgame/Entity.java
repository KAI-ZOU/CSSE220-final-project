package finalgame;


public abstract class Entity extends GameObject{

	int hp;
//	int movementSpeedX; // might not implement
//	int movementSpeedY; // might not
//	HeldItem item; // uh.. idk. // later, might not
	
/*
 * Somewhat redundant after deciding to give all GameObjects motion. Now that movement ranges and velocities are in GameObject, and since enemies don't have health,
 * there's not very much of a reason for this class, aside from future changes that might only affect living things (Player and Enemy (and other?)).
 */
	public Entity(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		// TODO Auto-generated constructor stub
	}
	
	public Entity() {
		
	}

	public Entity(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
	}
	
	public void setVelocity() {
		
	}
	
	public void getVelocity() {
		
	}
	
	private void setPosition() {
		xPosition+=velocityX;
		yPosition+=velocityX;
	}
}
