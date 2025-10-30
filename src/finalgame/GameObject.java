package finalgame;

import java.util.HashMap;

public abstract class GameObject {
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
	
	public void getbounds() {
		// returns an array of 4;
	}
	
	public abstract void render();
}
