package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Entity{

	boolean hitPlayer = false;
	boolean canVisor = false;
	
	
	int visor = 2;
	
	public Enemy(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, movementRangeX, movementRangeY, velocityX, velocityY, spriteNames, sprites);
//		this.canVisor = canVisor;
	}
	
	public Enemy(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
//		this.canVisor = canVisor;
	}
	
	public Enemy(String enemyName, int xPosition, int yPosition) {
		int startFlyL = 8;
		int stopFlyL = 9;
		int startFlyR = 18;
		int stopFlyR = 19;
		int startWalkL = 9;
		int stopWalkL = 17;
		int startWalkR = 27;
		int stopWalkR = 35;
		int sizeW = 150;
		int sizeH = 150;
		int numFly = 2;
		int numWalk = 9;
		double flySpeed = 0.09;
		switch (enemyName) {
			case "EnemyKatana":// 9 to 17 is left, 27 to 35 is right)
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT", "WALKRIGHT"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, enemyName), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, enemyName), 0.12, false)});
				break;
			case "EnemyFlail": // two
				this.canVisor = true;
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT1", "WALKRIGHT1", "WALKLEFT2", "WALKRIGHT2"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, (enemyName+"1")), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, (enemyName+"1")), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, (enemyName+"2")), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, (enemyName+"2")), 0.12, false)});
				break;
			case "EnemyAxe":
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT", "WALKRIGHT"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, enemyName), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, enemyName), 0.12, false)});
				break;
			case "EnemyDSpear":
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT", "WALKRIGHT"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, enemyName), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, enemyName), 0.12, false)});
				break;
			case "EnemySpear":
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT", "WALKRIGHT"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, enemyName), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, enemyName), 0.12, false)});
				break;
			case "EnemySkeleton":
				mimicConstructor(0, xPosition, yPosition, 100, 0, 1, 0, new String[] {"WALKLEFT", "WALKRIGHT"}, new Sprite[] {new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(9, 17, enemyName), 0.12, false), new Sprite(arrayReturn(9, sizeW), arrayReturn(9, sizeH), arrayReturn(27, 35, enemyName), 0.12, false)});
				break;
			case "EnemyFlailFlying": // two
				this.canVisor = true;
				mimicConstructor(0, xPosition, yPosition, 100, 15, 1, 1, new String[] {"FLYLEFT1", "FLYRIGHT1", "FLYLEFT2", "FLYRIGHT2"}, new Sprite[] {new Sprite(arrayReturn(numFly, sizeW), arrayReturn(numFly, sizeH), arrayReturn(startFlyL, stopFlyL, (enemyName+"1")), flySpeed, false), new Sprite(arrayReturn(numFly, sizeW), arrayReturn(numFly, sizeH), arrayReturn(startFlyR, stopFlyR, (enemyName+"1")), flySpeed, false), new Sprite(arrayReturn(numFly, sizeW), arrayReturn(numFly, sizeH), arrayReturn(startFlyL, stopFlyL, (enemyName+"2")), flySpeed, false), new Sprite(arrayReturn(numFly, sizeW), arrayReturn(numFly, sizeH), arrayReturn(startFlyR, stopFlyR, (enemyName+"2")), flySpeed, false)});
				break;
			case "EnemySpearFlying":
				mimicConstructor(0, xPosition, yPosition, 100, 15, 1, 1, new String[] {"FLYLEFT", "FLYRIGHT"}, new Sprite[] {new Sprite(arrayReturn(numFly, sizeW), arrayReturn(numFly, sizeH), arrayReturn(startFlyL, stopFlyL, enemyName), flySpeed, false), new Sprite(arrayReturn(numFly, sizeH), arrayReturn(numFly, sizeW), arrayReturn(startFlyR, stopFlyR, enemyName), flySpeed, false)});
				break;
		}
		
	}
	
	public void mimicConstructor(int id, int xPosition, int yPosition, int movementRangeX, int movementRangeY, int velocityX, int velocityY, String[] spriteNames, Sprite[] sprites) {
		mimicConstructor(id, xPosition, yPosition, spriteNames, sprites);
		initialXPosition = xPosition;
		initialYPosition = yPosition;
		this.velocityX = -velocityX;
		this.velocityY = -velocityY;
		this.movementRangeX = movementRangeX;
		this.movementRangeY = movementRangeY;
		update();
	}
	

	public void mimicConstructor(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) { // REWRITE CONSTRUCTOR TO USE FILE 
		this.id = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		velocityX = 0;
		velocityY = 0;
		
		

		for (int i = 0; i < spriteNames.length; i++) {
			if (spriteNames[i] == "") {
				this.sprites.put("DEFAULT", sprites[i]);
			}
			else {
				this.sprites.put(spriteNames[i], sprites[i]);
			}
		}
		
//		usedSprite = this.sprites.get("DEFAULT");
//		update();
		
//		for (int i = 0; i < spriteNames) { // TECHNICALLY SHOULD CHECK FOR NAMES AND SPRITES BEING SAME LENGTH
//			
//		}
	}
	
	public int[] arrayReturn(int len, int num) {
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = num;
		}
		return ret;
	}
	
	public String[] arrayReturn(int start, int end, String name) {
		String[] ret = new String[end-start+1];
		for (int i = 0; i < ret.length; i++) {
			if (start+i < 10) {
				ret[i] = name + "_00" + (start + i) + ".png";
			}
			else {
				ret[i] = name + "_0" + (start + i) + ".png";

			}
		}
		return ret;
	}
	
	@Override
	public void update() {
		super.update();
		if (sprites.containsKey("DEFAULT")) {
			usedSprite = sprites.get("DEFAULT");
		}
		else if (canVisor) {
			if (velocityY!=0) {
				 if (velocityX<0) {
					 usedSprite = sprites.get("FLYLEFT" + visor);
				 }
				 else {
					 usedSprite = sprites.get("FLYRIGHT" + visor);
				 }
			}
			else {
				if (velocityX<0) {
					usedSprite = sprites.get("WALKLEFT" + visor);
				}
				else {
					usedSprite = sprites.get("WALKRIGHT" + visor);
				}
			}
		}
		else {
			if (velocityY!=0) {
				if (velocityX<0) {
					 usedSprite = sprites.get("FLYLEFT");
				 }
				 else {
					 usedSprite = sprites.get("FLYRIGHT");
				 }
			}
			else {
				if (velocityX<0) {
					usedSprite = sprites.get("WALKLEFT");
				}
				else {
					usedSprite = sprites.get("WALKRIGHT");
				}
			}
		}
	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		if (usedSprite.spriteLoaded && !hitPlayer) {
//		}
//	}
}
