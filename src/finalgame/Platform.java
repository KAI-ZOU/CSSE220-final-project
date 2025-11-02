package finalgame;

import java.awt.Graphics;

public class Platform extends GameObject{

	public Platform(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
		collisionType = "SOLID";
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
