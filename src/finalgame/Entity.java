package finalgame;

public abstract class Entity extends GameObject{
	// hp
	// velocity
	int hp;
	int velocityx;
	int velocityy;
	int movementSpeedX; // 
	int movementSpeedY; // 
//	HeldItem item; // uh.. idk. // later
	
	public abstract void update();
	
	public void setVelocity() {
		
	}
	
	public void getVelocity() {
		
	}
	
	private void setPosition() {
		
	}
	
	public void getNextPosition() { // NEED TO THINK WAY MORE ABOUT THIS ONE.
		
	}
}
