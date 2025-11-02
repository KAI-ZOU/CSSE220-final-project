package finalgame;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JComponent;

public abstract class GameObject extends JComponent{ // is a component of the panel
	// bounding box
	// collision box
	// getupperboundx
	// the other three
	// state: collisiontype
	// autocollide
	// autocollide (damage)
	// interactcollide
	public HashMap<String, Sprite> sprites = new HashMap<>();
	public Sprite usedSprite;
	public int xPosition, yPosition;
	public int id;
	public String spriteName = "DEFAULT";
	public GameObject() {
		sprites.put("DEFAULT", usedSprite);
	}
	
	public GameObject(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) { // REWRITE CONSTRUCTOR TO USE FILE 
		this.id = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
//		for (int i = 0; i < spriteNames) { // TECHNICALLY SHOULD CHECK FOR NAMES AND SPRITES BEING SAME LENGTH
//			
//		}
	}
	
	public void setSprite(String spriteName) {
		this.spriteName = spriteName;
		this.usedSprite = sprites.get(spriteName);
	}
	
	public void getbounds() {// may use something like this, may just use sprite direct
		// returns an array of 4;
	}
	
	public abstract void render();
	
	public abstract void update();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		usedSprite.paintComponent(g, xPosition, yPosition);
	}
	
//	public abstract void draw();
}
