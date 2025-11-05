package finalgame;


public abstract class Entity extends GameObject{

	int hp;
	int movementSpeedX; // might not implement
	int movementSpeedY; // might not
//	HeldItem item; // uh.. idk. // later, might not
	

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
