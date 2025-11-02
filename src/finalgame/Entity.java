package finalgame;

public abstract class Entity extends GameObject{

	int hp;
	int velocityX;
	int velocityY;
	int movementSpeedX; // 
	int movementSpeedY; // 
//	HeldItem item; // uh.. idk. // later
	
	public Entity(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		// TODO Auto-generated constructor stub
	}

	public abstract void update();
	
	public void setVelocity() {
		
	}
	
	public void getVelocity() {
		
	}
	
	private void setPosition() {
		xPosition+=velocityX;
		yPosition+=velocityX;
	}
	
	public void getNextPosition() { // NEED TO THINK WAY MORE ABOUT THIS ONE.
		
	}
}
