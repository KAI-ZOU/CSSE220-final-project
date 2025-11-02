package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
//	public Sprite usedSprite = new Sprite(100, 100, 10, 10, "tennis.png");
	public Sprite usedSprite;
	public int xPosition, yPosition;
	public int id; // don't really know that we need this? seems like we might not
	public String spriteName = "DEFAULT";
	// never mind. seems like collisiontype isn't needed.
	public String collisionType; // whether the object has a solid collision (effects movement) or not. may change to a case-like thing for solid, interactable, or damaging. could just be a string where we check the value with conditionals. probably will do thatr.
	
	public GameObject() {
//		sprites.put("DEFAULT", usedSprite);
	}
	
	public GameObject(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) { // REWRITE CONSTRUCTOR TO USE FILE 
		this.id = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		usedSprite = sprites[0];
//		for (int i = 0; i < spriteNames) { // TECHNICALLY SHOULD CHECK FOR NAMES AND SPRITES BEING SAME LENGTH
//			
//		}
	}
	
	public void setSprite(String spriteName) {
		this.spriteName = spriteName;
		this.usedSprite = sprites.get(spriteName);
	}
	
	public void updatePosition() {
		// gaaaaah. maybe add vel
	}
	
	public void getbounds() {// may use something like this, may just use sprite direct
		// returns an array of 4;
	}
	
	public abstract void render(); // will likely delete
	
	public abstract void update(); // will likely delete
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (usedSprite.spriteLoaded) {
			g2.drawImage(usedSprite.image, xPosition,  yPosition,  usedSprite.width,  usedSprite.height, null);
		}
//		usedSprite.paintComponent(g, xPosition, yPosition);
	}
	
//	public abstract void draw();
}
